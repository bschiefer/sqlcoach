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
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.sqlcoach.db.jdbc.DBScenarioTable;
import de.sqlcoach.model.ScenarioTable;

/**
 * The Class DBScenarioTable.
 */
public class DBScenarioTable {
  private static final Logger log = Logger.getLogger(DBScenarioTable.class);

  /**
   * Get.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the scenario table
   */
  public static ScenarioTable get(Connection cn, int id) {
    if (log.isInfoEnabled())
      log.info("get ENTER id=" + id);

    final String query = "SELECT * FROM scenario_table WHERE id = ?";
    ScenarioTable model = null;
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {

      pstmt.setInt(1, id);
      try (final ResultSet resultset = pstmt.executeQuery();) {

        if (resultset.next()) {
          model = new ScenarioTable();
          setModel(resultset, model);
        }
      }
    } catch (SQLException e) {
      log.error("DBScenarioTable.get ", e);
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
  public static List<ScenarioTable> getAll(Connection cn) {
    if (log.isInfoEnabled()) 
      log.info("getAll ENTER" );
    
    final List<ScenarioTable> result = new ArrayList<ScenarioTable>();

    final String query = "SELECT * FROM scenario_table ORDER BY scenario_table";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);
         final ResultSet resultset = pstmt.executeQuery()) {

      while (resultset.next()) {
        ScenarioTable model = new ScenarioTable();
        setModel(resultset, model);

        result.add(model);

      } // try
    } catch (SQLException e) {
      log.error("DBScenarioTable.getAll query=" + query, e);
    }
    return result;
  }

  /**
   * Gets the by scenario id.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the by scenario id
   */
  public static List<ScenarioTable> getByScenarioId(Connection cn, String id) {
    if (log.isInfoEnabled()) 
      log.info("getByScenarioId ENTER id=" + id);

    /**
     * MPA angepasst
     * if (ParamUtil.isNull(id)) {
     */
    if (id.isEmpty()) {
      log.error ("getByScenarioId LEAVE mit id is NULL");
      return null;
    }
   
    final String query = "SELECT * FROM scenario_table WHERE scenario_id = ? ORDER BY scenario_table";
    final List<ScenarioTable> result = new ArrayList<ScenarioTable>();

    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {
      
      pstmt.setString(1, id);
      try (final ResultSet resultset = pstmt.executeQuery()) {

        while (resultset.next()) {
          ScenarioTable model = new ScenarioTable();
          setModel(resultset, model);
          result.add(model);
        }
      }
    } catch (SQLException e) {
      log.error("getByScenarioId id="+id+" query="+query, e);
    }
    if (log.isInfoEnabled()) 
      log.info("getByScenarioId, #Tabs=" + result.size());
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
  private static void setModel(ResultSet resultset, ScenarioTable model) throws SQLException {

    model.setScenarioId(resultset.getString("scenario_id"));
    model.setScenarioTable(resultset.getString("scenario_table"));
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
  public static int add(Connection cn, ScenarioTable model) {
    if (log.isInfoEnabled()) 
      log.info("add ENTER model=" + model);
    int result = -1;
    final String query = "INSERT INTO scenario_table(scenario_id, scenario_table) VALUES (?,?)";
    try (PreparedStatement pstmt = cn.prepareStatement(query);) {
      pstmt.setString(1, model.getScenarioId());
      pstmt.setString(2, model.getScenarioTable());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("add model="+model, e);
    }
    return result;
  }

  /**
   * Delete by scenario id.
   * 
   * @param cn
   *            the cn
   * @param id
   *            the id
   * 
   * @return the int
   */
  public static int delete(Connection cn, String id) {
    if (log.isInfoEnabled()) 
      log.info("deleteByScenarioId ENTER id=" + id);
      
    int result = -1;
    final String query = "DELETE FROM scenario_table WHERE scenario_id = ? ";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {
      pstmt.setString(1, id);
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("DBScenarioTable.delete ", e);
    }
    return result;
  }
}
