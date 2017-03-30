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
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.jdbc.AppStatisticsBeanJDBC;
import de.sqlcoach.db.entities.AppStatistic;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

//http://webdev.jhuep.com/~jcs/ejava-javaee/coursedocs/content/html/jpa-entitymgrex-testcase.html
public class AppStatisticsTest {
	
	private DBAppStatisticService appStatisticsJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService appStatisticsJDBC = null;

	public DBAppStatisticService getJPA() {
		if (null == appStatisticsJPA) {
			appStatisticsJPA = DBRemoteEJBClient.getEJB(DBAppStatisticService.class.getName(), DBAppStatisticService.BEANNAME, ModulName.JPA);
		}
		return appStatisticsJPA;
	}

	public de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService getJDBC() {
		if (null == appStatisticsJDBC) {
			appStatisticsJDBC = DBRemoteEJBClient.getEJB(de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService.class.getName(), AppStatisticsBeanJDBC.class.getSimpleName(), ModulName.JDBC);
		}
		return appStatisticsJDBC;
	}

	@Test
	public void testGet() {
		List<AppStatistic> appStatisticsJPA = getJPA().selectAll();
		AppStatistic appStatisticJPA = getJPA().get(appStatisticsJPA.get(0).getId());
		de.sqlcoach.model.AppStatistic appStatisticJDBC = getJDBC().get(appStatisticsJPA.get(0).getId().intValue());
		
		assertEquals(appStatisticJPA.getId(), Long.valueOf(appStatisticJDBC.getId()));
		assertEquals(appStatisticJPA.getQuery(), appStatisticJDBC.getQuery());
		assertEquals(appStatisticJPA.getSessionID(), appStatisticJDBC.getSessionId());
		assertEquals(appStatisticJPA.getTask().getId(), Long.valueOf(appStatisticJDBC.getTaskId()));
		assertEquals(appStatisticJPA.getDateCreate(), appStatisticJDBC.getDatecreate());
	}
	
	@Test
	public void testGetByTaskId() {
//		Long taskId = 1L;
//		Date from = new Date();
//		Date till = new Date();
		
		//TODO Fehler AppStatisticSuccessFail
//		AppStatisticSuccessFail appStatisticsJPA = getJPA().getByTaskId(taskId, from, till);
//		System.out.println("JPA: " + appStatisticsJPA);
		
//		de.sqlcoach.model.AppStatisticSuccessFail appStatisticJDBC = getJDBC().getByTaskId("1", from, till);
//		System.out.println("JDBC: " + appStatisticJDBC.getSuccess());
		
//		assertEquals(appStatisticsJPA, appStatisticJDBC);
	}
	
	@Test
	public void testSelectAll() {
		List<AppStatistic> appStatisticsJPA = getJPA().selectAll();
		assertTrue(appStatisticsJPA.size() > 0);
	}

}
