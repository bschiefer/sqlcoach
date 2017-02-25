package de.sqlcoach.beans;

import java.util.List;

import de.sqlcoach.db.entities.ScenarioTable;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBScenarioTableService {
	public static final String BEANNAME = "ScenarioTableBean";
	public ScenarioTable get(Long scenarioId);
	public List<ScenarioTable> getAll();
	public List<ScenarioTable> getByScenarioId(Long scenarioId);
	public void insert(ScenarioTable scenarioTable);
	public ScenarioTable update(ScenarioTable scenarioTable);
	public void delete(ScenarioTable scenarioTable);
	public void deleteByScenarioId(Long scenarioId);
}
