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
package de.sqlcoach.beans;

import java.util.List;

import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBTaskService {
	public static final String BEANNAME = "TaskBean";
	public Task get(Long id);
	public List<Task> getAll();
	public List<Task> getByTaskgroupId(Taskgroup taskgroup);
	public List<Task> getByScenarioId(Long scenarioId);
	public void insert(Task task);
	public Task update(Task task);
	public void delete(Task task);
	public Boolean rankUp(Task task);
	public Boolean rankDown(Task task);
}
