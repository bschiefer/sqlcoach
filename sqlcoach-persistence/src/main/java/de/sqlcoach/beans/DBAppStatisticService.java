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
