/*
 * Created on 24.03.2007 at 02:24:37
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
import java.sql.Timestamp;

/**
 * The Class AppStatistic.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class AppStatistic implements Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    return "[id=" + id + ", taskId=" + taskId + ", success=" + success + ", query=" + query
        + ", sessionId=" + sessionId + ", datecreate=" + datecreate + "]";
  }

  /** The id. */
  protected int id;

  /** The task id. */
  protected String taskId;

  /** The success. */
  protected boolean success;

  /** The query. */
  protected String query;

  /** The session id. */
  protected String sessionId;

  /** The datecreate. */
  protected Timestamp datecreate;

  /** The datelastmod. */
  protected Timestamp datelastmod;

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
   * Gets the id.
   * 
   * @return the id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the id.
   * 
   * @param id
   *            the new id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the query.
   * 
   * @return the query
   */
  public String getQuery() {
    return this.query;
  }

  /**
   * Sets the query.
   * 
   * @param query
   *            the new query
   */
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   * Gets the session id.
   * 
   * @return the session id
   */
  public String getSessionId() {
    return this.sessionId;
  }

  /**
   * Sets the session id.
   * 
   * @param sessionId
   *            the new session id
   */
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  /**
   * Checks if is success.
   * 
   * @return true, if is success
   */
  public boolean isSuccess() {
    return this.success;
  }

  /**
   * Sets the success.
   * 
   * @param success
   *            the new success
   */
  public void setSuccess(boolean success) {
    this.success = success;
  }

  /**
   * Gets the task id.
   * 
   * @return the task id
   */
  public String getTaskId() {
    return this.taskId;
  }

  /**
   * Sets the task id.
   * 
   * @param taskId
   *            the new task id
   */
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
}
