package de.sqlcoach.beans.jdbc.interfaces;

import java.util.Date;

import javax.ejb.Local;

import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.model.AppStatisticSuccessFail;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Local
public interface DBAppStatisticService {
	public AppStatistic get(int id);
	public AppStatisticSuccessFail getByTaskId(String taskId, Date from, Date till);
	public int add(final AppStatistic model);

}
