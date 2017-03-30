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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.jdbc.interfaces.DBAppUserService;
import de.sqlcoach.db.jdbc.DBAppUser;
import de.sqlcoach.model.AppUser;

/**
 * Stateless Bean serves as interface between sqlcoach-integration-test-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBAppUserService.class)
public class AppUserBeanJDBC extends BaseBeanJDBC implements DBAppUserService {
	private static final Logger LOG = LoggerFactory.getLogger(AppUserBeanJDBC.class);
	
	@Override
	public AppUser get(int id) {
		AppUser appUser = null;
		try (Connection connection = getConnection()) {
			appUser = DBAppUser.get(connection, id);
		} catch (SQLException e) {
			LOG.error("AppUser get id: " + e);
		}

		return appUser;
	}

	@Override
	public AppUser get(String nickname) {
		AppUser appUser = null;
		try (Connection connection = getConnection()) {
			appUser = DBAppUser.get(connection, nickname);
		} catch (SQLException e) {
			LOG.error("AppUser get nickname: " + e);
		}

		return appUser;
	}

	@Override
	public List<AppUser> getAll() {
		List<AppUser> appUser = null;
		try (Connection connection = getConnection()) {
			appUser = DBAppUser.getAll(connection);
		} catch (SQLException e) {
			LOG.error("AppUser getAll: " + e);
		}

		return appUser;
	}

	@Override
	public int add(AppUser model) {
		int addResult = -2;
		try (Connection connection = getConnection()) {
			addResult = DBAppUser.add(connection, model);
		} catch (SQLException e) {
			LOG.error("AppUser add: " + e);
		}

		return addResult;
	}

	@Override
	public int update(AppUser model) {
		int updateResult = -2;
		try (Connection connection = getConnection()) {
			updateResult = DBAppUser.update(connection, model);
		} catch (SQLException e) {
			LOG.error("AppUser update: " + e);
		}

		return updateResult;
	}

	@Override
	public int updateWithoutPassword(AppUser model) {
		int updateWithoutPasswordResult = -2;
		try (Connection connection = getConnection()) {
			updateWithoutPasswordResult = DBAppUser.updateWithoutPassword(connection, model);
		} catch (SQLException e) {
			LOG.error("AppUser updateWithoutPassword: " + e);
		}

		return updateWithoutPasswordResult;
	}

	@Override
	public int delete(AppUser model) {
		int deleteResult = -2;
		try (Connection connection = getConnection()) {
			deleteResult = DBAppUser.delete(connection, model);
		} catch (SQLException e) {
			LOG.error("AppUser delete: " + e);
		}

		return deleteResult;
	}

}
