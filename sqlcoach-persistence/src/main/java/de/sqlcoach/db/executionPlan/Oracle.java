/*******************************************************************************
 * This file is part of SQLCoach.
 *
 * SQLCoach is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SQLCoach is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.sqlcoach.db.executionPlan;

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
		// TODO resultset must close?
		ResultSet result = connection.createStatement().executeQuery(statement);
		Integer countResults = -1;

		while (result.next()) {
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

				String planTable = "select OPERATION, OPTIONS, OBJECT_NAME, OBJECT_INSTANCE, COST, CARDINALITY, BYTES, CPU_COST, IO_COST, ACCESS_PREDICATES from "
						+ PLAN_TABLE_NAME 
						+	" where PLAN_ID = (select max(PLAN_ID) from " + PLAN_TABLE_NAME + ")"; //last inserted row from plan table
				viewResultSet = execute(planTable, connection);
			} else if (0 == checkPlanTableExists(connection)) {
				LOG.error("create @$ORACLE_HOME/rdbms/admin/catplan.sql");
			} else {
				LOG.error("More than one result from: select table_name from user_tables where table_name = \'" + PLAN_TABLE_NAME
						+ "\'");
			}
		} catch (SQLException e) {
			LOG.error("SQLException: " + e);
		}

		return viewResultSet;
	}

	@Override
	public ViewResultSet execute(String query, Connection cn) {
		ViewResultSet viewResultSet = null;

		try {
			viewResultSet = DBViewResultSet.get(cn, query);
		} catch (SQLException e) {
			LOG.error("SQLException: " + e);
		}

		return viewResultSet;
	}

}
