package de.sqlcoach.beans.jdbc.interfaces;

import java.util.List;

import de.sqlcoach.model.Taskgroup;

/**
 * Local Bean serves as interface between sqlcoach-integration-test-modul and ejb-jdbc-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBTaskgroupService {
	public Taskgroup get(String id);
	public List<Taskgroup> getAll();
	public List<Taskgroup> getByScenarioId(String id);
	public int add(Taskgroup model);
	public int update(Taskgroup model);
	public int delete(Taskgroup model);
}
