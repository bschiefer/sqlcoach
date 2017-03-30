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
package de.sqlcoach.beans;

import java.sql.Connection;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import de.sqlcoach.beans.DBAppUserService;
import de.sqlcoach.db.jpa.DBAppUser;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBAppUserService.class)
public class AppUserBean extends DBAppUser implements DBAppUserService {

	@PersistenceContext(unitName = "applicationdb")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	/**
	 * Get Connection from selected scenario EntityManager 
	 * 
	 * @param entityManager
	 * @return Connection
	 */
	public Connection getConnection(EntityManager entityManager) {
		Session hibernateSession = entityManager.unwrap(Session.class);
		SessionImpl sessionImpl = (SessionImpl) hibernateSession;
		Connection connection = sessionImpl.connection();

		return connection;
	}
}
