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

import java.util.Date;
import java.util.List;

import de.sqlcoach.db.entities.AppStatisticSuccessFail;
import de.sqlcoach.db.entities.AppStatistic;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBAppStatisticService {
	public static final String BEANNAME = "AppStatisticsBean";
	public AppStatistic get(Long id);
	public List<AppStatistic> selectAll();
	public AppStatisticSuccessFail getByTaskId(Long taskId, Date from, Date till);
	public void insert(AppStatistic appStatistic);
	public AppStatistic update(AppStatistic appStatistic);
	public void delete(AppStatistic appStatistic);

}
