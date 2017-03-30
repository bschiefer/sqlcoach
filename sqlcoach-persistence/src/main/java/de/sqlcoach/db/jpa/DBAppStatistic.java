/*******************************************************************************
 * This file is part of SQLCoach.
 *
 * SQLCoach is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SQLCoach is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.sqlcoach.db.jpa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBAppStatisticService;
import de.sqlcoach.db.entities.AppStatisticSuccessFail;
import de.sqlcoach.db.entities.AppStatistic;

/**
 * Class extends methods to manipulate AppStatistic Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBAppStatistic extends DBBase implements DBAppStatisticService {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DBAppStatistic.class);
	private static final String ENTITYNAME = AppStatistic.class.getSimpleName();
	private static final String SEQUENCENAME = "S_APP_STATISTIC";

	public DBAppStatistic() {
		// nothing
	}

	@Override
	public AppStatistic get(Long id) {
		LOG.info("get ENTER id= {} ", id);

		String strQuery = "SELECT e FROM " + ENTITYNAME + " e WHERE e.id=:id";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("id", id);
		AppStatistic appStatistics = findByQuerySingleResult(query);

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
	public List<AppStatistic> selectAll() {
		String strQuery = "SELECT e FROM " + ENTITYNAME + " e";
		Query query = getEntityManager().createQuery(strQuery);
		List<AppStatistic> appStatistics = findByQuery(query);

		LOG.info("Query: {} \nSize: {}", strQuery, appStatistics.size());
		return appStatistics;
	}

	@Override
	public void insert(AppStatistic appStatistic) {
		appStatistic.setId(generateNextId(SEQUENCENAME));
		super.insertT(appStatistic);
	}

	@Override
	public AppStatistic update(AppStatistic appStatistic) {
		return super.updateT(appStatistic);
	}

	@Override
	public void delete(AppStatistic appStatistic) {
		super.deleteT(appStatistic);
	}
}
