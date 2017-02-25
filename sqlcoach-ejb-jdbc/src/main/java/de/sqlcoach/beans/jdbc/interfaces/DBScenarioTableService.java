package de.sqlcoach.beans.jdbc.interfaces;

import java.util.List;

import de.sqlcoach.model.ScenarioTable;

/**
 * Local Bean serves as interface between sqlcoach-integration-test-modul and ejb-jdbc-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBScenarioTableService {
	public ScenarioTable get(int id);
	public List<ScenarioTable> getAll();
	public List<ScenarioTable> getByScenarioId(String id);
	public int add(ScenarioTable model);
	public int delete(String id);
}
