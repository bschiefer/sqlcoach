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
package de.sqlcoach.db.jpa;

import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;

import de.sqlcoach.db.entities.Scenario;

public class DBConnectionBase {
	/**
	 * getEntityManager() are created in relevant Beans at module ejb (package
	 * de.sqlcoach.beans).
	 * 
	 * @return EntityManager
	 */
	public EntityManager createConnection(Scenario scenario) {
		return null;
	}
	
	/**
	 * 
	 * @param entityManager
	 * @return
	 */
	public Connection getConnection(EntityManager entityManager) {
		return null;
	}
	
	/**
	 * 
	 * @param sqlString
	 * @param scenario
	 * @return
	 */
	public List<Object[]> executeQuery(String sqlString, Scenario scenario){
		return null;
	}
}
