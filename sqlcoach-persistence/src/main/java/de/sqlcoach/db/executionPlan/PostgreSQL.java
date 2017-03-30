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
 * PostgreSQL class create explain and execute statement for database PostgreSQL  
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class PostgreSQL extends Database {

	private static final Logger LOG = LoggerFactory.getLogger(PostgreSQL.class);

	public PostgreSQL() {
		LOG.debug("enter PostgreSQL");
	}

	@Override
	public ViewResultSet explain(String query, Connection cn) {
		ViewResultSet viewResultSet = null;

		return viewResultSet;
	}

	@Override
	public ViewResultSet execute(String query, Connection cn) {
		ViewResultSet viewResultSet = null;

		return viewResultSet;
	}
}
