package de.sqlcoach.beans.jdbc;

import java.util.List;

import javax.ejb.Stateless;

import de.sqlcoach.beans.jdbc.interfaces.DBScenarioService;
import de.sqlcoach.db.jdbc.DBScenario;
import de.sqlcoach.model.Scenario;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class ScenarioBeanJDBC extends BaseBeanJDBC implements DBScenarioService {

	@Override
	public Scenario get(String id) {
		return DBScenario.get(getConnection(), id);
	}

	@Override
	public Scenario getByDescription(String description) {
		return DBScenario.getByDescription(getConnection(), description);
	}

	@Override
	public List<Scenario> getAll() {
		return DBScenario.getAll(getConnection());
	}

	@Override
	public int add(Scenario model) {
		return DBScenario.add(getConnection(), model);
	}

	@Override
	public int update(Scenario model) {
		return DBScenario.update(getConnection(), model);
	}

	@Override
	public int delete(Scenario model) {
		return DBScenario.delete(getConnection(), model);
	}
}
