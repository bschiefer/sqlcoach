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

import de.sqlcoach.beans.jdbc.interfaces.DBTaskgroupService;
import de.sqlcoach.db.jdbc.DBTaskgroup;
import de.sqlcoach.model.Taskgroup;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class TaskgroupBeanJDBC extends BaseBeanJDBC implements DBTaskgroupService {

	@Override
	public Taskgroup get(String id) {
		return DBTaskgroup.get(getConnection(), id);
	}

	@Override
	public List<Taskgroup> getAll() {
		return DBTaskgroup.getAll(getConnection());
	}

	@Override
	public List<Taskgroup> getByScenarioId(String id) {
		return DBTaskgroup.getByScenarioId(getConnection(), id);
	}

	@Override
	public int add(Taskgroup model) {
		return DBTaskgroup.add(getConnection(), model);
	}

	@Override
	public int update(Taskgroup model) {
		return DBTaskgroup.update(getConnection(), model);
	}

	@Override
	public int delete(Taskgroup model) {
		return DBTaskgroup.delete(getConnection(), model);
	}

}
