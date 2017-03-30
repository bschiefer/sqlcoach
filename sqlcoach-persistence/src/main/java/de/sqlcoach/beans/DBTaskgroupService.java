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

import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Taskgroup;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBTaskgroupService {
	public static final String BEANNAME = "TaskgroupBean";
	public Taskgroup get(Long id);
	public List<Taskgroup> getAll();
	public List<Taskgroup> getByScenarioId(Scenario scenarioId);
	public void insert(Taskgroup taskgroup);
	public Taskgroup update(Taskgroup taskgroup);
	public void delete(Taskgroup taskgroup);
	public Boolean rankUp(Taskgroup taskClicked);
	public Boolean rankDown(Taskgroup taskClicked);
}
