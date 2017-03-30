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
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.jdbc.TaskBeanJDBC;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

public class TaskTest {
	private DBTaskService taskJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBTaskService taskJDBC = null;

	public DBTaskService getJPA() {
		if (null == taskJPA) {
			taskJPA = DBRemoteEJBClient.getEJB(DBTaskService.class.getName(), DBTaskService.BEANNAME, ModulName.JPA);
		}
		return taskJPA;
	}

	public de.sqlcoach.beans.jdbc.interfaces.DBTaskService getJDBC() {
		if (null == taskJDBC) {
			taskJDBC = DBRemoteEJBClient.getEJB(de.sqlcoach.beans.jdbc.interfaces.DBTaskService.class.getName(), TaskBeanJDBC.class.getSimpleName(), ModulName.JDBC);
		}
		return taskJDBC;
	}
	
	@Test
	public void testGetId() {
		List<Task> tasksJPA = getJPA().getAll();
		Long id = tasksJPA.get(0).getId();
		Task taskJPA = getJPA().get(id);
		de.sqlcoach.model.Task taskJDBC = getJDBC().get(id.toString());
		
		int number = 0;
		if(null != taskJPA.getNumber()) {
			number = taskJPA.getNumber().intValue();
		}
		
		int failedQueries = 0;
		if(null != taskJPA.getFailedQueries()) {
			failedQueries = taskJPA.getFailedQueries().intValue();
		}
		
		int hintTrials = 0;
		if(null != taskJPA.getHint_trials()) {
			hintTrials = taskJPA.getHint_trials().intValue();
		}
		
		int solutionTrials = 0;
		if(null != taskJPA.getSolution_trials()) {
			solutionTrials = taskJPA.getSolution_trials().intValue();
		}
		
		int successQueries = 0;
		if(null != taskJPA.getSuccessQueries()) {
			successQueries = taskJPA.getSuccessQueries().intValue();
		}
		
		assertEquals(taskJPA.getId(), Long.valueOf(taskJDBC.getId()));
		assertEquals(number, taskJDBC.getNumber());
		assertEquals(taskJPA.getRank().intValue(), taskJDBC.getRank());
		assertEquals(taskJPA.getReferenceId(), Long.valueOf(taskJDBC.getReferenceId()));
		assertEquals(taskJPA.getReferenceIdName(), taskJDBC.getReferenceIdName());
		assertEquals(taskJPA.getDescription(), taskJDBC.getDescription());
		assertEquals(taskJPA.getDateCreate(), taskJDBC.getDatecreate());
		assertEquals(taskJPA.getDateLastMod(), taskJDBC.getDatelastmod());
		assertEquals(failedQueries, taskJDBC.getFailedQueries());
		assertEquals(taskJPA.getHint(), taskJDBC.getHint());
		assertEquals(hintTrials, taskJDBC.getHint_trials());
		assertEquals(taskJPA.getQuery(), taskJDBC.getQuery());
		assertEquals(solutionTrials, taskJDBC.getSolution_trials());
		assertEquals(successQueries, taskJDBC.getSuccessQueries());
	}
	
	@Test
	public void testGetByScenarioId() {
		Long id = getJPA().getAll().get(0).getTaskgroup().getScenario().getId();
		List<Task> tasksJPA = getJPA().getByScenarioId(id);
		List<de.sqlcoach.model.Task> tasksJDBC = getJDBC().getByScenarioId(id.toString());
		
		assertEquals(tasksJPA.size(), tasksJDBC.size());
	}
	
	@Test
	public void testGetByTaskgroupId() {
		Taskgroup taskgroup = getJPA().getAll().get(0).getTaskgroup();
		List<Task> tasksJPA = getJPA().getByTaskgroupId(taskgroup);
		List<de.sqlcoach.model.Task> tasksJDBC = getJDBC().getByTaskgroupId(taskgroup.getId().toString());
		
		assertEquals(tasksJPA.size(), tasksJDBC.size());
	}
	
	@Test
	public void testGetAll() {
		List<Task> tasksJPA = getJPA().getAll();
		List<de.sqlcoach.model.Task> tasksJDBC = getJDBC().getAll();
		
		assertEquals(tasksJPA.size(), tasksJDBC.size());
	}
}
