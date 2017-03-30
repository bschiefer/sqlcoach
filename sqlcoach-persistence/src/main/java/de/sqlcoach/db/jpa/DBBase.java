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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parent DB class with findByQuery, findByQuerySingleResult, insert, update and
 * delete methods for all childs.
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public abstract class DBBase {
	private static final Logger LOG = LoggerFactory.getLogger(DBBase.class);
	
	public DBBase() {
		// nothing
	}
	
	/**
	 * getEntityManager() are created in relevant Beans at module ejb (package
	 * de.sqlcoach.beans).
	 * 
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return null;
	}
	
	public Connection getConnection(EntityManager entityManager) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> findByQuery(Query query) {
		List<T> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T findByQuerySingleResult(Query query) {
		List<T> list = query.getResultList();
		if (list.size() > 1) {
			throw new IllegalStateException("list.size: " + list.size());
		}
		if (list.size() < 1) {
			LOG.debug("list.size: {}", list.size());
			return null;
		} else {
			LOG.debug("findByQuerySingleResult: {}", list.get(0));
			return list.get(0);
		}
	}
	
	private String getDatabaseProductName() {
		String databaseProductName = "";
		try {
			databaseProductName = getConnection(getEntityManager()).getMetaData().getDatabaseProductName();
		} catch (SQLException e) {
			LOG.error("getDatabaseProductName: " + e);
		}
		
		return databaseProductName;
	}
	
	/**
	 * @GeneratedValue delivers Wrong Statement (for SAP DB and Oracle) from
	 *                 dialect: select next_val as id_val from S_APP_USER for
	 *                 update
	 * 
	 * @return Long
	 */
	protected Long generateNextId(String sequenceName) {
		String queryString = "";
		Long id = null;
		Query query = null;
		
		LOG.info("getDatabaseProductName().toUpperCase(): " + getDatabaseProductName().toUpperCase());
		
		switch (getDatabaseProductName().toUpperCase()) {
			case "SAP DB":
			case "ORACLE":
				queryString = "select " + sequenceName + ".nextval from dual";
				query = getEntityManager().createNativeQuery(queryString);
				BigDecimal resultId = findByQuerySingleResult(query);
				id = resultId.longValue();
				break;
			case "MYSQL":
				// create new id from extra table (simulate sequence with
				// auto_increment)
				queryString = "insert into " + sequenceName + " (id) values (null)";
				query = getEntityManager().createNativeQuery(queryString);
				query.executeUpdate();
				
				// get last id from extra table (simulate sequence with auto_increment)
				queryString = "select last_insert_id() from " + sequenceName + " LIMIT 1";
				query = getEntityManager().createNativeQuery(queryString);
				BigInteger taskRankId = findByQuerySingleResult(query);
				
				id = taskRankId.longValue();
				break;
			case "POSTGRESQL":
				queryString = "SELECT nextval('" + sequenceName + "')";
				query = getEntityManager().createNativeQuery(queryString);
				BigInteger resultPostgreId = findByQuerySingleResult(query);
				id = resultPostgreId.longValue();
				break;
			default:
				queryString = "";
				break;
		}
		
		return id;
	}
	
	protected <T> void insertT(T t) {
		getEntityManager().persist(t);
	}
	
	protected <T> T updateT(T t) {
		return getEntityManager().merge(t);
	}
	
	protected <T> void deleteT(T t) {
		// http://stackoverflow.com/questions/17027398/java-lang-illegalargumentexception-removing-a-detached-instance-com-test-user5
		getEntityManager().remove(getEntityManager().contains(t) ? t : getEntityManager().merge(t));
		// getEntityManager().remove(t);
	}
}
