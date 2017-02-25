package de.sqlcoach.beans;

import java.util.List;

import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBTaskService {
	public static final String BEANNAME = "TaskBean";
	public Task get(Long id);
	public List<Task> getAll();
	public List<Task> getByTaskgroupId(Taskgroup taskgroup);
	public List<Task> getByScenarioId(Long scenarioId);
	public void insert(Task task);
	public Task update(Task task);
	public void delete(Task task);
	public Boolean rankUp(Task task);
	public Boolean rankDown(Task task);
}
