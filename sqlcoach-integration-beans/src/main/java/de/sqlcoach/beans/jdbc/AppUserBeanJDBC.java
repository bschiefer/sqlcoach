package de.sqlcoach.beans.jdbc;

import java.util.List;

import javax.ejb.Stateless;

import de.sqlcoach.beans.jdbc.interfaces.DBAppUserService;
import de.sqlcoach.db.jdbc.DBAppUser;
import de.sqlcoach.model.AppUser;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class AppUserBeanJDBC extends BaseBeanJDBC implements DBAppUserService {

	@Override
	public AppUser get(int id) {
		return DBAppUser.get(getConnection(), id);
	}

	@Override
	public AppUser get(String nickname) {
		return DBAppUser.get(getConnection(), nickname);
	}

	@Override
	public List<AppUser> getAll() {
		return DBAppUser.getAll(getConnection());
	}

	@Override
	public int add(AppUser model) {
		return DBAppUser.add(getConnection(), model);
	}

	@Override
	public int update(AppUser model) {
		return DBAppUser.update(getConnection(), model);
	}

	@Override
	public int updateWithoutPassword(AppUser model) {
		return DBAppUser.updateWithoutPassword(getConnection(), model);
	}

	@Override
	public int delete(AppUser model) {
		return DBAppUser.delete(getConnection(), model);
	}

}
