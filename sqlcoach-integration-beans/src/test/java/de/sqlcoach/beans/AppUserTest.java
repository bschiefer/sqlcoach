package de.sqlcoach.beans;
import java.util.List;

import org.junit.Test;

import de.sqlcoach.db.entities.AppUser;

public class AppUserTest {
	
	@Test
	public void test() {
		AppUser appUserJPA = null;
		de.sqlcoach.model.AppUser appUserJDBC = null;
		
		List<AppUser> appUsersJPA = null;
		List<de.sqlcoach.model.AppUser> appUsersJDBC = null;
		
		if(null != lookUpAppUserJPA() && null != lookUpAppUserJDBC()) {
			int id = 0;
			appUserJPA = lookUpAppUserJPA().get(Long.valueOf(id) );
			appUserJDBC = lookUpAppUserJDBC().get(id);
			appUserJDBC.toString().equals(appUserJPA.toString());
			
			String nickname = "";
			appUserJPA = lookUpAppUserJPA().get(nickname);
			appUserJDBC = lookUpAppUserJDBC().get(nickname);
			appUserJDBC.toString().equals(appUserJPA.toString());
			
			appUsersJPA = lookUpAppUserJPA().getAll();
			appUsersJDBC = lookUpAppUserJDBC().getAll();
			appUsersJDBC.toString().equals(appUsersJPA.toString());
		}
	}
	
	private DBAppUserService lookUpAppUserJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBAppUserService lookUpAppUserJDBC() {
		return null;
	}

}
