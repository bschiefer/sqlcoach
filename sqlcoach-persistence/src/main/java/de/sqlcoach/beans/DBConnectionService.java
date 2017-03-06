package de.sqlcoach.beans;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.util.MetaTable;
import de.sqlcoach.util.ViewResultSet;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBConnectionService {
	public static final String BEANNAME = "DBConnectionBean";
	public void testConnection(Scenario scenario);
	public List<Object[]> executeQuery(String sqlString, Scenario scenario);
	public ViewResultSet get(String query, Scenario scenario) throws SQLException;
	public ViewResultSet getExplainPlan(String query, Scenario scenario);
	public List<MetaTable> readAllTables(Scenario scenario);
	public List<MetaTable> readByScenarioTableCol(Collection<ScenarioTable> scenarioTableCol, Scenario scenario);
	public String getDatabaseProductName(Scenario scenario);
	public String getDatabaseProductVersion(Scenario scenario);
}
