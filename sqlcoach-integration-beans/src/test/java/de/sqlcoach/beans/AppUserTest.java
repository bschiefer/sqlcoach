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
import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.DBAppUserService;
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
