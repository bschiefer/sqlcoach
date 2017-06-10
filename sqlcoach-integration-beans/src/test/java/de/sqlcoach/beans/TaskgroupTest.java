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

import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.Taskgroup;

public class TaskgroupTest {
	
	@Test
	public void test() {
		Taskgroup taskgroupJPA = null;
		de.sqlcoach.model.Taskgroup taskgroupJDBC = null;
		
		List<Taskgroup> taskgroupsJPA = null;
		List<de.sqlcoach.model.Taskgroup> taskgroupsJDBC = null;
		
		if(null != lookUpTaskgroupJPA() && null != lookUpTaskgroupJDBC()) {
			String id = "";
			taskgroupJPA = lookUpTaskgroupJPA().get(Long.valueOf(id));
			taskgroupJDBC = lookUpTaskgroupJDBC().get(id);
			taskgroupJDBC.toString().equals(taskgroupJPA.toString());
			
			id = "";
//			taskgroupsJPA = lookUpTaskgroupJPA().getByScenarioId(id);
			taskgroupsJDBC = lookUpTaskgroupJDBC().getByScenarioId(id);
			taskgroupsJDBC.toString().equals(taskgroupsJPA.toString());
		}
	}
	
	private DBTaskgroupService lookUpTaskgroupJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBTaskgroupService lookUpTaskgroupJDBC() {
		return null;
	}

}
