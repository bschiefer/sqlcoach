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
