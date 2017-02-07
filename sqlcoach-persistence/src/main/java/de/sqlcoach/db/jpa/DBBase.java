package de.sqlcoach.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parent DB class with findByQuery, findByQuerySingleResult, 
 * insert, update and delete methods for all childs.
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
	 * getEntityManager() are created in relevant Beans at module ejb (package de.sqlcoach.beans).
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
			return list.get(0);
		}
	}
	
	protected <T> void insertT(T t) {
		getEntityManager().persist(t);
	}

	protected <T> T updateT(T t) {
		return getEntityManager().merge(t);
	}

	protected <T> void deleteT(T t) {
		//http://stackoverflow.com/questions/17027398/java-lang-illegalargumentexception-removing-a-detached-instance-com-test-user5
		getEntityManager().remove(getEntityManager().contains(t) ? t : getEntityManager().merge(t));
		//getEntityManager().remove(t);
	}
}
