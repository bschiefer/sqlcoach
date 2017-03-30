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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.sqlcoach.db.jdbc.DBAppUser;
import de.sqlcoach.model.AppUser;
import de.sqlcoach.util.DBUtil;

/**
 * The Class DBAppUser.
 */
public class DBAppUser {

	/** The log. */
	private static final Log log = LogFactory.getLog(DBAppUser.class);

	/** The Constant TABLENAME. */
	private static final String TABLENAME = "app_user";

	/**
	 * Get.
	 * 
	 * @param cn
	 *            the cn
	 * @param id
	 *            the id
	 * 
	 * @return the app user
	 */
	public static AppUser get(final Connection cn, int id) {
	  if (log.isInfoEnabled()) 
	     log.info("get ENTER id="+ id);    

    final String query = "SELECT * FROM " + TABLENAME + " WHERE id = ?";
    
    AppUser model = null;
    try (PreparedStatement pstmt = cn.prepareStatement(query);) {
      pstmt.setInt(1, id);
      try (ResultSet resultset = pstmt.executeQuery();) {

        if (resultset.next()) {
          model = new AppUser();
          setModel(resultset, model);
        }
      }
    } catch (SQLException e) {
      log.error("get ", e);
    }
    return model;
  }

	/**
	 * Get.
	 * 
	 * @param cn
	 *            the cn
	 * @param nickname
	 *            the nickname
	 * 
	 * @return the app user
	 */
	public static AppUser get(Connection cn, String nickname) {
	  if (log.isInfoEnabled()) 
	     log.info("get ENTER nickname="+ nickname);    

    AppUser model = null;

    final String query = "SELECT * FROM " + TABLENAME + " WHERE nickname like ?";
    try (final PreparedStatement pstmt = cn.prepareStatement(query);) {

      pstmt.setString(1, nickname);
      try (ResultSet resultset = pstmt.executeQuery();) {
        if (resultset.next()) {
          model = new AppUser();
          setModel(resultset, model);
        }
      } // try
    } catch (SQLException e) {
      log.error("get query=" + query + " nickname=" + nickname, e);
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
  public static List<AppUser> getAll(Connection cn) {
    if (log.isInfoEnabled())
      log.info("getAll ENTER ");
    final ArrayList<AppUser> col = new ArrayList<AppUser>();
    final String query = "SELECT * FROM " + TABLENAME;
    try (final PreparedStatement pstmt = cn.prepareStatement(query);
         final ResultSet resultset = pstmt.executeQuery()) {
      while (resultset.next()) {
        AppUser model = new AppUser();
        setModel(resultset, model);
        col.add(model);
      }
    } catch (SQLException e) {
      log.error("getAll ", e);
    }
    return col;
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
	private static void setModel(ResultSet resultset, AppUser model)
			throws SQLException {
		model.setId(resultset.getInt("id"));
		model.setNickname(resultset.getString("nickname"));
		model.setPassword(resultset.getString("password"));
		model.setTitle(resultset.getString("title"));
		model.setFirstname(resultset.getString("firstname"));
		model.setLastname(resultset.getString("lastname"));
		model.setEmail(resultset.getString("email"));
		model.setRole(resultset.getString("role"));
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
	public static int add(final Connection cn, final AppUser model) {
	  if (log.isInfoEnabled()) 
	     log.info("add ENTER model="+ model);    
		int result = -1;
    final String query = "INSERT INTO "
        + TABLENAME
        + " (id, nickname, password, title, firstname, lastname, email, role) "
        + "VALUES (s_app_user.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstmt = cn.prepareStatement(query)) {

			pstmt.setString(1, model.getNickname());
			pstmt.setString(2, model.getPassword());
			pstmt.setString(3, model.getTitle());
			pstmt.setString(4, model.getFirstname());
			pstmt.setString(5, model.getLastname());
			pstmt.setString(6, model.getEmail());
			pstmt.setString(7, model.getRole());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		  log.error("add  query="+query, e);
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
	public static int update(Connection cn, AppUser model) {
	  if (log.isInfoEnabled()) 
	     log.info("update ENTER model="+ model);    
		int result = -1;
		
    final String query = "UPDATE "
        + TABLENAME
        + " SET nickname=?, password=?, title=?, firstname=?, lastname=?, email=?, role=?, datelastmod=? "
        + "WHERE id = ?";

		try (PreparedStatement pstmt = cn.prepareStatement(query)) {
			pstmt.setString(1, model.getNickname());
			pstmt.setString(2, model.getPassword());
			pstmt.setString(3, model.getTitle());
			pstmt.setString(4, model.getFirstname());
			pstmt.setString(5, model.getLastname());
			pstmt.setString(6, model.getEmail());
			pstmt.setString(7, model.getRole());
			pstmt.setTimestamp(8, new Timestamp(DBUtil.getNow()));
			pstmt.setInt(9, model.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		  log.error("update query="+query, e);
		} 
    
		return result;
	}

	/**
	 * Update without password.
	 * 
	 * @param cn
	 *            the cn
	 * @param model
	 *            the model
	 * 
	 * @return the int
	 */
	public static int updateWithoutPassword(Connection cn, AppUser model) {
	  if (log.isInfoEnabled()) 
	     log.info("updateWithoutPassword ENTER model="+ model);    
		int result = -1;
    final String query = "UPDATE "
        + TABLENAME
        + " SET nickname=?, title=?, firstname=?, lastname=?, email=?, role=?, datelastmod=? "
        + "WHERE id = ?";

		try (PreparedStatement pstmt = cn.prepareStatement(query)) {

			pstmt.setString(1, model.getNickname());
			pstmt.setString(2, model.getTitle());
			pstmt.setString(3, model.getFirstname());
			pstmt.setString(4, model.getLastname());
			pstmt.setString(5, model.getEmail());
			pstmt.setString(6, model.getRole());
			pstmt.setTimestamp(7, new Timestamp(DBUtil.getNow()));
			pstmt.setInt(8, model.getId());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("DBAppUser.updateWithoutPassword query=" + query, e);
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
	public static int delete(Connection cn, AppUser model) {
		if (log.isInfoEnabled())
			log.info("delete ENTER model=" + model);
		int result = -1;
    final String query = "DELETE FROM " + TABLENAME + " WHERE id = ?";

    try (PreparedStatement pstmt = cn.prepareStatement(query);) {

      pstmt.setInt(1, model.getId());
      result = pstmt.executeUpdate();

    } catch (SQLException e) {
      log.error("delete ", e);
    }

    return result;
	}
}
