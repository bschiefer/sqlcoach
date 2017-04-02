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

import org.junit.Test;

import de.sqlcoach.beans.DBScenarioTableService;
import de.sqlcoach.db.entities.ScenarioTable;

public class ScenarioTableTest {
	
	@Test
	public void test() {
		ScenarioTable scenarioTableJPA = null;
		de.sqlcoach.model.ScenarioTable scenarioTableJDBC = null;
		
		List<ScenarioTable> scenarioTablesJPA = null;
		List<de.sqlcoach.model.ScenarioTable> scenarioTablesJDBC = null;
		
		if(null != lookUpScenarioTableJPA() && null != lookUpScenarioTableJDBC()) {
//			scenarioTableJPA = lookUpScenarioTableJPA().get(0);
			scenarioTableJDBC = lookUpScenarioTableJDBC().get(0);
			scenarioTableJDBC.toString().equals(scenarioTableJPA.toString());
			
			String id = "";
//			scenarioTablesJPA = lookUpScenarioTableJPA().getByScenarioId(id);
			scenarioTablesJDBC = lookUpScenarioTableJDBC().getByScenarioId(id);
			scenarioTablesJDBC.toString().equals(scenarioTablesJPA.toString());
			
			scenarioTablesJPA = lookUpScenarioTableJPA().getAll();
			scenarioTablesJDBC = lookUpScenarioTableJDBC().getAll();
			scenarioTablesJDBC.toString().equals(scenarioTablesJPA.toString());
		}
	}
	
	private DBScenarioTableService lookUpScenarioTableJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService lookUpScenarioTableJDBC() {
		return null;
	}

}
