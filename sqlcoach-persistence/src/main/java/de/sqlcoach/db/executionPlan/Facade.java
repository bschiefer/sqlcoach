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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.util.ViewResultSet;

/**
 * Facade encapsulate different database type classes and get explain plan for created query.
 * Supported databases are: Sap DB, Oracle, MySQL and PostgreSQL 
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class Facade {

	private static final Logger LOG = LoggerFactory.getLogger(Facade.class);

	public Facade() {
		LOG.debug("enter Facade");
	}

	/**
	 * Get selected database scenario database type name, to create relevant database type class.
	 * 
	 * @param dataBaseProductName
	 * @return Database
	 * 
	 * @see http://www.programcreek.com/java-api-examples/index.php?api=java.sql.DatabaseMetaData
	 */
	protected Database createDatabasebyKey(String dataBaseProductName) {
		Database database;		

		switch (dataBaseProductName.toUpperCase()) {
		case "SAP DB":
			database = new MaxDB();
			break;
		case "ORACLE":
			database = new Oracle();
			break;
		case "POSTGRESQL":
			database = new PostgreSQL();
			break;
		case "MYSQL":
			database = new MySQL();
			break;
		default:
			database = null;
			break;
		}
		
		return database;
	}

	/**
	 * Create explain plan for trainee query
	 * 
	 * @param cn
	 * @param query
	 * @param dataBaseProductName
	 * @return ViewResultSet
	 */
	public ViewResultSet execute(Connection cn, String query, String dataBaseProductName) {
		ViewResultSet viewResultSet = null;
		viewResultSet = createDatabasebyKey(dataBaseProductName).execute(query, cn);
		
		return viewResultSet;
	}

	/**
	 * Show explain plan for trainee query  
	 * 
	 * @param cn
	 * @param query
	 * @param dataBaseProductName
	 * @return ViewResultSet
	 */
	public ViewResultSet explain(Connection cn, String query, String dataBaseProductName) {
		ViewResultSet viewResultSet = null;
		viewResultSet = createDatabasebyKey(dataBaseProductName).explain(query, cn);
		
		return viewResultSet;
	}
}
