package de.sqlcoach.db.jpa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBAppStatisticService;
import de.sqlcoach.db.entities.AppStatistics;
import de.sqlcoach.db.entities.AppStatisticSuccessFail;

/**
 * Class extends methods to manipulate AppStatistic Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBAppStatistic extends DBBase implements DBAppStatisticService {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DBAppStatistic.class);
	private static final String ENTITYNAME = AppStatistics.class.getSimpleName();

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

	@Override
	public AppStatistics getLastEntry() {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e order by e.id desc";
		Query query = getEntityManager().createQuery(strQuery);
		@SuppressWarnings("unchecked")
		List<AppStatistics> appStatistics = query.setMaxResults(1).getResultList();

		if (null == appStatistics) {
			return null;
		}

		LOG.info("Query: {} ", strQuery);

		if (appStatistics.size() > 1) {
			throw new IllegalStateException("list.size: " + appStatistics.size());
		}
		if (appStatistics.size() < 1) {
			LOG.debug("list.size: {}", appStatistics.size());
			return null;
		} else {
			return appStatistics.get(0);
		}

	}

	private AppStatisticSuccessFail checkSuccessOrFail(Character success, Long taskId, Date from, Date till) {
		AppStatisticSuccessFail appStatisticSuccessFail = new AppStatisticSuccessFail();

		Timestamp fromTimestamp = new java.sql.Timestamp(from.getTime());
		Timestamp tillTimestamp = new java.sql.Timestamp(till.getTime());

		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.task.id=:taskId "
				+ "AND e.datecreate > from AND e.datecreate < till AND e.success=:success";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("taskId", taskId);
		query.setParameter("from", fromTimestamp);
		query.setParameter("till", tillTimestamp);
		query.setParameter("success", success);
		List<AppStatisticSuccessFail> appStatisticSuccessFails = findByQuery(query);

		for (int i = 0; i < appStatisticSuccessFails.size(); i++) {
			if ('0' == success) {
				appStatisticSuccessFail.setFail(appStatisticSuccessFails.size());
			} else if ('1' == success) {
				appStatisticSuccessFail.setSuccess(appStatisticSuccessFails.size());
			}
		}

		LOG.info("Query: {} \nappStatisticSuccessFail: {}", strQuery, appStatisticSuccessFails);
		return appStatisticSuccessFail;
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

		AppStatisticSuccessFail appStatisticSuccessFail = null;
		Character success = '1';
		Character fail = '0';

		appStatisticSuccessFail = checkSuccessOrFail(success, taskId, from, till);
		appStatisticSuccessFail = checkSuccessOrFail(fail, taskId, from, till);

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
