package de.sqlcoach.beans;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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
	public List<Object[]> executeQuery(String sqlString, String jndiName);
	public ViewResultSet get(String query, String dataSourceName) throws SQLException;
	public ViewResultSet getExplainPlan(String query, String dataSourceName);
	public List<MetaTable> readAllTables(String dataSourceName);
	public List<MetaTable> readByScenarioTableCol(Collection<ScenarioTable> scenarioTableCol, String dataSourceName);
	public String getDatabaseProductName(String dataSourceName);
	public String getDatabaseProductVersion(String dataSourceName);
}
