package de.sqlcoach.db.jpa;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBAppUserService;
import de.sqlcoach.db.entities.AppUser;

/**
 * Class extends methods to manipulate AppUser Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBAppUser extends DBBase implements DBAppUserService {
	private static final Logger LOG = LoggerFactory.getLogger(DBAppUser.class);
	private static final String ENTITYNAME = AppUser.class.getName();

	/** The Constant TABLENAME. */
	Table table = AppUser.class.getAnnotation(Table.class);
	String tableName = table.name();

	public DBAppUser() {
		super();
	}
	
	@Override
	public AppUser get(Long id) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.id=:id";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("id", id);
		AppUser appUser = findByQuerySingleResult(query);

		return appUser;
	}

	@Override
	public AppUser get(String nickname) {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.nickname=:nickname";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("nickname", nickname);
		AppUser appUser = findByQuerySingleResult(query);

		return appUser;
	}
	
	@Override
	public List<AppUser> getAll() {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e";
		Query query = getEntityManager().createQuery(strQuery);
		List<AppUser> appUsers = findByQuery(query);

		LOG.info("Query: {} \nSize: {}", strQuery, appUsers.size());
		return appUsers;
	}

	@Override
	public void insert(AppUser appUser) {
		super.insertT(appUser);
	}

	@Override
	public AppUser update(AppUser appUser) {
		return super.updateT(appUser);
	}

	@Override
	public void delete(AppUser appUser) {
		super.deleteT(appUser);
	}
}
