package de.sqlcoach.beans.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService;
import de.sqlcoach.db.jdbc.DBAppStatistic;
import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.model.AppStatisticSuccessFail;

/**
 * Stateless Bean serves as interface between sqlcoach-integration-test-modul
 * and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBAppStatisticService.class)
public class AppStatisticsBeanJDBC extends BaseBeanJDBC implements DBAppStatisticService {
	private static final Logger LOG = LoggerFactory.getLogger(AppStatisticsBeanJDBC.class);

	@Override
	public AppStatistic get(int id) {
		AppStatistic appStatistic = null;
		try (Connection connection = getConnection()) {
			appStatistic = DBAppStatistic.get(connection, id);
		} catch (SQLException e) {
			LOG.error("AppStatistic get: " + e);
		}

		return appStatistic;
	}

	@Override
	public AppStatisticSuccessFail getByTaskId(String taskId, Date from, Date till) {
		AppStatisticSuccessFail appStatisticSuccessFail = null;
		try (Connection connection = getConnection()) {
			appStatisticSuccessFail = DBAppStatistic.getByTaskId(connection, taskId, from, till);
		} catch (SQLException e) {
			LOG.error("AppStatistic getByTaskId: " + e);
		}

		return appStatisticSuccessFail;
	}

	@Override
	public int add(AppStatistic model) {
		int addResult = -2;
		try (Connection connection = getConnection()) {
			addResult = DBAppStatistic.add(connection, model);
		} catch (SQLException e) {
			LOG.error("AppStatistic add: " + e);
		}

		return addResult;
	}

}
