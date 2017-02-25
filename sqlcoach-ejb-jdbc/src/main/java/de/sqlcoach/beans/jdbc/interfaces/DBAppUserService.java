package de.sqlcoach.beans.jdbc.interfaces;

import java.util.List;

import de.sqlcoach.model.AppUser;

/**
 * Local Bean serves as interface between sqlcoach-integration-test-modul and ejb-jdbc-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBAppUserService {
	public AppUser get(int id);
	public AppUser get(String nickname);
	public List<AppUser> getAll();
	public int add(final AppUser model);
	public int update(AppUser model);
	public int updateWithoutPassword(AppUser model);
	public int delete(AppUser model);
}
