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

import org.junit.Test;

import de.sqlcoach.beans.DBAppStatisticService;
import de.sqlcoach.beans.jdbc.AppStatisticsBeanJDBC;
import de.sqlcoach.db.entities.AppStatistics;
import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;

public class AppStatisticsTest {
	private DBAppStatisticService appStatisticsJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService appStatisticsJDBC = null;

	public DBAppStatisticService getJPA() {
		if (null == appStatisticsJPA) {
			appStatisticsJPA = DBRemoteEJBClient.getEJB(DBAppStatisticService.class.getName(), DBAppStatisticService.BEANNAME);
		}
		return appStatisticsJPA;
	}

	public de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService getJDBC() {
		if (null == appStatisticsJDBC) {
			appStatisticsJDBC = DBRemoteEJBClient.getEJB(de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService.class.getName(), AppStatisticsBeanJDBC.class.getSimpleName());
		}
		return appStatisticsJDBC;
	}

	@Test
	public void test() {
//		AppStatistics appStatisticsJPA = getJPA().get(1L);
//		System.out.println(appStatisticsJPA);
//		
//		AppStatistic appStatisticJDBC = getJDBC().get(1);
//		System.out.println(appStatisticJDBC);

//		if (null != lookUpAppstatisticsJPA() && null != lookUpAppstatisticsJDBC()) {
//			// appStatisticsJPA = lookUpAppstatisticsJPA().get(0);
//			appStatisticsJDBC = lookUpAppstatisticsJDBC().get(0);
//			appStatisticsJDBC.toString().equals(appStatisticsJPA.toString());
//
//			// appStatisticsJPA = lookUpAppstatisticsJPA().get(0);
//			appStatisticsJDBC = lookUpAppstatisticsJDBC().get(0);
//			appStatisticsJDBC.toString().equals(appStatisticsJPA.toString());
//		}
	}

	private DBAppStatisticService lookUpAppstatisticsJPA() {
		return null;
	}

	private de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService lookUpAppstatisticsJDBC() {
		return null;
	}

}
