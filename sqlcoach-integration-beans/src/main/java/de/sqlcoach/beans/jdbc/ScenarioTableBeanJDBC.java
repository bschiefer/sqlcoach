package de.sqlcoach.beans.jdbc;

import java.util.List;

import javax.ejb.Stateless;

import de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService;
import de.sqlcoach.db.jdbc.DBScenarioTable;
import de.sqlcoach.model.ScenarioTable;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class ScenarioTableBeanJDBC extends BaseBeanJDBC implements DBScenarioTableService {

	@Override
	public ScenarioTable get(int id) {
		return DBScenarioTable.get(getConnection(), id);
	}

	@Override
	public List<ScenarioTable> getAll() {
		return DBScenarioTable.getAll(getConnection());
	}

	@Override
	public List<ScenarioTable> getByScenarioId(String id) {
		return DBScenarioTable.getByScenarioId(getConnection(), id);
	}

	@Override
	public int add(ScenarioTable model) {
		return DBScenarioTable.add(getConnection(), model);
	}

	@Override
	public int delete(String id) {
		return DBScenarioTable.delete(getConnection(), id);
	}
}
