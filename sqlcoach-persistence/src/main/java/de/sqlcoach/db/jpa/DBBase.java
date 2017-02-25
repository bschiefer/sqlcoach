package de.sqlcoach.db.jpa;

import java.math.BigDecimal;
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

	/**
	 * @GeneratedValue delivers Wrong Statement (for SAP DB and Oracle) from
	 * dialect: select next_val as id_val from S_APP_USER for update
	 * 
	 * @return Long
	 */
	protected Long generateNextId(String sequenceName) {
		Query query = getEntityManager().createNativeQuery("select " + sequenceName + ".nextval from dual");
		BigDecimal id = findByQuerySingleResult(query);
		return id.longValue();
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
