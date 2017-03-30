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
package de.sqlcoach.beans.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.sqlcoach.db.jdbc.DBConnection;

public abstract class BaseBeanJDBC {
	private final static Logger log = Logger.getLogger(BaseBeanJDBC.class);
	
	protected Connection getConnection(){
		DBConnection cn = null;
		try {
			cn = DBConnection.getConnection();
		} catch (NamingException e) {
			log.error("NamingException: " + e);
		} catch (SQLException e) {
			log.error("SQLException: " + e);
		}
		return cn.getCn();
	}
}
