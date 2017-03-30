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

import de.sqlcoach.db.entities.ScenarioTable;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBScenarioTableService {
	public static final String BEANNAME = "ScenarioTableBean";
	public ScenarioTable get(Long scenarioId);
	public List<ScenarioTable> getAll();
	public List<ScenarioTable> getByScenarioId(Long scenarioId);
	public void insert(ScenarioTable scenarioTable);
	public ScenarioTable update(ScenarioTable scenarioTable);
	public void delete(ScenarioTable scenarioTable);
	public void deleteByScenarioId(Long scenarioId);
}
