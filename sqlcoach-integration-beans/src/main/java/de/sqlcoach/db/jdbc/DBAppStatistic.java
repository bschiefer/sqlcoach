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
package de.sqlcoach.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import de.sqlcoach.db.jdbc.DBAppStatistic;
import de.sqlcoach.model.AppStatistic;
import de.sqlcoach.model.AppStatisticSuccessFail;

/**
 * The Class DBAppStatistic.
 */
public class DBAppStatistic {

  private final static Logger log = Logger.getLogger(DBAppStatistic.class);

  /**
   * Get.
   * 
   * @param cn  the cn
   * @param id  the id
   * 
   * @return the app statistic
   */
  public static AppStatistic get(Connection cn, int id) {
    if (log.isInfoEnabled())
      log.info("get ENTER id=" + id);

    final String query = "SELECT * FROM app_statistic WHERE id = ?";
    AppStatistic model = null;

    try (PreparedStatement pstmt = cn.prepareStatement(query)) {
      pstmt.setInt(1, id);
      try (ResultSet resultset = pstmt.executeQuery();) {
        if (resultset.next()) {
          model = new AppStatistic();
          setModel(resultset, model);
        }
      }
    } catch (SQLException e) {
      log.warn("get id=" + id+"\n query="+query, e);
    }
    return model;
  }

  /**
   * Gets the by task id.
   * 
   * @param cn   the cn
   * @param taskId the task id
   * @param from   the from
   * @param till   the till
   * 
   * @return the by task id
   */
  public static AppStatisticSuccessFail getByTaskId(Connection cn, String taskId, Date from, Date till) {
    if (log.isInfoEnabled()) {
      log.info("getByTaskId ENTER taskid=" + taskId+" from=" + from+" till=" + till);
    }
    AppStatisticSuccessFail model = null;
    final String querySuccess = 
        "SELECT COUNT(success) AS counted FROM app_statistic " + 
        "WHERE task_id = ? AND datecreate > ? AND datecreate < ? AND success = '1'";

    try (PreparedStatement pstmt = cn.prepareStatement(querySuccess)) {  // success
      model = new AppStatisticSuccessFail(); 
      
      pstmt.setString(1, taskId);
      pstmt.setTimestamp(2, new java.sql.Timestamp(from.getTime()));
      pstmt.setTimestamp(3, new java.sql.Timestamp(till.getTime()));
      if (log.isInfoEnabled()) {
        log.info("dateFrom=" + new java.sql.Timestamp(from.getTime()) + ", dateTill=" + 
                 new java.sql.Timestamp(till.getTime()));
      }
      // log.info("Statistic Success-Query = " + querySuccess);
      // log.info("TaskId=" + taskId);

      try (ResultSet resultset = pstmt.executeQuery()) {
        if (resultset.next()) {
          model.setSuccess(resultset.getInt("counted"));
        }
        if (log.isInfoEnabled()) {
          log.info("Success=" + model.getSuccess());
        }
      }
    } catch (SQLException e) {
      log.warn("getByTaskId taskid=" + taskId+" from=" + from+" till=" + till+"\n query="+querySuccess, e);
    }

    final String queryFailed = 
        "SELECT COUNT(success) AS counted FROM app_statistic " + 
        "WHERE task_id = ? AND datecreate > ? AND datecreate < ? AND success = '0'";
    try (PreparedStatement pstmt2 = cn.prepareStatement(queryFailed)) {  // failed
      pstmt2.setString(1, taskId);
      pstmt2.setTimestamp(2, new java.sql.Timestamp(from.getTime()));
      pstmt2.setTimestamp(3, new java.sql.Timestamp(till.getTime()));

      try (ResultSet resultset2 = pstmt2.executeQuery()) {
        if (resultset2.next()) {
          model.setFail(resultset2.getInt("counted"));
        }
        if (log.isInfoEnabled()) {
          log.info("Failed=" + model.getFail());
        }
      }
    } catch (SQLException e) {
      log.warn("getByTaskId taskid=" + taskId+" from=" + from+" till=" + till+"\n query="+queryFailed, e);
    }
    return model;
  }

  /**
   * TODO MPA never used ???
   * Gets the by scenario id.
   * 
   * @param cn   the cn
   * @param id   the id
   * 
   * @return the by scenario id
   */
//  public static Collection<AppStatistic> getByScenarioId(Connection cn, String id) {
//    if (log.isInfoEnabled()) {
//      log.info("getByScenarioId ENTER id=" + id);
//    }
//    ArrayList<AppStatistic> col = new ArrayList<AppStatistic>();
//
//    final String query = "SELECT * FROM app_statistic " + 
//                         "WHERE scenario_id = ? ORDER BY rank";
//    try (final PreparedStatement pstmt = cn.prepareStatement(query)) {
//      
//      pstmt.setString(1, id);
//      try (final ResultSet resultset = pstmt.executeQuery();) {
//        
//        while (resultset.next()) {
//          AppStatistic model = new AppStatistic();
//          setModel(resultset, model);
//          col.add(model);
//        }
//      }
//    } catch (SQLException e) {
//      log.warn("getByScenarioId id="+id+"\n query="+query, e);
//    }
//
//    return col;
//  }

  /**
   * Sets the model.
   * 
   * @param resultset the resultset
   * @param model the model
   * 
   * @throws SQLException the SQL exception
   */
  private static void setModel(ResultSet resultset, AppStatistic model) throws SQLException {

    model.setId(resultset.getInt("id"));
    model.setTaskId(resultset.getString("task_id"));
    model.setSuccess(resultset.getBoolean("success"));
    model.setQuery(resultset.getString("query"));
    model.setSessionId(resultset.getString("session_id"));
    model.setDatecreate(resultset.getTimestamp("datecreate"));
  }

  /**
   * Add.
   * 
   * @param cn  the cn
   * @param model the model
   * 
   * @return the int
   */
  public static int add(final Connection cn, final AppStatistic model) {
    if (log.isInfoEnabled())
      log.info("add ENTER model=" + model);

    int result = -1;

    final String query = 
      "INSERT INTO app_statistic(id, task_id, success, query, session_id) " + 
      "VALUES (s_app_statistic.NEXTVAL,?, ?, ?, ?)";

    try (PreparedStatement  pstmt = cn.prepareStatement(query)) {

      pstmt.setString(1, model.getTaskId());
      pstmt.setBoolean(2, model.isSuccess());
      pstmt.setString(3, model.getQuery());
      pstmt.setString(4, model.getSessionId());

      result = pstmt.executeUpdate();

    } catch (SQLException e) {
      log.warn("add model=" + model + "\n query=" + query, e);
    }
    return result;
 }
}
