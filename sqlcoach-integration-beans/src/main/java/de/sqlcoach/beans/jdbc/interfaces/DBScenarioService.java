package de.sqlcoach.beans.jdbc.interfaces;

import java.util.List;

import javax.ejb.Local;

import de.sqlcoach.model.Scenario;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Local
public interface DBScenarioService {
	public Scenario get(String id);
	public Scenario getByDescription(String description);
	public List<Scenario> getAll();
	public int add(Scenario model);
	public int update(Scenario model);
	public int delete(Scenario model);
}
