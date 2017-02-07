package de.sqlcoach.beans;

import java.util.List;

import de.sqlcoach.db.entities.AppUser;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBAppUserService {
	public static final String BEANNAME = "AppUserBean";
	public AppUser get(Long id);
	public AppUser get(String nickname);
	public List<AppUser> getAll();
	public void insert(AppUser appUser);
	public AppUser update(AppUser appUser);
	public void delete(AppUser appUser);

}
