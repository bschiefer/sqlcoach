package de.sqlcoach.beans;

import java.util.Date;
import java.util.List;

import de.sqlcoach.db.entities.AppStatisticSuccessFail;
import de.sqlcoach.db.entities.AppStatistics;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBAppStatisticService {
	public static final String BEANNAME = "AppStatisticsBean";
	public AppStatistics get(Long id);
	public List<AppStatistics> selectAll();
	public AppStatisticSuccessFail getByTaskId(Long taskId, Date from, Date till);
	public void insert(AppStatistics appStatistic);
	public AppStatistics update(AppStatistics appStatistic);
	public void delete(AppStatistics appStatistic);

}
