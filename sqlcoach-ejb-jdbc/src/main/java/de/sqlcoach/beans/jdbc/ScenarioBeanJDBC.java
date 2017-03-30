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
