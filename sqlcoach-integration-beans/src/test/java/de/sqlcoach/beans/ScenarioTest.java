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

import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.db.entities.Scenario;

public class ScenarioTest {
	
	@Test
	public void test() {
		Scenario scenarioJPA = null;
		de.sqlcoach.model.Scenario scenarioJDBC = null;
		
		List<Scenario> scenariosJPA = null;
		List<de.sqlcoach.model.Scenario> scenariosJDBC = null;
		
		if(null != lookUpScenarioJPA() && null != lookUpScenarioJDBC()) {
			String id = "";
//			scenarioJPA = lookUpScenarioJPA().get(id);
			scenarioJDBC = lookUpScenarioJDBC().get(id);
			scenarioJDBC.toString().equals(scenarioJPA.toString());
			
			String description = "";
			scenarioJPA = lookUpScenarioJPA().getByDescription(description);
			scenarioJDBC = lookUpScenarioJDBC().getByDescription(description);
			scenarioJDBC.toString().equals(scenarioJPA.toString());
			
			scenariosJPA = lookUpScenarioJPA().getAll();
			scenariosJDBC = lookUpScenarioJDBC().getAll();
			scenariosJDBC.toString().equals(scenariosJPA.toString());
		}
	}
	
	private DBScenarioService lookUpScenarioJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBScenarioService lookUpScenarioJDBC() {
		return null;
	}

}
