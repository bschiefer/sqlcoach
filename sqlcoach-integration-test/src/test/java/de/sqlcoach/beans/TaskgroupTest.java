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

import de.sqlcoach.beans.jdbc.TaskgroupBeanJDBC;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

public class TaskgroupTest {
	private DBTaskgroupService taskgroupJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBTaskgroupService taskgroupJDBC = null;

	public DBTaskgroupService getJPA() {
		if (null == taskgroupJPA) {
			taskgroupJPA = DBRemoteEJBClient.getEJB(DBTaskgroupService.class.getName(), DBTaskgroupService.BEANNAME, ModulName.JPA);
		}
		return taskgroupJPA;
	}

	public de.sqlcoach.beans.jdbc.interfaces.DBTaskgroupService getJDBC() {
		if (null == taskgroupJDBC) {
			taskgroupJDBC = DBRemoteEJBClient.getEJB(de.sqlcoach.beans.jdbc.interfaces.DBTaskgroupService.class.getName(), TaskgroupBeanJDBC.class.getSimpleName(), ModulName.JDBC);
		}
		return taskgroupJDBC;
	}
	
	@Test
	public void testGetId() {
		List<Taskgroup> taskgroupsJPA = getJPA().getAll();
		Long id = taskgroupsJPA.get(0).getId();
		Taskgroup taskgroupJPA = getJPA().get(id);
		System.out.println("id.toString(): " + id.toString());
		de.sqlcoach.model.Taskgroup taskgroupJDBC = getJDBC().get(id.toString());
		
		int number = 0;
		if(null != taskgroupJPA.getNumber()) {
			number = taskgroupJPA.getNumber().intValue();
		}
		
		assertEquals(taskgroupJPA.getId(), Long.valueOf(taskgroupJDBC.getId()));
		assertEquals(number, taskgroupJDBC.getNumber());
		assertEquals(taskgroupJPA.getRank().intValue(), taskgroupJDBC.getRank());
		assertEquals(taskgroupJPA.getReferenceId(), Long.valueOf(taskgroupJDBC.getReferenceId()));
		assertEquals(taskgroupJPA.getReferenceIdName(), taskgroupJDBC.getReferenceIdName());
		assertEquals(taskgroupJPA.getScenario().getId().toString(), taskgroupJDBC.getScenarioId());
		assertEquals(taskgroupJPA.getDescription(), taskgroupJDBC.getDescription());
		assertEquals(taskgroupJPA.getDateCreate(), taskgroupJDBC.getDatecreate());
		assertEquals(taskgroupJPA.getDateLastMod(), taskgroupJDBC.getDatelastmod());
	}
	
	@Test
	public void testGetByScenarioId() {
		Scenario scenario = getJPA().getAll().get(0).getScenario();
		List<Taskgroup> taskgroupsJPA = getJPA().getByScenarioId(scenario);
		System.out.println("scenario.getId().toString(): " + scenario.getId().toString());
		List<de.sqlcoach.model.Taskgroup> taskgroupsJDBC = getJDBC().getByScenarioId(scenario.getId().toString());
		
		assertEquals(taskgroupsJPA.size(), taskgroupsJDBC.size());
	}
	
	@Test
	public void testGetAll() {
		List<Taskgroup> taskgroupsJPA = getJPA().getAll();
		List<de.sqlcoach.model.Taskgroup> taskgroupsJDBC = getJDBC().getAll();
		
		assertEquals(taskgroupsJPA.size(), taskgroupsJDBC.size());
	}
}
