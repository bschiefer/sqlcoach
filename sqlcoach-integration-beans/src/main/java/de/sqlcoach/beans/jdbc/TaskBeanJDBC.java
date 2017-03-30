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

import java.util.List;

import javax.ejb.Stateless;

import de.sqlcoach.beans.jdbc.interfaces.DBTaskService;
import de.sqlcoach.db.jdbc.DBTask;
import de.sqlcoach.model.Task;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class TaskBeanJDBC extends BaseBeanJDBC implements DBTaskService {

	@Override
	public Task get(String id) {
		return DBTask.get(getConnection(), id);
	}

	@Override
	public List<Task> getAll() {
		return DBTask.getAll(getConnection());
	}

	@Override
	public List<Task> getByTaskgroupId(String id) {
		return DBTask.getByTaskgroupId(getConnection(), id);
	}

	@Override
	public List<Task> getByScenarioId(String id) {
		return DBTask.getByScenarioId(getConnection(), id);
	}

	@Override
	public int add(Task model) {
		return DBTask.add(getConnection(), model);
	}

	@Override
	public int update(Task model) {
		return DBTask.update(getConnection(), model);
	}

	@Override
	public int delete(Task model) {
		return DBTask.delete(getConnection(), model);
	}
}
