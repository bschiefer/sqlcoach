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
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.sqlcoach.db.jdbc.DBTaskgroup;
import de.sqlcoach.model.Taskgroup;
import de.sqlcoach.util.DBUtil;

/**
 * The Class DBTaskgroup.
 */
public class DBTaskgroup {

  private static final Logger log = Logger.getLogger(DBTaskgroup.class);

  /**
   * Get.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the taskgroup
   */
  public static Taskgroup get(Connection cn, String id) {
    if (log.isInfoEnabled())
      log.info("get ENTER id=" + id);

    if (id == null) {
      log.error("get LEAVE id is NULL");
      return null;
    }
    Taskgroup model = null;
    final String query = "SELECT * FROM Taskgroup WHERE id=?";
    try (final PreparedStatement pstmt = cn.prepareStatement(query)) {

      pstmt.setString(1, id);
      try (final ResultSet resultset = pstmt.executeQuery()) {
        if (resultset.next()) {
          model = new Taskgroup();
          setModel(resultset, model);
        }
      }
    } catch (SQLException e) {
      log.error("get id="+id+" query="+query, e);
    }
    return model;
  }

  /**
   * Gets the all.
   * 
   * @param cn
   *            the cn
   * 
   * @return the all
   */
  public static List<Taskgroup> getAll(Connection cn) {
    if (log.isInfoEnabled()) 
       log.info("getAll ENTER " );
    final List<Taskgroup> result = new ArrayList<Taskgroup>();
    
    final String query = "SELECT * FROM Taskgroup ORDER BY rank";
    
    try (final PreparedStatement pstmt = cn.prepareStatement(query);
         final ResultSet resultset = pstmt.executeQuery()) {

      int number = 1;
      while (resultset.next()) {
        Taskgroup model = new Taskgroup();
        model.setNumber(number++);
        setModel(resultset, model);

        result.add(model);
      }
    } catch (SQLException e) {
      log.error("getAll query="+query, e);
    }
    return result;
  }

  public static List<Taskgroup> getByScenarioId(Connection cn, String id) {
    if (log.isInfoEnabled())
      log.info("getByScenarioId ENTER id=" + id);

    if (id == null) {
      log.error("getByScenarioId LEAVE id is NULL");
      return null;
    }

    final List<Taskgroup> result = new ArrayList<Taskgroup>();

    final String query = "SELECT * FROM Taskgroup WHERE scenario_id = ? ORDER BY rank";
    try (final PreparedStatement pstmt = cn.prepareStatement(query)) {

      pstmt.setString(1, id);
      try (final ResultSet resultset = pstmt.executeQuery()) {

        int number = 1;
        while (resultset.next()) {
          Taskgroup model = new Taskgroup();
          model.setNumber(number++);
          setModel(resultset, model);

          result.add(model);
        }
      }
    } catch (SQLException e) {
      log.error("getByScenarioId id=" + id + " query=" + query, e);
    }
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
  private static void setModel(ResultSet resultset, Taskgroup model) throws SQLException {

    model.setId(resultset.getString("id"));
    model.setScenarioId(resultset.getString("scenario_id"));
    model.setRank(resultset.getInt("rank"));
    model.setDescription(resultset.getString("description"));
    model.setDatecreate(resultset.getTimestamp("datecreate"));
    model.setDatelastmod(resultset.getTimestamp("datelastmod"));
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
  public static int add(Connection cn, Taskgroup model) {
    if (log.isInfoEnabled())
      log.info("add ENTER model=" + model);
    int result = -1;
    final String query = "INSERT INTO Taskgroup (id, scenario_id, rank, description) "
                      + " VALUES (S_Taskgroup.NEXTVAL, ?, S_Taskgroup_Rank.NEXTVAL, ?)";

    try (final PreparedStatement pstmt = cn.prepareStatement(query)) {
      pstmt.setString(1, model.getScenarioId());
      pstmt.setString(2, model.getDescription());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("add model=" + model + " query=" + query, e);
    }
    return result;
  }

  /**
   * Update.
   * 
   * @param cn
   *          the cn
   * @param model
   *          the model
   * 
   * @return the int
   */
  public static int update(Connection cn, Taskgroup model) {
    if (log.isInfoEnabled())
      log.info("update ENTER model=" + model);
    int result = -1;
    final String query = "UPDATE Taskgroup SET description=?, datelastmod=? WHERE id=?";

    try (PreparedStatement pstmt = cn.prepareStatement(query)) {
      pstmt.setString(1, model.getDescription());
      pstmt.setTimestamp(2, new Timestamp(DBUtil.getNow()));
      pstmt.setString(3, model.getId());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("update model=" + model+" query="+query, e);
    }
    return result;
  }

  /**
   * Delete.
   * 
   * @param cn  the cn
   * @param model the model
   * 
   * @return the int
   */
  public static int delete(Connection cn, Taskgroup model) {
    if (log.isInfoEnabled()) 
       log.info("delete ENTER model="+model );    
    int result = -1;
    final String query = "DELETE FROM Taskgroup WHERE id = ?";

    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {    
      pstmt.setString(1, model.getId());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("DBTaskgroup.delete ", e);
    }
    return result;
  }
}
