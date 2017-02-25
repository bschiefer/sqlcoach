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
			taskgroupJPA = lookUpTaskgroupJPA().get(id);
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
