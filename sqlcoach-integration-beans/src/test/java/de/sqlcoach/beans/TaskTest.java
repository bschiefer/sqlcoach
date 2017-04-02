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

import de.sqlcoach.beans.DBTaskService;
import de.sqlcoach.db.entities.Task;

public class TaskTest {
	
	@Test
	public void test() {
		Task taskJPA = null;
		de.sqlcoach.model.Task taskJDBC = null;
		
		List<Task> tasksJPA = null;
		List<de.sqlcoach.model.Task> tasksJDBC = null;
		
		if(null != lookUpTaskJPA() && null != lookUpTaskJDBC()) {
			String id = "";
//			taskJPA = lookUpTaskJPA().get(id);
			taskJDBC = lookUpTaskJDBC().get(id);
			taskJDBC.toString().equals(taskJPA.toString());
			
			String scenarioId = "";
//			tasksJPA = lookUpTaskJPA().getByScenarioId(scenarioId);
			tasksJDBC = lookUpTaskJDBC().getByScenarioId(scenarioId);
			tasksJDBC.toString().equals(tasksJPA.toString());
			
			String taskgroupId ="";
//			tasksJPA = lookUpTaskJPA().getByTaskgroupId(taskgroupId);
			tasksJDBC = lookUpTaskJDBC().getByTaskgroupId(taskgroupId);
			tasksJDBC.toString().equals(tasksJPA.toString());
			
			tasksJPA = lookUpTaskJPA().getAll();
			tasksJDBC = lookUpTaskJDBC().getAll();
			tasksJDBC.toString().equals(tasksJPA.toString());
		}
	}
	
	private DBTaskService lookUpTaskJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBTaskService lookUpTaskJDBC() {
		return null;
	}

}
