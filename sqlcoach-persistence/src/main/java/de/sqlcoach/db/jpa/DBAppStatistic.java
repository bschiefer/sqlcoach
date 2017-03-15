package de.sqlcoach.db.jpa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBAppStatisticService;
import de.sqlcoach.db.entities.AppStatisticSuccessFail;
import de.sqlcoach.db.entities.AppStatistics;

/**
 * Class extends methods to manipulate AppStatistic Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBAppStatistic extends DBBase implements DBAppStatisticService {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DBAppStatistic.class);
	private static final String ENTITYNAME = AppStatistics.class.getSimpleName();
	private static final String SEQUENCENAME = "S_APP_STATISTIC";

	public DBAppStatistic() {
		// nothing
	}

	@Override
	public AppStatistics get(Long id) {
		LOG.info("get ENTER id= {} ", id);

		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.id=:id";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("id", id);
		AppStatistics appStatistics = findByQuerySingleResult(query);

		LOG.info("Query: {} ", strQuery);

		return appStatistics;
	}

	private Integer checkSuccessOrFail(Character success, Long taskId, Date from, Date till) {
		Integer result = 0;

		Timestamp fromTimestamp = new java.sql.Timestamp(from.getTime());
		Timestamp tillTimestamp = new java.sql.Timestamp(till.getTime());

		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.task.id=:taskId "
				+ "AND e.dateCreate >:from AND e.dateCreate <:till AND e.success=:success";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("taskId", taskId);
		query.setParameter("from", fromTimestamp);
		query.setParameter("till", tillTimestamp);
		query.setParameter("success", success);
		List<AppStatisticSuccessFail> appStatisticSuccessFails = findByQuery(query);

		for (int i = 0; i < appStatisticSuccessFails.size(); i++) {
			if ('0' == success) {
				result = appStatisticSuccessFails.size();
			} else if ('1' == success) {
				result = appStatisticSuccessFails.size();
			}
		}

		LOG.info("Query: {} \nappStatisticSuccessFail: {}", strQuery, result);
		return result;
	}

	// TODO refactoring 2 functions in one Method
	/**
	 * Gets the by task id.
	 * 
	 * @param cn
	 *          the cn
	 * @param taskId
	 *          the task id
	 * @param from
	 *          the from
	 * @param till
	 *          the till
	 * 
	 * @return the by task id
	 */
	@Override
	public AppStatisticSuccessFail getByTaskId(Long taskId, Date from, Date till) {
		LOG.info("getByTaskId ENTER taskid= {} from= {} till= {} ", taskId, from, till);

		AppStatisticSuccessFail appStatisticSuccessFail = new AppStatisticSuccessFail();
		Character success = '1';
		Character fail = '0';
		
		appStatisticSuccessFail.setSuccess(checkSuccessOrFail(success, taskId, from, till));
		appStatisticSuccessFail.setFail(checkSuccessOrFail(fail, taskId, from, till));

		return appStatisticSuccessFail;
	}

	@Override
	public List<AppStatistics> selectAll() {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e";
		Query query = getEntityManager().createQuery(strQuery);
		List<AppStatistics> appStatistics = findByQuery(query);

		LOG.info("Query: {} \nSize: {}", strQuery, appStatistics.size());
		return appStatistics;
	}

	@Override
	public void insert(AppStatistics appStatistic) {
		appStatistic.setId(generateNextId(SEQUENCENAME));
		super.insertT(appStatistic);
	}

	@Override
	public AppStatistics update(AppStatistics appStatistic) {
		return super.updateT(appStatistic);
	}

	@Override
	public void delete(AppStatistics appStatistic) {
		super.deleteT(appStatistic);
	}
}
