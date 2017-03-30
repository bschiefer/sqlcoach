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
 * Created on 14.03.2007 at 13:50:27
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

package de.sqlcoach.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * The Class Scenario.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class Scenario implements Serializable {

  private static final long serialVersionUID = 1L;

  /** The id. */
  protected String id;

  /** The description. */
  protected String description;

  /** The datasource. */
  protected String datasource;

  /** The datecreate. */
  protected Timestamp datecreate;

  /** The datelastmod. */
  protected Timestamp datelastmod;

  /** The app user id. */
  protected int appUserId;

  /** The app user name (title firstname lastname). */
  protected String appUserName;

  /** The app user name (title firstname lastname). */
  protected String appUserTitle;

  /** The app user name (title firstname lastname). */
  protected String appUserFirstName;

  /** The app user name (title firstname lastname). */
  protected String appUserLastName;

  public Scenario ()  { }
  
  public Scenario (ResultSet rs) throws SQLException {
    this.setId(rs.getString("id"));
    this.setAppUserId(rs.getInt("app_user_id"));
    this.setDescription(rs.getString("description"));
    this.setDatasource(rs.getString("datasource"));
    this.setDatecreate(rs.getTimestamp("datecreate"));
    this.setDatelastmod(rs.getTimestamp("datelastmod"));
    
    this.appUserTitle = rs.getString("Title");
    this.appUserFirstName = rs.getString("FirstName");
    this.appUserLastName = rs.getString("LastName");
    this.appUserName = this.appUserFirstName + ' ' + this.appUserLastName;
    
    if (this.appUserTitle != null) 
      this.appUserName =  this.appUserTitle + ' ' + this.appUserName;
  }


  /**
   * Gets the app user id.
   * 
   * @return the app user id
   */
  public int getAppUserId() {
    return this.appUserId;
  }

  /**
   * Sets the app user id.
   * 
   * @param appUserId
   *            the new app user id
   */
  public void setAppUserId(int appUserId) {
    this.appUserId = appUserId;
  }

  /**
   * Gets the datasource.
   * 
   * @return the datasource
   */
  public String getDatasource() {
    return this.datasource;
  }

  /**
   * Sets the datasource.
   * 
   * @param datasource
   *            the new datasource
   */
  public void setDatasource(String datasource) {
    this.datasource = datasource;
  }

  /**
   * Gets the datecreate.
   * 
   * @return the datecreate
   */
  public Timestamp getDatecreate() {
    return this.datecreate;
  }

  /**
   * Sets the datecreate.
   * 
   * @param datecreate
   *            the new datecreate
   */
  public void setDatecreate(Timestamp datecreate) {
    this.datecreate = datecreate;
  }

  /**
   * Gets the datelastmod.
   * 
   * @return the datelastmod
   */
  public Timestamp getDatelastmod() {
    return this.datelastmod;
  }

  /**
   * Sets the datelastmod.
   * 
   * @param datelastmod
   *            the new datelastmod
   */
  public void setDatelastmod(Timestamp datelastmod) {
    this.datelastmod = datelastmod;
  }

  /**
   * Gets the description.
   * 
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the description.
   * 
   * @param description
   *            the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the id.
   * 
   * @return the id
   */
  public String getId() {
    return this.id;
  }

  /**
   * Sets the id.
   * 
   * @param id
   *            the new id
   */
  public void setId(String id) {
    this.id = id;
  }

  /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

  public String toString() {
    return "[id=" + this.id + " appUserId=" + this.appUserId + " description=" + this.description + 
      " datasource=" + this.datasource+"]";
  }

  public void setAppUserName(String appUserName) {
    this.appUserName = appUserName;
  }

  public String getAppUserName() {
    return appUserName;
  }

  public void setAppUserTitle(String appUserTitle) {
    this.appUserTitle = appUserTitle;
  }

  public String getAppUserTitle() {
    return appUserTitle;
  }

  public void setAppUserFirstName(String appUserFirstName) {
    this.appUserFirstName = appUserFirstName;
  }

  public String getAppUserFirstName() {
    return appUserFirstName;
  }

  public void setAppUserLastName(String appUserLastName) {
    this.appUserLastName = appUserLastName;
  }

  public String getAppUserLastName() {
    return appUserLastName;
  }
}
