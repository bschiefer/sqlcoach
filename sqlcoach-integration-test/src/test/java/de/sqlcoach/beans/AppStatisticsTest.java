package de.sqlcoach.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.jdbc.AppStatisticsBeanJDBC;
import de.sqlcoach.db.entities.AppStatistics;
import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

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
		AppStatistics appStatisticsJPA = getJPA().get(1L);
		AppStatistic appStatisticJDBC = getJDBC().get(1);
		
		assertEquals(appStatisticsJPA.getId(), Long.valueOf(appStatisticJDBC.getId()));
		assertEquals(appStatisticsJPA.getQuery(), appStatisticJDBC.getQuery());
		assertEquals(appStatisticsJPA.getSessionID(), appStatisticJDBC.getSessionId());
		assertEquals(appStatisticsJPA.getTask().getId(), Long.valueOf(appStatisticJDBC.getTaskId()));
		assertEquals(appStatisticsJPA.getDateCreate(), appStatisticJDBC.getDatecreate());
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
		List<AppStatistics> appStatisticsJPA = getJPA().selectAll();
		assertTrue(appStatisticsJPA.size() > 0);
	}

}
