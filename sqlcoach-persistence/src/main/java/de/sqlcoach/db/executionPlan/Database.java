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

import de.sqlcoach.util.ViewResultSet;

/**
 * Parent Database class with explain, execute methods for all childs 
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public abstract class Database {
	public abstract ViewResultSet explain (String query, Connection cn);
	public abstract ViewResultSet execute (String query, Connection cn);
}
