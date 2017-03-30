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
/*
 * Created on 23.03.2007 at 15:42:15
 *
 * Authors:
 * Florian Moritz - http://www.flomedia.de
 * Christoph Gerstle - http://www.christophgerstle.de
 *
 * Project: SQLcoach
 * Subject: Project Digital Media
 * Insitution: University of Applied Sciences Kaiserslautern, Zweibruecken - http://www.hs-kl.de
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */

package de.sqlcoach.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import java.sql.Types;

import java.text.SimpleDateFormat;

/**
 * The Class DBViewResultSet.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class DBViewResultSet {
  private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

  private static final Logger log = Logger.getLogger(DBViewResultSet.class);

  /**
   * Get.
   * 
   * @param cn
   *            the cn
   * @param query
   *            the query
   * 
   * @return the view result set
   * 
   * @throws SQLException
   *             the SQL exception
   */
  public static ViewResultSet get(Connection cn, String query) throws SQLException {
    if (log.isInfoEnabled()) 
      log.info("get ENTER query = " + query);

    final ViewResultSet viewResultSet = new ViewResultSet();
    
    if (query == null || !query.toUpperCase().contains("SELECT") ) {
      log.warn("get - Illegal Statement: query= " + query);
      return null;
    }

    try (PreparedStatement pstmt = cn.prepareStatement(query);
         ResultSet resultset = pstmt.executeQuery()) {

      if (resultset == null) { // Fehler aufgetreten!
        log.warn("get - resultset == null query= " + query);
        pstmt.close();
        return null;
      }
      final ResultSetMetaData rsm = resultset.getMetaData();

      viewResultSet.setFieldNames(getColumnNames(pstmt, rsm));

      while (resultset.next()) {
        ViewResultRow row = getDataRow(resultset, rsm);
        viewResultSet.add(row);
      }
    } catch (SQLException e) {
      if (log.isInfoEnabled())
        log.info("get - Error " + e + " query= " + query);
      throw e;
    }

    if (log.isDebugEnabled())
      log.debug("get LEAVE #records = " + viewResultSet.size());

    return viewResultSet;
  }

  /**
   * Gets the column names.
   * 
   * @param ps
   *            the ps
   * 
   * @return the column names
   * 
   * @throws SQLException
   *             the SQL exception
   */
  private static ViewResultRow getColumnNames(final PreparedStatement ps, final ResultSetMetaData rsm) throws SQLException {
    if (log.isInfoEnabled()) 
      log.info("getColumnNames ENTER");

    final ViewResultRow row = new ViewResultRow();

    // final ResultSetMetaData rsm = ps.getMetaData();
    int rscols = rsm.getColumnCount();

    for (int i = 0; i < rscols; i++) {
      ViewResultValue value = new ViewResultValue();
      value.setValue(rsm.getColumnName(i + 1));
      row.add(value);
    }

    if (log.isInfoEnabled()) 
      log.info("getColumnNames LEAVE rscols="+rscols);
    return row;
  }

  /**
   * Gets the data row.
   * 
   * @param rs
   *            the rs
   * 
   * @return the data row
   * 
   * @throws SQLException
   *             the SQL exception
   */
  private static ViewResultRow getDataRow(final ResultSet rs, final ResultSetMetaData rsm) throws SQLException {
    final ViewResultRow row = new ViewResultRow();

    if (log.isDebugEnabled()) 
      log.debug("getDataRow ENTER");

    for (int i = 1; i <= rsm.getColumnCount(); i++) {
      int ctype = rsm.getColumnType(i);
      String element = ((ctype == Types.DATE || ctype == Types.TIMESTAMP) && rs.getDate(i) != null) 
                       ? df.format (rs.getDate(i)) // Datum formatieren 
                       : rs.getString(i); // alles andere als String
      // log.debug ("getDataRow i="+i+ " columnName="+rsm.getColumnName(i)+ " ctype="+ctype+ " Types.DATE="+Types.DATE+ " Types.TIMESTAMP="+Types.TIMESTAMP+" element"+element);
      if (element == null)
        element = "-";
      final ViewResultValue value = new ViewResultValue();
      value.setValue(element);
      row.add(value);
    }

    return row;
  }
}
