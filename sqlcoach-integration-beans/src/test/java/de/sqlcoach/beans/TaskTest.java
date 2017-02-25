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
