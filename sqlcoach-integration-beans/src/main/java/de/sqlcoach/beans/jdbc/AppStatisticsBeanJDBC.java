package de.sqlcoach.beans.jdbc;

import java.util.Date;

import javax.ejb.Stateless;

import de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService;
import de.sqlcoach.db.jdbc.DBAppStatistic;
import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.model.AppStatisticSuccessFail;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class AppStatisticsBeanJDBC extends BaseBeanJDBC implements DBAppStatisticService {

	@Override
	public AppStatistic get(int id) {
		return DBAppStatistic.get(getConnection(), id);
	}

	@Override
	public AppStatisticSuccessFail getByTaskId(String taskId, Date from, Date till) {
		return DBAppStatistic.getByTaskId(getConnection(), taskId, from, till);
	}

	@Override
	public int add(AppStatistic model) {
		return DBAppStatistic.add(getConnection(), model);
	}

}
