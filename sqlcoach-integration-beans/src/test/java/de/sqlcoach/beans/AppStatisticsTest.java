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
