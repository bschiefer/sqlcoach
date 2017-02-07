package de.sqlcoach.beans;
import org.junit.Test;

import de.sqlcoach.db.entities.AppStatistics;
import de.sqlcoach.model.AppStatistic;

public class AppStatisticsTest {
	
	@Test
	public void test() {
		AppStatistics appStatisticsJPA = null;
		AppStatistic appStatisticsJDBC = null;
		
		if(null != lookUpAppstatisticsJPA() && null != lookUpAppstatisticsJDBC()) {
//			appStatisticsJPA = lookUpAppstatisticsJPA().get(0);
			appStatisticsJDBC = lookUpAppstatisticsJDBC().get(0);
			appStatisticsJDBC.toString().equals(appStatisticsJPA.toString());
			
//			appStatisticsJPA = lookUpAppstatisticsJPA().get(0);
			appStatisticsJDBC = lookUpAppstatisticsJDBC().get(0);
			appStatisticsJDBC.toString().equals(appStatisticsJPA.toString());
		}
	}
	
	private DBAppStatisticService lookUpAppstatisticsJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService lookUpAppstatisticsJDBC() {
		return null;
	}

}
