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

import java.util.Date;
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
	private static final String SEQUENCENAME = "S_APP_USER";

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
		appUser.setId(generateNextId(SEQUENCENAME));
		appUser.setDatecreate(new Date());
		appUser.setDatelastmod(new Date());
		super.insertT(appUser);
	}

	@Override
	public AppUser update(AppUser appUser) {
		AppUser appUserTmp = this.get(appUser.getId());
		//AppUser changed without changes on password
		if(null == appUser.getPassword()){
			appUser.setPassword(appUserTmp.getPassword());
		}
		appUser.setDatecreate(appUserTmp.getDatecreate());
		appUser.setDatelastmod(new Date());
		return super.updateT(appUser);
	}

	@Override
	public void delete(AppUser appUser) {
		super.deleteT(appUser);
	}
}
