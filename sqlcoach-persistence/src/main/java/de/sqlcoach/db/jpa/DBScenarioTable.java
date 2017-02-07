package de.sqlcoach.db.jpa;

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
		super.insertT(scenarioTable);
	}

	@Override
	public ScenarioTable update(ScenarioTable scenarioTable) {
		return super.updateT(scenarioTable);
	}

	@Override
	public void delete(ScenarioTable scenarioTable) {
		super.deleteT(scenarioTable);
	}
}
