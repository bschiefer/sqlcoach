/*
 * Created on 8.10.2007 at 13:08:46
 *
 * Authors:
 * Bernhard Schiefer
 *
 * Project: SQLcoach
 * Subject: Project Digital Media
 * Insitution: University of Applied Sciences Kaiserslautern, Zweibruecken - http://www.hs-kl.de
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */

package de.sqlcoach.bean;

import java.io.Serializable;

import de.sqlcoach.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * The Superclass Parameter.
 * 
 * @author Bernhard Schiefer
 * @version 0.1
 */
public class Parameter implements Serializable{
  private static final long serialVersionUID = 1L;

  /** The log. */
  private static final Logger log = Logger.getLogger(Parameter.class);

  /** The scenario id. */
  protected String scenarioId = "-1";

  /** The taskgroup id. */
  protected String taskgroupId = "-1";

  /** The task id. */
  protected String taskId = "-1";

  /**
   * Instantiates a new training parameter.
   * 
   * @param request
   *            the request
   */
  public Parameter(HttpServletRequest request) {
    this.scenarioId  = ParamUtil.setString("scenario_id", request);
    this.taskgroupId = ParamUtil.setString("taskgroup_id", request);
    this.taskId      = ParamUtil.setString("task_id", request);
    if (log.isInfoEnabled()) 
      log.info("Parameter scenario_id="+ this.scenarioId+ " taskgroupId="+this.taskgroupId);
  }
  
  public Parameter() {
  }


  /**
   * Sets the request attributes.
   * 
   * @param request
   *            the new request attributes
   */
  public void setRequestAttributes(HttpServletRequest request) {
    request.setAttribute("scenario_id", new Integer(this.scenarioId));
    request.setAttribute("taskgroup_id", (this.taskgroupId!=null) ? new Integer(this.taskgroupId) : null);
    request.setAttribute("task_id", (this.taskId!=null) ? new Integer(this.taskId) : null);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "ScenarioId=" + this.scenarioId + ", taskgroupId=" + this.taskgroupId + ", taskId=" + this.taskId;
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
}
