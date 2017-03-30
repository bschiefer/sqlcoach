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

import de.sqlcoach.beans.jdbc.ScenarioBeanJDBC;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

public class ScenarioTest {
	private DBScenarioService scenarioJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBScenarioService scenarioJDBC = null;

	public DBScenarioService getJPA() {
		if (null == scenarioJPA) {
			scenarioJPA = DBRemoteEJBClient.getEJB(DBScenarioService.class.getName(), DBScenarioService.BEANNAME, ModulName.JPA);
		}
		return scenarioJPA;
	}

	public de.sqlcoach.beans.jdbc.interfaces.DBScenarioService getJDBC() {
		if (null == scenarioJDBC) {
			scenarioJDBC = DBRemoteEJBClient.getEJB(de.sqlcoach.beans.jdbc.interfaces.DBScenarioService.class.getName(), ScenarioBeanJDBC.class.getSimpleName(), ModulName.JDBC);
		}
		return scenarioJDBC;
	}
	
	@Test
	public void testGetId() {
		List<Scenario> scenariosJPA = getJPA().getAll();
		Long id = scenariosJPA.get(0).getId();
		Scenario scenarioJPA = getJPA().get(id);
		de.sqlcoach.model.Scenario scenarioJDBC = getJDBC().get(id.toString());
		
		assertEquals(scenarioJPA.getId(), Long.valueOf(scenarioJDBC.getId()));
		assertEquals(scenarioJPA.getAppUser().getId(), Long.valueOf(scenarioJDBC.getAppUserId()));
		assertEquals(scenarioJPA.getAppUser().getFirstname(), scenarioJDBC.getAppUserFirstName());
		assertEquals(scenarioJPA.getAppUser().getLastname(), scenarioJDBC.getAppUserLastName());
		assertEquals(scenarioJPA.getAppUserName(), scenarioJDBC.getAppUserName());
		assertEquals(scenarioJPA.getAppUser().getTitle(), scenarioJDBC.getAppUserTitle());
		assertEquals(scenarioJPA.getDatasource(), scenarioJDBC.getDatasource());
		assertEquals(scenarioJPA.getDescription(), scenarioJDBC.getDescription());
		assertEquals(scenarioJPA.getDateCreate(), scenarioJDBC.getDatecreate());
		assertEquals(scenarioJPA.getDateLastMod(), scenarioJDBC.getDatelastmod());
	}
	
	@Test
	public void testGetByDescription() {
		List<Scenario> scenariosJPA = getJPA().getAll();
		String description = scenariosJPA.get(0).getDescription();
		Scenario scenarioJPA = getJPA().getByDescription(description);
		de.sqlcoach.model.Scenario scenarioJDBC = getJDBC().getByDescription(description);
		
		assertEquals(scenarioJPA.getId(), Long.valueOf(scenarioJDBC.getId()));
		assertEquals(scenarioJPA.getAppUser().getId(), Long.valueOf(scenarioJDBC.getAppUserId()));
		assertEquals(scenarioJPA.getAppUser().getFirstname(), scenarioJDBC.getAppUserFirstName());
		assertEquals(scenarioJPA.getAppUser().getLastname(), scenarioJDBC.getAppUserLastName());
		assertEquals(scenarioJPA.getAppUserName(), scenarioJDBC.getAppUserName());
		assertEquals(scenarioJPA.getAppUser().getTitle(), scenarioJDBC.getAppUserTitle());
		assertEquals(scenarioJPA.getDatasource(), scenarioJDBC.getDatasource());
		assertEquals(scenarioJPA.getDescription(), scenarioJDBC.getDescription());
		assertEquals(scenarioJPA.getDateCreate(), scenarioJDBC.getDatecreate());
		assertEquals(scenarioJPA.getDateLastMod(), scenarioJDBC.getDatelastmod());
	}
	
	@Test
	public void testGetAll() {
		List<Scenario> scenariosJPA = getJPA().getAll();
		List<de.sqlcoach.model.Scenario> scenariosJDBC = getJDBC().getAll();
		
		assertEquals(scenariosJPA.size(), scenariosJDBC.size());
	}
}
