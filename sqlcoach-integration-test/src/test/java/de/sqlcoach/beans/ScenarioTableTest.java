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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.jdbc.ScenarioTableBeanJDBC;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

public class ScenarioTableTest {
	private DBScenarioTableService scenarioTableJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService scenarioTableJDBC = null;
	
	public DBScenarioTableService getJPA() {
		if (null == scenarioTableJPA) {
			scenarioTableJPA = DBRemoteEJBClient.getEJB(DBScenarioTableService.class.getName(),
					DBScenarioTableService.BEANNAME, ModulName.JPA);
		}
		return scenarioTableJPA;
	}
	
	public de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService getJDBC() {
		if (null == scenarioTableJDBC) {
			scenarioTableJDBC = DBRemoteEJBClient.getEJB(
					de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService.class.getName(),
					ScenarioTableBeanJDBC.class.getSimpleName(), ModulName.JDBC);
		}
		return scenarioTableJDBC;
	}
	
	/**
	 * scenarioTable get() are never called for old and new state of project
	 */
	@Test
	public void testGet() {
		// List<ScenarioTable> scenarioTables = getJPA().getAll();
		// Long id = scenarioTables.get(0).getScenario().getId();
		// ScenarioTable scenarioTableJPA = getJPA().get(id);
		// de.sqlcoach.model.ScenarioTable scenarioTableJDBC =
		// getJDBC().get(id.intValue());
		//
		// assertEquals(scenarioTableJPA.getDateCreate(),
		// scenarioTableJDBC.getDatecreate());
		// assertEquals(scenarioTableJPA.getDateLastMod(),
		// scenarioTableJDBC.getDatelastmod());
		// assertEquals(scenarioTableJPA.getScenario().getId(),
		// Long.valueOf(scenarioTableJDBC.getScenarioId()));
		// assertEquals(scenarioTableJPA.getScenarioTable(),
		// scenarioTableJDBC.getScenarioTable());
	}
	
	@Test
	public void testGetByScenarioId() {
		List<ScenarioTable> scenarioTables = getJPA().getAll();
		Long id = scenarioTables.get(0).getScenario().getId();
		List<ScenarioTable> scenarioTablesJPA = getJPA().getByScenarioId(id);
		List<de.sqlcoach.model.ScenarioTable> scenarioTablesJDBC = getJDBC().getByScenarioId(id.toString());
		
		assertEquals(scenarioTablesJPA.size(), scenarioTablesJDBC.size());
	}
	
	@Test
	public void testGetAll() {
		List<ScenarioTable> scenarioTablesJPA = getJPA().getAll();
		List<de.sqlcoach.model.ScenarioTable> scenarioTablesJDBC = getJDBC().getAll();
		
		assertEquals(scenarioTablesJPA.size(), scenarioTablesJDBC.size());
	}
}
