package de.sqlcoach.beans;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.jpa.DBMetaData;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 * 
 *          https://developer.jboss.org/thread/228555
 */
@Stateless
@Remote(DBConnectionService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class DBConnectionBean extends DBMetaData implements DBConnectionService {

	private static final Logger LOG = LoggerFactory.getLogger(DBConnectionBean.class);
	private static final String PERSISTENCE_UNIT_NAME = "scenariodb";

	private static Map<String, EntityManagerFactory> entityManagerFactories = new HashMap<>();
	
	/**
	 * Deliver connectionProperties to create EntityManagerFactory for scenario database
	 * (see scenario table)
	 * 
	 * @param jndiName
	 * @return Map<String, String>
	 */
	private Map<String, String> getConnectionProperties(Scenario scenario) {
		Map<String, String> connectionProperties = new HashMap<>();
		
		connectionProperties.put("javax.persistence.jtaDataSource", scenario.getDatasource());
		
		String hibernateDialect;
		switch (scenario.getDatabaseProductName().toUpperCase()) {
		case "SAPDB":
			hibernateDialect = "org.hibernate.dialect.SAPDBDialect";
			break;
		case "ORACLE":
			hibernateDialect = "org.hibernate.dialect.Oracle10gDialect";
			break;
		case "MYSQL":
			hibernateDialect = "org.hibernate.dialect.MySQLDialect";
			break;
		case "POSTGRESQL":
			hibernateDialect = "org.hibernate.dialect.PostgreSQLDialect";
			break;
		default:
			hibernateDialect = "org.hibernate.dialect.SAPDBDialect";
			break;
		}
		
		LOG.info("hibernateDialect: {}", hibernateDialect);
		
		connectionProperties.put("hibernate.dialect", hibernateDialect);
		// connectionProperties.put("hibernate.dialect",
		// "org.hibernate.dialect.SAPDBDialect");
		connectionProperties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
		
		return connectionProperties;
	}

	/**
	 * create EntityManagerFactory for scenario database
	 * 
	 * @param jndiName
	 * @return EntityManager
	 */
	public EntityManager createConnection(Scenario scenario) {
		String jndiName = scenario.getDatasource();
		Map<String, String> connectionProperties = new HashMap<>();
		connectionProperties = getConnectionProperties(scenario);
		

		if (entityManagerFactories.containsKey(jndiName)) {
			LOG.info("createConnection entityManagerFactory exists with jta Datasource: " + jndiName);
		} else {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,
					connectionProperties);

			entityManagerFactories.put(jndiName, entityManagerFactory);
			LOG.info("createConnection entityManagerFactory does not exists and create with jta Datasource: " + jndiName);
		}

		return entityManagerFactories.get(jndiName).createEntityManager();
	}

	/**
	 * get connection for selected scenario database and execute query as native query 
	 * 
	 * @param sqlString
	 * @param jndiName
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeQuery(String sqlString, Scenario scenario) {
		List<Object[]> list = null;

		EntityManager entityManager = createConnection(scenario);
		Query query = entityManager.createNativeQuery(sqlString);
		list = query.getResultList();

		return list;
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
