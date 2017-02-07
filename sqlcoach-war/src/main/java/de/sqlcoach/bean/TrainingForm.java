/*
 * Created on 11.03.2007 at 18:37:57
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

package de.sqlcoach.bean;

/**
 * Form bean for the Login Entry Screen.
 *
 */
import de.sqlcoach.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

/**
 * The Class TrainingForm.
 */
public class TrainingForm extends ActionForm {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5066331541211175398L;

  /** The query. */
  private String query;

  /** The status. */
  private String status;

  /** The scenario id. */
  private String scenarioId = "-1";

  /** The taskgroup id. */
  private String taskgroupId = "-1";

  /** The task id. */
  private String taskId = "-1";

  /** The number. */
  private String number;


  @Override
  public String toString() {
    return "[number=" + this.number + " status=" + this.status + " scenarioId=" + this.scenarioId + " taskgroupId=" + 
      this.taskgroupId + " taskId=" + this.taskId + " traineeQuery=" + (this.query!= null ? this.query.trim() : null) +"]";
  }

  public TrainingForm() {
  }
  
  public TrainingForm(HttpServletRequest request) {
    this.status = ParamUtil.setString("status", request);
    this.scenarioId = ParamUtil.setString("scenario_id", request);
    this.taskgroupId = ParamUtil.setString("taskgroup_id", request);
    this.taskId = ParamUtil.setString("task_id", request);
    this.number = ParamUtil.setString("number", request);
    this.query = ParamUtil.setString("query", request);
  }

  /**
   * Gets the number.
   * 
   * @return the number
   */
  public String getNumber() {
    return this.number;
  }

  /**
   * Sets the number.
   * 
   * @param number
   *            the new number
   */
  public void setNumber(String number) {
    this.number = number;
  }

  /**
   * Gets the scenario id.
   * 
   * @return the scenario id
   */
  public String getScenarioId() {
    return this.scenarioId;
  }

  /**
   * Sets the scenario id.
   * 
   * @param scenarioId
   *            the new scenario id
   */
  public void setScenarioId(String scenarioId) {
    this.scenarioId = scenarioId;
  }

  /**
   * Gets the status.
   * 
   * @return the status
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * Sets the status.
   * 
   * @param status
   *            the new status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the taskgroup id.
   * 
   * @return the taskgroup id
   */
  public String getTaskgroupId() {
    return this.taskgroupId;
  }

  /**
   * Sets the taskgroup id.
   * 
   * @param taskgroupId
   *            the new taskgroup id
   */
  public void setTaskgroupId(String taskgroupId) {
    this.taskgroupId = taskgroupId;
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
}
