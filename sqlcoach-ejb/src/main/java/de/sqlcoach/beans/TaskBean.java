package de.sqlcoach.beans;

import java.sql.Connection;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import de.sqlcoach.db.jpa.DBTask;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBTaskService.class)
public class TaskBean extends DBTask implements DBTaskService {

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
