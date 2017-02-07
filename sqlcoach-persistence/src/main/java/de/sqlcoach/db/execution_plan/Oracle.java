package de.sqlcoach.db.execution_plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.util.DBViewResultSet;
import de.sqlcoach.util.ViewResultSet;

/**
 * Oracle class create explain and execute statement for database Oracle  
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class Oracle extends Database {

	private static final Logger LOG = LoggerFactory.getLogger(Oracle.class);
	private static final String PLAN_TABLE_NAME = "PLAN_TABLE$";

	public Oracle() {
		LOG.debug("enter Oracle");
	}

	/**
	 * http://www.is.informatik.uni-duisburg.de/courses/dortmund/lectures/is_ws01-02/Plan.java
	 * http://wiki.hsr.ch/Datenbanken/N%C3%BCtzlicheOracleQueries
	 * http://docs.oracle.com/cd/E25178_01/server.1111/e16638/ex_plan.htm#i21501
	 * http://use-the-index-luke.com/de/sql/ausfuehrungsplaene/oracle/explain-plan-for-erstellen
	 * 
	 * @param connection
	 * @return
	 * @throws SQLException
	 */

	private Integer checkPlanTableExists(Connection connection) throws SQLException {
		String statement = "select count(table_name) from user_tables where table_name = \'" + PLAN_TABLE_NAME + "\'";
		//TODO resultset must close?
		ResultSet result = connection.createStatement().executeQuery(statement);
		Integer countResults = -1;
		
		while(result.next()) {
			countResults = result.getInt(1);
			break;
		}
		
		LOG.info("MPA result count Plantables: " + countResults);
		return countResults;
	}

	@Override
	public ViewResultSet explain(String query, Connection connection) {
		ViewResultSet viewResultSet = null;
		
		try {
			if (1 == checkPlanTableExists(connection)) {
				Statement statement = connection.createStatement();
				statement.execute("explain plan for " + query);
				
				String planTable = "select * from " + PLAN_TABLE_NAME;
				viewResultSet = execute(planTable, connection);
			} else if (0 == checkPlanTableExists(connection)) {
				//TODO create @$ORACLE_HOME/rdbms/admin/catplan.sql
				LOG.info("create @$ORACLE_HOME/rdbms/admin/catplan.sql");
			} else {
				//TODO More than one result from: 
				LOG.info("More than one result from: select table_name from user_tables where table_name = \'" + PLAN_TABLE_NAME + "\'");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return viewResultSet;
	}

	@Override
	public ViewResultSet execute(String query, Connection cn) {
		ViewResultSet viewResultSet = null;

		try {
			viewResultSet = DBViewResultSet.get(cn, query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return viewResultSet;
	}

}
