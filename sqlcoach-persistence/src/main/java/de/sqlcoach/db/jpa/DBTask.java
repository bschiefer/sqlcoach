package de.sqlcoach.db.jpa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBTaskService;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.util.DBUtil;

 /**
 * Class extends methods to manipulate TaskService Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBTask extends DBBase implements DBTaskService {
	private static final Logger LOG = LoggerFactory.getLogger(DBTask.class);
	private static final String ENTITYNAME = Task.class.getSimpleName();
	private static final String SEQUENCENAME_TASK = "S_TASK";
	private static final String SEQUENCENAME_TASK_RANK = "S_TASK_RANK";
	
	private Boolean isUpdated;

	public Boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

	@Override
	public Task get(Long id) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.id=:id";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("id", id);
		Task task = findByQuerySingleResult(query);

		return task;
	}

	@Override
	public List<Task> getAll() {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e ORDER BY e.rank";
		Query query = getEntityManager().createQuery(strQuery);
		List<Task> tasks = findByQuery(query);

		LOG.info("Query: {} \nSize: {}", strQuery, tasks.size());
		return tasks;
	}

	@Override
	public List<Task> getByTaskgroupId(Taskgroup taskgroup) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.taskgroup.id=:taskgroupId ORDER BY e.rank";
		TypedQuery<Task> query = getEntityManager().createQuery(strQuery, Task.class);

		query.setParameter("taskgroupId", taskgroup.getId());
		List<Task> tasks = findByQuery(query);

		int number = 1;
		for (Task taskTmp : tasks) {
			taskTmp.setNumber(number);
			number++;
		}

		LOG.info("Query: {} \nSize: {}", strQuery, tasks.size());
		return tasks;
	}

	@Override
	public List<Task> getByScenarioId(Long scenarioId) {
		String strQuery = "SELECT e FROM " + ENTITYNAME
				+ " e WHERE e.taskgroup.scenario.id=:scenarioId ORDER BY e.rank";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("scenarioId", scenarioId);
		List<Task> tasks = findByQuery(query);

		return tasks;
	}

	private void changeRank(Task taskClicked, Task taskAffected) {
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
	private void rank(String strQuery, Task taskClicked) {
		Task taskAffected = new Task();
		
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
	public Boolean rankUp(Task taskClicked) {
		setIsUpdated(false);
		String query = "SELECT id, rank FROM " + ENTITYNAME + " WHERE rank = " + "(SELECT MAX(rank) AS previous FROM "
				+ ENTITYNAME + " WHERE rank < ? AND " + taskClicked.getReferenceIdName() + " = ? )";

		rank(query, taskClicked);
		
		return getIsUpdated();
	}

	@Override
	public Boolean rankDown(Task taskClicked) {
		setIsUpdated(false);
		String query = "SELECT id, rank FROM " + ENTITYNAME + " WHERE rank = " + "(SELECT MIN(rank) AS nextrank FROM "
				+ ENTITYNAME + " WHERE rank > ? AND " + taskClicked.getReferenceIdName() + " = ? )";

		rank(query, taskClicked);
		
		return getIsUpdated();
	}

	@Override
	public void insert(Task task) {
		task.setId(generateNextId(SEQUENCENAME_TASK));
		task.setRank(generateNextId(SEQUENCENAME_TASK_RANK).intValue());
		task.setDateCreate(new Date());
		task.setDateLastMod(new Date());
		super.insertT(task);
	}

	@Override
	public Task update(Task task) {
		Task taskTmp = this.get(task.getId());
		task.setRank(taskTmp.getRank());
		task.setDateCreate(taskTmp.getDateCreate());
		task.setDateLastMod(new Date());
		return super.updateT(task);
	}

	@Override
	public void delete(Task task) {
		super.deleteT(task);
	}

}
