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

import java.util.Date;

import javax.ejb.Stateless;

import de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService;
import de.sqlcoach.db.jdbc.DBAppStatistic;
import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.model.AppStatisticSuccessFail;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class AppStatisticsBeanJDBC extends BaseBeanJDBC implements DBAppStatisticService {

	@Override
	public AppStatistic get(int id) {
		return DBAppStatistic.get(getConnection(), id);
	}

	@Override
	public AppStatisticSuccessFail getByTaskId(String taskId, Date from, Date till) {
		return DBAppStatistic.getByTaskId(getConnection(), taskId, from, till);
	}

	@Override
	public int add(AppStatistic model) {
		return DBAppStatistic.add(getConnection(), model);
	}

}
