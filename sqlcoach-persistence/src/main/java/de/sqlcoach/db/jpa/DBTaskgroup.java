/*******************************************************************************
 * This file is part of SQLCoach.
 *
 * SQLCoach is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SQLCoach is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.sqlcoach.db.jpa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.util.DBUtil;

/**
 * Class extends methods to manipulate Taskgroup Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBTaskgroup extends DBBase implements DBTaskgroupService {

	private static final Logger LOG = LoggerFactory.getLogger(DBTaskgroup.class);
	private static final String ENTITYNAME = Taskgroup.class.getSimpleName();
	private static final String SEQUENCENAME_TASKGROUP = "S_TASKGROUP";
	private static final String SEQUENCENAME_TASKGROUP_RANK = "S_TASKGROUP_RANK";

	private Boolean isUpdated;

	public Boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	@Override
	public Taskgroup get(Long id) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.id=:id";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("id", id);
		Taskgroup taskgroup = findByQuerySingleResult(query);

		return taskgroup;
	}
	
	@Override
	public List<Taskgroup> getAll() {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e ORDER BY e.rank";
		Query query = getEntityManager().createQuery(strQuery);
		List<Taskgroup> taskgroups = findByQuery(query);

		LOG.info("Query: {} \nSize: {}", strQuery, taskgroups.size());
		return taskgroups;

	}

	@Override
	public List<Taskgroup> getByScenarioId(Scenario scenarioId) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.scenario.id=:scenarioId ORDER BY e.rank";
		Query query = getEntityManager().createQuery(strQuery);

		query.setParameter("scenarioId", scenarioId.getId());
		List<Taskgroup> taskgroups = findByQuery(query);

		int number = 1;
		for (Taskgroup taskgroup : (List<Taskgroup>)taskgroups) {
			taskgroup.setNumber(number);
			number++;
		}
		
		LOG.info("Query: {} \nSize: {}", strQuery, taskgroups.size());
		return taskgroups;
	}
	
	private void changeRank(Taskgroup taskClicked, Taskgroup taskAffected) {
		LOG.info("changeRank, tablename=" + ENTITYNAME + " model1=" + taskClicked + " model2=" + taskAffected);

		final String strQuery = "UPDATE " + ENTITYNAME + " SET rank = ?, datelastmod = ? WHERE id = ?";
		
		Query query = getEntityManager().createNativeQuery(strQuery);
		query.setParameter(1, taskAffected.getRank());
		query.setParameter(2, new Timestamp(DBUtil.getNow()));
		query.setParameter(3, taskClicked.getId());
		int resultTaskClicked = query.executeUpdate();
		
		Query query2 = getEntityManager().createNativeQuery(strQuery);
		query2.setParameter(1, taskClicked.getRank());
		query2.setParameter(2, new Timestamp(DBUtil.getNow()));
		query2.setParameter(3, taskAffected.getId());
		int resultTaskAffected = query2.executeUpdate();

		if(1 == resultTaskClicked && 1 == resultTaskAffected ) {
			LOG.info("resultTaskClicked: " + resultTaskClicked + " resultTaskAffected: " + resultTaskAffected);
			setIsUpdated(true);
		}
		
	}

	// http://stackoverflow.com/questions/3998064/how-to-use-em-merge-to-insert-or-update-for-jpa-entities-if-primary-key-is-gen
	private void rank(String strQuery, Taskgroup taskClicked) {
		Taskgroup taskAffected = new Taskgroup();
		
		Query query = getEntityManager().createNativeQuery(strQuery);
		query.setParameter(1, taskClicked.getRank());
		query.setParameter(2, taskClicked.getReferenceId());
		Object[] list = findByQuerySingleResult(query);
		
		if(null != list && null != list[0] && null != list[1]) {
			taskAffected.setId(Long.valueOf(list[0].toString()));
			taskAffected.setRank(Integer.valueOf(list[1].toString()));
			
			LOG.info("modelClicked:  rank=" + taskClicked.getRank() + ", id=" + taskClicked.getId());
			LOG.info("modelAffected: rank=" + taskAffected.getRank() + ", id=" + taskAffected.getId());
			
			changeRank(taskClicked, taskAffected);
		} else {
			setIsUpdated(false);
		}
	}

	@Override
	public Boolean rankUp(Taskgroup taskClicked) {
		setIsUpdated(false);
		String query = "SELECT id, rank FROM " + ENTITYNAME + " WHERE rank = " + "(SELECT MAX(rank) AS previous FROM "
				+ ENTITYNAME + " WHERE rank < ? AND " + taskClicked.getReferenceIdName() + " = ? )";

		rank(query, taskClicked);
		
		return getIsUpdated();
	}

	@Override
	public Boolean rankDown(Taskgroup taskClicked) {
		setIsUpdated(false);
		String query = "SELECT id, rank FROM " + ENTITYNAME + " WHERE rank = " + "(SELECT MIN(rank) AS nextrank FROM "
				+ ENTITYNAME + " WHERE rank > ? AND " + taskClicked.getReferenceIdName() + " = ? )";

		rank(query, taskClicked);
		
		return getIsUpdated();
	}
	
	@Override
	public void insert(Taskgroup taskgroup) {
		taskgroup.setId(generateNextId(SEQUENCENAME_TASKGROUP));
		taskgroup.setRank(generateNextId(SEQUENCENAME_TASKGROUP_RANK).intValue());
		taskgroup.setDateCreate(new Date());
		taskgroup.setDateLastMod(new Date());
		super.insertT(taskgroup);
	}

	@Override
	public Taskgroup update(Taskgroup taskgroup) {
		Taskgroup taskgroupTmp = this.get(taskgroup.getId());
		taskgroup.setRank(taskgroupTmp.getRank());
		taskgroup.setDateCreate(taskgroupTmp.getDateCreate());
		taskgroup.setDateLastMod(new Date());
		return super.updateT(taskgroup);
	}

	@Override
	public void delete(Taskgroup taskgroup) {
		super.deleteT(taskgroup);
	}

}
