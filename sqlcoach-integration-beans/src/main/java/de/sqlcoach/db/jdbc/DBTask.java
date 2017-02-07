package de.sqlcoach.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.sqlcoach.db.jdbc.DBTask;
import de.sqlcoach.model.Task;
import de.sqlcoach.util.DBUtil;

/**
 * The Class DBTask.
 */
public class DBTask {
  private static final Logger log = Logger.getLogger(DBTask.class);

  /**
   * Get one task by id.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the task
   */
  public static Task get(Connection cn, String id) {
    if (log.isInfoEnabled()) 
       log.info("get ENTER id="+ id);
    
    /**
     * MPA angepasst
     * if (ParamUtil.isNull(id)) {
     */
    if (id.isEmpty()) {
      log.warn("get LEAVE id is  NULL");
      return null;
    }

    Task model = null;
    final String query = "SELECT * FROM Task WHERE id = ?";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {
      pstmt.setString(1, id);
      try (ResultSet resultset = pstmt.executeQuery();) {
        if (resultset.next()) {
          model = new Task();
          setModel(resultset, model);
        }
      }
    } catch (SQLException e) {
      log.error("get id=" + id + " query=" + query, e);
    }
    return model;
  }

  /**
   * Gets all tasks.
   * 
   * @param cn
   *          the cn
   * 
   * @return the all
   */
  public static List<Task> getAll(Connection cn) {
    if (log.isInfoEnabled())
      log.info("getAll ENTER");
    final List<Task>  result = new ArrayList<Task>();

    final String query = "SELECT * FROM Task ORDER BY rank";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);
         final ResultSet resultset = pstmt.executeQuery()) {
      int number = 1;
      while (resultset.next()) {
        Task model = new Task();
        model.setNumber(number++);
        setModel(resultset, model);

        result.add(model);
      }
    } catch (SQLException e) {
      log.error("getAll query=" + query, e);
    }
    return result;
  }

  /**
   * Gets by taskgroup id.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the by taskgroup id
   */
  public static List<Task> getByTaskgroupId(Connection cn, String id) {
    if (log.isInfoEnabled())
      log.info("getByTaskgroupId ENTER id=" + id);

    if (id == null) {
      log.error("getByTaskgroupId LEAVE id is NULL");
      return null;
    }
    final String query = "SELECT * FROM Task WHERE taskgroup_id = ? ORDER BY rank";
    final List<Task> result = new ArrayList<Task>();

    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {

      pstmt.setString(1, id);
      try (final ResultSet resultset = pstmt.executeQuery()) {

        int number = 1;
        while (resultset.next()) {
          Task model = new Task();
          model.setNumber(number++);
          setModel(resultset, model);

          result.add(model);
        }
      }
    } catch (SQLException e) {
      log.error("getByTaskgroupId id=" + id + " query=" + query, e);
    }
    return result;
  }

  /**
   * Gets  by scenario id.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the by taskgroup id
   */
  public static List<Task> getByScenarioId(Connection cn, String id) {
    if (log.isInfoEnabled()) 
      log.info("getByScenarioId ENTER id=" + id);
    
    if (id == null) {
      log.error("getByScenarioId LEAVE id is NULL");
      return null;
    }
    final List<Task> result = new ArrayList<Task>();

    final String query = "SELECT t.* FROM Task t JOIN TaskGroup tg ON t.taskgroup_id = tg.id " +
                         "WHERE scenario_id = ? " +
                         "ORDER BY rank";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {

      pstmt.setString(1, id);
      try (final ResultSet resultset = pstmt.executeQuery()) {
        int number = 1;
        while (resultset.next()) {
          Task model = new Task();
          model.setNumber(number++);
          setModel(resultset, model);

          result.add(model);
        }
      }
    } catch (SQLException e) {
      log.error("getByScenarioId id=" + id + " col.size()=" + (result == null ? -1 : result.size())+" query="+query, e);
    }
    if (log.isInfoEnabled())
      log.info("getByScenarioId LEAVE, id=" + id + " col.size()=" + (result == null ? -1 : result.size()));
    return result;
  }

  /**
   * Sets the model.
   * 
   * @param resultset
   *            the resultset
   * @param model
   *            the model
   * 
   * @throws SQLException
   *             the SQL exception
   */
  private static void setModel(ResultSet resultset, Task model) throws SQLException {
    if (log.isDebugEnabled()) 
      log.debug("setModel ENTER model=" + model);

    model.setId(resultset.getString("id"));
    model.setTaskgroupId(resultset.getString("taskgroup_id"));
    model.setRank(resultset.getInt("rank"));
    model.setDescription(resultset.getString("description"));
    model.setQuery(resultset.getString("query"));
    model.setDatecreate(resultset.getTimestamp("datecreate"));
    model.setDatelastmod(resultset.getTimestamp("datelastmod"));
    model.setHint(resultset.getString("hint"));
    model.setHint_trials(resultset.getInt("hint_trials"));
//	MPA solution_trials existiert in der Tabelle Task nicht, also nicht aktuelle sql statements
//    model.setSolution_trials(resultset.getInt("solution_trials"));
    if (log.isDebugEnabled()) 
      log.debug("setModel LEAVE model=" + model);
  }

  /**
   * Add.
   * 
   * @param cn
   *            the cn
   * @param model
   *            the model
   * 
   * @return the int
   */
  public static int add(Connection cn, Task model) {
    if (log.isInfoEnabled()) 
      log.info("add ENTER model=" + model);

    int result = -1;
    int i = 1;
    final String query = 
        "INSERT INTO Task (id, rank, taskgroup_id, description, query, hint, hint_trials, solution_trials) " + 
        "VALUES (S_Task.NEXTVAL, S_Task_Rank.NEXTVAL, ?, ?, ?, ?, ?)";

    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {
     
      pstmt.setString(i++, model.getTaskgroupId());
      pstmt.setString(i++, model.getDescription());
      pstmt.setString(i++, model.getQuery());
      pstmt.setString(i++, model.getHint());
      pstmt.setInt(i++, model.getHint_trials());
      pstmt.setInt(i++, model.getSolution_trials());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("add  query=" + query, e);
    }
    return result;
  }

  /**
   * Update.
   * 
   * @param cn
   *            the cn
   * @param model
   *            the model
   * 
   * @return the int
   */
  public static int update(Connection cn, Task model) {
    if (log.isInfoEnabled()) 
      log.info("update ENTER model=" + model);

    int result = -1;
    int i = 1;
    final String query = 
        "UPDATE Task " +
        " SET description=?, datelastmod=?, query=?, hint=?, hint_trials=?, solution_trials=?" + 
        " WHERE id = ?";

    try {
      final PreparedStatement pstmt = cn.prepareStatement(query);

      pstmt.setString(i++, model.getDescription());
      pstmt.setTimestamp(i++, new Timestamp(DBUtil.getNow()));
      pstmt.setString(i++, model.getQuery());
      pstmt.setString(i++, model.getHint());
      pstmt.setInt(i++, model.getHint_trials());
      pstmt.setInt(i++, model.getSolution_trials());
      
      pstmt.setString(i++, model.getId());

      result = pstmt.executeUpdate();

      pstmt.close();
    } catch (SQLException e) {
      log.error("update model="+model+" query="+query, e);
    }
    return result;
  }

  /**
   * Delete.
   * 
   * @param cn
   *            the cn
   * @param model
   *            the model
   * 
   * @return the int
   */
  public static int delete(Connection cn, Task model) {
    if (log.isInfoEnabled()) 
      log.info("delete ENTER model=" + model);
    int result = -1;
    final String query = "DELETE FROM Task WHERE id = ?";
    
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {
      pstmt.setString(1, model.getId());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("delete model=" + model, e);
    }
    return result;
  }
}
