package de.sqlcoach.beans.jdbc.interfaces;

import java.util.Date;

import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.model.AppStatisticSuccessFail;

/**
 * Local Bean serves as interface between sqlcoach-integration-test-modul and ejb-jdbc-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBAppStatisticService {
	public AppStatistic get(int id);
	public AppStatisticSuccessFail getByTaskId(String taskId, Date from, Date till);
	public int add(final AppStatistic model);

}
