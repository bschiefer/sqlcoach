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
