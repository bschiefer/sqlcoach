package de.sqlcoach.beans.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService;
import de.sqlcoach.db.jdbc.DBScenarioTable;
import de.sqlcoach.model.ScenarioTable;

/**
 * Stateless Bean serves as interface between sqlcoach-integration-test-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBScenarioTableService.class)
public class ScenarioTableBeanJDBC extends BaseBeanJDBC implements DBScenarioTableService {
	private static final Logger LOG = LoggerFactory.getLogger(ScenarioBeanJDBC.class);

	@Override
	public ScenarioTable get(int id) {
		ScenarioTable scenarioTable = null;
		try (Connection connection = getConnection()) {
			scenarioTable = DBScenarioTable.get(connection, id);
		} catch (SQLException e) {
			LOG.error("ScenarioTable get id: " + e);
		}

		return scenarioTable;
	}

	@Override
	public List<ScenarioTable> getAll() {
		List<ScenarioTable> scenarioTables = null;
		try (Connection connection = getConnection()) {
			scenarioTables = DBScenarioTable.getAll(connection);
		} catch (SQLException e) {
			LOG.error("ScenarioTable getAll: " + e);
		}

		return scenarioTables;
	}

	@Override
	public List<ScenarioTable> getByScenarioId(String id) {
		List<ScenarioTable> scenarioTables = null;
		try (Connection connection = getConnection()) {
			scenarioTables = DBScenarioTable.getByScenarioId(connection, id);
		} catch (SQLException e) {
			LOG.error("ScenarioTable getByScenarioId: " + e);
		}

		return scenarioTables;
	}

	@Override
	public int add(ScenarioTable model) {
		int addResult = -2;
		try (Connection connection = getConnection()) {
			addResult = DBScenarioTable.add(connection, model);
		} catch (SQLException e) {
			LOG.error("ScenarioTable add: " + e);
		}

		return addResult;
	}

	@Override
	public int delete(String id) {
		int deleteResult = -2;
		try (Connection connection = getConnection()) {
			deleteResult = DBScenarioTable.delete(connection, id);
		} catch (SQLException e) {
			LOG.error("ScenarioTable add: " + e);
		}

		return deleteResult;
	}
}
