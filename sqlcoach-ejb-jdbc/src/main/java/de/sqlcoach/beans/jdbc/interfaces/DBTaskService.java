package de.sqlcoach.beans.jdbc.interfaces;

import java.util.List;

import de.sqlcoach.model.Task;

/**
 * Local Bean serves as interface between sqlcoach-integration-test-modul and ejb-jdbc-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBTaskService {
	public Task get(String id);
	public List<Task> getAll();
	public List<Task> getByTaskgroupId(String id);
	public List<Task> getByScenarioId(String id);
	public int add(Task model);
	public int update(Task model);
	public int delete(Task model);
}
