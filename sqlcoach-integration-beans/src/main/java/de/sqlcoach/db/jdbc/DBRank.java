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
 * Created on 22.03.2007 at 11:41:28
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

package de.sqlcoach.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import de.sqlcoach.db.jdbc.DBRank;
import de.sqlcoach.model.Rankable;
import de.sqlcoach.util.DBUtil;

/**
 * The Class DBRank.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class DBRank {

	/** The log. */
	private static final Logger log = Logger.getLogger(DBRank.class);

	/**
	 * Rank up.
	 * 
	 * @param cn
	 *            the cn
	 * @param tablename
	 *            the tablename
	 * @param model
	 *            the model
	 * @param modelAffected
	 *            the model affected
	 * 
	 * @return the int
	 */
	public static int rankUp(Connection cn, String tablename, Rankable model,
			Rankable modelAffected) {
		if (log.isInfoEnabled()) 
			log.info("rankUp ENTER tablename=" + tablename+ " model="+model);

		int result = -1;
		final String query = 
        "SELECT id, rank FROM " + tablename + " WHERE rank = "
				+ "(SELECT MAX(rank) AS previous FROM " + tablename
				+ " WHERE rank < ? AND " + model.getReferenceIdName()
				+ " = ? )";
		result = rank(query, cn, tablename, model, modelAffected);
		return result;
	}

	/**
	 * Rank down.
	 * 
	 * @param cn
	 *            the cn
	 * @param tablename
	 *            the tablename
	 * @param model
	 *            the model
	 * @param modelAffected
	 *            the model affected
	 * 
	 * @return the int
	 */
	public static int rankDown(Connection cn, String tablename, Rankable model,
			Rankable modelAffected) {
		if (log.isInfoEnabled()) 
			log.info("rankDown ENTER tablename=" + tablename+ " model="+model);

		int result = -1;
		final String query = 
        "SELECT id, rank FROM " + tablename + " WHERE rank = "
				+ "(SELECT MIN(rank) AS nextrank FROM " + tablename
				+ " WHERE rank > ? AND " + model.getReferenceIdName()
				+ " = ? )";
		result = rank(query, cn, tablename, model, modelAffected);
		return result;
	}

	/**
	 * Rank.
	 * 
	 * @param query
	 *            the query
	 * @param cn
	 *            the cn
	 * @param tablename
	 *            the tablename
	 * @param model
	 *            the model
	 * @param modelAffected
	 *            the model affected
	 * 
	 * @return the int
	 */
	private static int rank(String query, Connection cn, String tablename,
		                    	Rankable model, Rankable modelAffected) {
		if (log.isInfoEnabled()) 
			log.info("rank ENTER tablename=" + tablename+ " model="+model+"\nquery="+query);

		int result = -1;
		try (final PreparedStatement pstmt = cn.prepareStatement(query)) {
			pstmt.setInt(1, model.getRank());
      pstmt.setString(2, model.getReferenceId());
      try (final ResultSet resultset = pstmt.executeQuery()) {
        if (resultset.next()) {
          modelAffected.setRank(resultset.getInt("rank"));
          modelAffected.setId(resultset.getString("id"));
          if (log.isInfoEnabled()) {
            log.info("modelClicked:  rank=" + model.getRank() + ", id=" + model.getId());
            log.info("modelAffected: rank=" + modelAffected.getRank() + ", id=" + modelAffected.getId());
          }
          result = changeRank(cn, tablename, model, modelAffected);

        } else {
          result = 0;
          log.info("Ranking is not modifialbe.");
        }
      } // try
    } catch (SQLException e) {
      log.error("rank ", e);
		}
		return result;
	}

	/**
	 * Change rank.
	 * 
	 * @param cn
	 *            the cn
	 * @param tablename
	 *            the tablename
	 * @param model1
	 *            the model1
	 * @param model2
	 *            the model2
	 * 
	 * @return the int
	 */
	private static int changeRank(Connection cn, String tablename, Rankable model1, Rankable model2) {
		if (log.isInfoEnabled()) 
			log.info("changeRank, tablename=" + tablename+ " model1="+model1+" model2="+model2);

    final String query = "UPDATE " + tablename
        + " SET rank = ?, datelastmod = ? WHERE id = ?";

    int result1 = -1;
		int result2 = -1;
		try (final PreparedStatement pstmt = cn.prepareStatement(query)) {

		  pstmt.setInt(1, model2.getRank());
		  pstmt.setTimestamp(2, new Timestamp(DBUtil.getNow()));
		  pstmt.setString(3, model1.getId());
			result1 = pstmt.executeUpdate();

			// second
			pstmt.setInt(1, model1.getRank());
			pstmt.setTimestamp(2, new Timestamp(DBUtil.getNow()));
			pstmt.setString(3, model2.getId());
			result2 = pstmt.executeUpdate();

		} catch (SQLException e) {
			log.error("changeRank ",e);
		}
		return (result1 > 0 && result2 > 0) ? 1 : -1;
	}
}
