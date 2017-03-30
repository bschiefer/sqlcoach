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
package de.sqlcoach.beans;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.jdbc.AppUserBeanJDBC;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

public class AppUserTest {
	
	private DBAppUserService appUserJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBAppUserService appUserJDBC = null;

	public DBAppUserService getJPA() {
		if (null == appUserJPA) {
			appUserJPA = DBRemoteEJBClient.getEJB(DBAppUserService.class.getName(), DBAppUserService.BEANNAME, ModulName.JPA);
		}
		return appUserJPA;
	}

	public de.sqlcoach.beans.jdbc.interfaces.DBAppUserService getJDBC() {
		if (null == appUserJDBC) {
			appUserJDBC = DBRemoteEJBClient.getEJB(de.sqlcoach.beans.jdbc.interfaces.DBAppUserService.class.getName(), AppUserBeanJDBC.class.getSimpleName(), ModulName.JDBC);
		}
		return appUserJDBC;
	}
	
	@Test
	public void testGetId() {
		List<AppUser> appUsersJPA = getJPA().getAll();
		Long id = appUsersJPA.get(0).getId();
		AppUser appUserJPA = getJPA().get(id);
		de.sqlcoach.model.AppUser appUserJDBC = getJDBC().get(id.intValue());
		
		assertEquals(appUserJPA.getId(), Long.valueOf(appUserJDBC.getId()));
		assertEquals(appUserJPA.getFirstname(), appUserJDBC.getFirstname());
		assertEquals(appUserJPA.getLastname(), appUserJDBC.getLastname());
		assertEquals(appUserJPA.getNickname(), appUserJDBC.getNickname());
		assertEquals(appUserJPA.getTitle(), appUserJDBC.getTitle());
		assertEquals(appUserJPA.getPassword(), appUserJDBC.getPassword());
		assertEquals(appUserJPA.getRole(), appUserJDBC.getRole());
		assertEquals(appUserJPA.getEmail(), appUserJDBC.getEmail());
		assertEquals(appUserJPA.getDatecreate(), appUserJDBC.getDatecreate());
		assertEquals(appUserJPA.getDatelastmod(), appUserJDBC.getDatelastmod());
	}
	
	@Test
	public void testGetNickname() {
		List<AppUser> appUsersJPA = getJPA().getAll();
		String nickname = appUsersJPA.get(0).getNickname();
		AppUser appUserJPA = getJPA().get(nickname);
		de.sqlcoach.model.AppUser appUserJDBC = getJDBC().get(nickname);
		
		assertEquals(appUserJPA.getId(), Long.valueOf(appUserJDBC.getId()));
		assertEquals(appUserJPA.getFirstname(), appUserJDBC.getFirstname());
		assertEquals(appUserJPA.getLastname(), appUserJDBC.getLastname());
		assertEquals(appUserJPA.getNickname(), appUserJDBC.getNickname());
		assertEquals(appUserJPA.getTitle(), appUserJDBC.getTitle());
		assertEquals(appUserJPA.getPassword(), appUserJDBC.getPassword());
		assertEquals(appUserJPA.getRole(), appUserJDBC.getRole());
		assertEquals(appUserJPA.getEmail(), appUserJDBC.getEmail());
		assertEquals(appUserJPA.getDatecreate(), appUserJDBC.getDatecreate());
		assertEquals(appUserJPA.getDatelastmod(), appUserJDBC.getDatelastmod());
	}
	
	@Test
	public void testGetAll() {
		List<AppUser> appUsersJPA = getJPA().getAll();
		List<de.sqlcoach.model.AppUser> appUsersJDBC = getJDBC().getAll();
		
		assertEquals(appUsersJPA.size(), appUsersJDBC.size());
	}
}
