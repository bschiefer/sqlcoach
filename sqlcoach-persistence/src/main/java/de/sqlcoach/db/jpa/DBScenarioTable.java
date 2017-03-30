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

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBScenarioTableService;
import de.sqlcoach.db.entities.ScenarioTable;

/**
 * Class extends methods to manipulate ScenarioTable Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBScenarioTable extends DBBase implements DBScenarioTableService {
	private static final Logger LOG = LoggerFactory.getLogger(DBScenarioTable.class);
	private static final String ENTITYNAME = ScenarioTable.class.getSimpleName();
	
	@Override
	public ScenarioTable get(Long scenarioId) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.scenario.id=:scenarioId";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("scenarioId", scenarioId);
		ScenarioTable scenarioTable = findByQuerySingleResult(query);

		return scenarioTable;
	}
	
	@Override
	public List<ScenarioTable> getAll() {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e ORDER BY e.scenario_table";
		Query query = getEntityManager().createQuery(strQuery);
		List<ScenarioTable> scenarioTables = findByQuery(query);

		LOG.info("Query: {} \nSize: {}", strQuery, scenarioTables.size());
		return scenarioTables;
	}

	@Override
	public List<ScenarioTable> getByScenarioId(Long scenarioId) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.scenario.id=:scenarioId ORDER BY e.scenario_table";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("scenarioId", scenarioId);
		List<ScenarioTable> scenarioTables = findByQuery(query);

		LOG.info("Query: {} \nSize: {}", strQuery, scenarioTables.size());
		return scenarioTables;
	}

	@Override
	public void insert(ScenarioTable scenarioTable) {
		scenarioTable.setDateCreate(new Date());
		scenarioTable.setDateLastMod(new Date());
		super.insertT(scenarioTable);
	}

	@Override
	public ScenarioTable update(ScenarioTable scenarioTable) {
		//TODO scenarioTable.getId().getId() zu scenarioTable.getScenario().getId()
		ScenarioTable scenarioTableTmp = this.get(scenarioTable.getScenario().getId());
		scenarioTable.setDateCreate(scenarioTableTmp.getDateCreate());
		scenarioTable.setDateLastMod(new Date());
		return super.updateT(scenarioTable);
	}

	@Override
	public void delete(ScenarioTable scenarioTable) {
		super.deleteT(scenarioTable);
	}
	
	@Override
	public void deleteByScenarioId(Long scenarioId) {
		String strQuery = "DELETE FROM " + ENTITYNAME + " e WHERE e.scenario.id=:scenarioId";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("scenarioId", scenarioId);
		query.executeUpdate();
	}

}
