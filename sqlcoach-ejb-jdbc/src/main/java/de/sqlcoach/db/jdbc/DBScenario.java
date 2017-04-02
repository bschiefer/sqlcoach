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

import de.sqlcoach.db.jdbc.DBScenario;
import de.sqlcoach.model.Scenario;
import de.sqlcoach.util.DBUtil;

/**
 * The Class DBScenario.
 */
public class DBScenario {
  private static final Logger log = Logger.getLogger(DBScenario.class);
  private static final String base_query = 
            "SELECT s.*, u.title, u.firstname, u.lastname " +
            "FROM Scenario s JOIN App_User u ON s.app_user_id=u.id ";

  /**
   * Get.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the scenario
   */
  public static Scenario get(Connection cn, String id) {
    if (log.isInfoEnabled())
      log.info("get ENTER id=" + id);
    
    /**
     * MPA angepasst
     * if (ParamUtil.isNull(id)) {
     */
    if (id.isEmpty()) {
      log.error ("get LEAVE mit id is NULL");
      return null;
    }

    Scenario model = null;
    final String query = base_query +" WHERE s.id = ?";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {

//      pstmt.setString(1, id);
      pstmt.setInt(1, Integer.valueOf(id));
      try (final ResultSet resultset = pstmt.executeQuery();) {
        if (resultset.next())
          model = new Scenario(resultset);
      }
    } catch (SQLException e) {
      log.error("get id="+id, e);
    }
    return model;
  }


  /**
   * Gets the by description.
   * 
   * @param cn
   *            the cn
   * @param description
   *            the description
   * 
   * @return the by description
   */
  public static Scenario getByDescription(Connection cn, String description) {
    if (log.isInfoEnabled())
      log.info("getByDescription ENTER description=" + description);

    final String query = base_query + " WHERE description = ?";
    Scenario model = null;
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {

      pstmt.setString(1, description);
      try (final ResultSet resultset = pstmt.executeQuery();) {
        if (resultset.next())
          model = new Scenario(resultset);
      }
    } catch (SQLException e) {
      log.error("DBScenario.getByDescription ", e);
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
  public static List<Scenario> getAll(Connection cn) {
    if (log.isInfoEnabled())
      log.info("getAll ENTER" );

    final ArrayList<Scenario> result = new ArrayList<Scenario>();

    final String query = base_query + " ORDER BY s.id";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);
         final ResultSet resultset = pstmt.executeQuery();) {

      while (resultset.next()) {
        Scenario model = new Scenario(resultset);
        result.add(model);
      }

    } catch (SQLException e) {
      log.error("DBScenario.getAll ", e);
    }
    return result;
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
  public static int add(Connection cn, Scenario model) {
    if (log.isInfoEnabled())
      log.info("add ENTER model=" + model);
    int result = -1;
    final String query = 
               "INSERT INTO Scenario(id, app_user_id, description, datasource) " + 
               "VALUES (s_scenario.NEXTVAL, ?, ?, ?)";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {
      
      pstmt.setInt(1, model.getAppUserId());
      pstmt.setString(2, model.getDescription());
      pstmt.setString(3, model.getDatasource());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("add ERROR model="+model+" query="+query, e);
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
  public static int update(Connection cn, Scenario model) {
    if (log.isInfoEnabled())
      log.info("update ENTER model=" + model);
    int result = -1;
    final String query = 
    "UPDATE Scenario SET description=?, datasource=?, datelastmod=? WHERE id=?";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {

      pstmt.setString(1, model.getDescription());
      pstmt.setString(2, model.getDatasource());
      pstmt.setTimestamp(3, new Timestamp(DBUtil.getNow()));
      pstmt.setString(4, model.getId());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("update ERROR model="+model+" query="+query, e);
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
  public static int delete(Connection cn, Scenario model) {
    if (log.isInfoEnabled())
      log.info("delete ENTER model=" + model);
    int result = -1;
    final String query = "DELETE FROM Scenario WHERE id = ?";
    try (final PreparedStatement pstmt = cn.prepareStatement(query)) {

      pstmt.setString(1, model.getId());
      result = pstmt.executeUpdate();

    } catch (SQLException e) {
      log.error("delete model="+model, e);
    }
    return result;
  }
}
