package de.sqlcoach.beans;

import java.util.List;

import de.sqlcoach.db.entities.Scenario;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBScenarioService {
	public static final String BEANNAME = "ScenarioBean";
	public Scenario get(Long id);
	public Scenario getByDescription(String description);
	public List<Scenario> getAll();
	public void insert(Scenario scenario);
	public Scenario update(Scenario scenario);
	public void delete(Scenario scenario);
}
