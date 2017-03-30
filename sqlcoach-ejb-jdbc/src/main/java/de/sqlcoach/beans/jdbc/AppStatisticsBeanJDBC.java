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
import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService;
import de.sqlcoach.db.jdbc.DBAppStatistic;
import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.model.AppStatisticSuccessFail;

/**
 * Stateless Bean serves as interface between sqlcoach-integration-test-modul
 * and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBAppStatisticService.class)
public class AppStatisticsBeanJDBC extends BaseBeanJDBC implements DBAppStatisticService {
	private static final Logger LOG = LoggerFactory.getLogger(AppStatisticsBeanJDBC.class);

	@Override
	public AppStatistic get(int id) {
		AppStatistic appStatistic = null;
		try (Connection connection = getConnection()) {
			appStatistic = DBAppStatistic.get(connection, id);
		} catch (SQLException e) {
			LOG.error("AppStatistic get: " + e);
		}

		return appStatistic;
	}

	@Override
	public AppStatisticSuccessFail getByTaskId(String taskId, Date from, Date till) {
		AppStatisticSuccessFail appStatisticSuccessFail = null;
		try (Connection connection = getConnection()) {
			appStatisticSuccessFail = DBAppStatistic.getByTaskId(connection, taskId, from, till);
		} catch (SQLException e) {
			LOG.error("AppStatistic getByTaskId: " + e);
		}

		return appStatisticSuccessFail;
	}

	@Override
	public int add(AppStatistic model) {
		int addResult = -2;
		try (Connection connection = getConnection()) {
			addResult = DBAppStatistic.add(connection, model);
		} catch (SQLException e) {
			LOG.error("AppStatistic add: " + e);
		}

		return addResult;
	}

}
