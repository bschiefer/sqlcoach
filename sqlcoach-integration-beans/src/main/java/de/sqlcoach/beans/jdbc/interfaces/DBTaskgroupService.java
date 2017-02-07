package de.sqlcoach.beans.jdbc.interfaces;

import java.util.List;

import javax.ejb.Local;

import de.sqlcoach.model.Taskgroup;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Local
public interface DBTaskgroupService {
	public Taskgroup get(String id);
	public List<Taskgroup> getAll();
	public List<Taskgroup> getByScenarioId(String id);
	public int add(Taskgroup model);
	public int update(Taskgroup model);
	public int delete(Taskgroup model);
}
