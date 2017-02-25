package de.sqlcoach.beans.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.jdbc.interfaces.DBScenarioService;
import de.sqlcoach.db.jdbc.DBScenario;
import de.sqlcoach.model.Scenario;

/**
 * Stateless Bean serves as interface between sqlcoach-integration-test-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBScenarioService.class)
public class ScenarioBeanJDBC extends BaseBeanJDBC implements DBScenarioService {
	private static final Logger LOG = LoggerFactory.getLogger(ScenarioBeanJDBC.class);

	@Override
	public Scenario get(String id) {
		Scenario scenario = null;
		try (Connection connection = getConnection()) {
			scenario = DBScenario.get(connection, id);
		} catch (SQLException e) {
			LOG.error("Scenario get id: " + e);
		}

		return scenario;
	}

	@Override
	public Scenario getByDescription(String description) {
		Scenario scenario = null;
		try (Connection connection = getConnection()) {
			scenario = DBScenario.getByDescription(connection, description);
		} catch (SQLException e) {
			LOG.error("Scenario getByDescription: " + e);
		}

		return scenario;
	}

	@Override
	public List<Scenario> getAll() {
		List<Scenario> scenarios = null;
		try (Connection connection = getConnection()) {
			scenarios = DBScenario.getAll(getConnection());
		} catch (SQLException e) {
			LOG.error("Scenario getAll: " + e);
		}

		return scenarios;
	}

	@Override
	public int add(Scenario model) {
		int addResult = -2;
		try (Connection connection = getConnection()) {
			addResult = DBScenario.add(connection, model);
		} catch (SQLException e) {
			LOG.error("Scenario add: " + e);
		}

		return addResult;
	}

	@Override
	public int update(Scenario model) {
		int updateResult = -2;
		try (Connection connection = getConnection()) {
			updateResult = DBScenario.update(connection, model);
		} catch (SQLException e) {
			LOG.error("Scenario update: " + e);
		}

		return updateResult;
	}

	@Override
	public int delete(Scenario model) {
		int deleteResult = -2;
		try (Connection connection = getConnection()) {
			deleteResult = DBScenario.delete(connection, model);
		} catch (SQLException e) {
			LOG.error("Scenario delete: " + e);
		}

		return deleteResult;
	}
}
