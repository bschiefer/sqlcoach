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

import de.sqlcoach.beans.jdbc.interfaces.DBTaskgroupService;
import de.sqlcoach.db.jdbc.DBTaskgroup;
import de.sqlcoach.model.Taskgroup;

/**
 * Stateless Bean serves as interface between sqlcoach-integration-test-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBTaskgroupService.class)
public class TaskgroupBeanJDBC extends BaseBeanJDBC implements DBTaskgroupService {
	private static final Logger LOG = LoggerFactory.getLogger(TaskgroupBeanJDBC.class);

	@Override
	public Taskgroup get(String id) {
		Taskgroup taskgroup = null;
		try (Connection connection = getConnection()) {
			taskgroup =  DBTaskgroup.get(connection, id);
		} catch (SQLException e) {
			LOG.error("Taskgroup get id: " + e);
		}

		return taskgroup;
	}

	@Override
	public List<Taskgroup> getAll() {
		List<Taskgroup> taskgroups = null;
		try (Connection connection = getConnection()) {
			taskgroups =  DBTaskgroup.getAll(connection);
		} catch (SQLException e) {
			LOG.error("Taskgroup getAll: " + e);
		}

		return taskgroups;
	}

	@Override
	public List<Taskgroup> getByScenarioId(String id) {
		List<Taskgroup> taskgroups = null;
		try (Connection connection = getConnection()) {
			taskgroups =  DBTaskgroup.getByScenarioId(connection, id);
		} catch (SQLException e) {
			LOG.error("Taskgroup getByScenarioId: " + e);
		}

		return taskgroups;
	}

	@Override
	public int add(Taskgroup model) {
		int addResult = -2;
		try (Connection connection = getConnection()) {
			addResult =  DBTaskgroup.add(connection, model);
		} catch (SQLException e) {
			LOG.error("Taskgroup add: " + e);
		}

		return addResult;
	}

	@Override
	public int update(Taskgroup model) {
		int updateResult = -2;
		try (Connection connection = getConnection()) {
			updateResult =  DBTaskgroup.update(connection, model);
		} catch (SQLException e) {
			LOG.error("Taskgroup update: " + e);
		}

		return updateResult;
	}

	@Override
	public int delete(Taskgroup model) {
		int deleteResult = -2;
		try (Connection connection = getConnection()) {
			deleteResult =  DBTaskgroup.delete(connection, model);
		} catch (SQLException e) {
			LOG.error("Taskgroup delete: " + e);
		}

		return deleteResult;
	}

}
