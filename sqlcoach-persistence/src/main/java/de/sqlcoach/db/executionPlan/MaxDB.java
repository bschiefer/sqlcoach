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
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.util.DBViewResultSet;
import de.sqlcoach.util.ViewResultSet;

/**
 * MaxDB class create explain and execute statement for database Sap DB  
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class MaxDB extends Database{

	private static final Logger LOG = LoggerFactory.getLogger(MaxDB.class);
	
	private static final String EXPLAIN = "explain ";
	
	public MaxDB() {
		LOG.debug("enter MaxDB");
	}
	
	//TODO ViewResult, evtl. in Fasade
	public ViewResultSet explain(String query, Connection cn) {
		ViewResultSet viewResultSet = null;
		String sqlString = EXPLAIN + query;
		LOG.info("MPA sqlString:" + sqlString);
	
		viewResultSet = execute(sqlString, cn);
		
		return viewResultSet;
	}

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
