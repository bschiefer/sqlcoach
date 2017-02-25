package de.sqlcoach.beans;

import java.util.List;

import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Taskgroup;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBTaskgroupService {
	public static final String BEANNAME = "TaskgroupBean";
	public Taskgroup get(Long id);
	public List<Taskgroup> getAll();
	public List<Taskgroup> getByScenarioId(Scenario scenarioId);
	public void insert(Taskgroup taskgroup);
	public Taskgroup update(Taskgroup taskgroup);
	public void delete(Taskgroup taskgroup);
	public Boolean rankUp(Taskgroup taskClicked);
	public Boolean rankDown(Taskgroup taskClicked);
}
