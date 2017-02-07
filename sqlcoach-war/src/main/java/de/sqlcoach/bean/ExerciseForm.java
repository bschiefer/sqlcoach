/*
 * Created on 16.03.2007 at 13:08:46
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

import de.sqlcoach.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

/**
 * The Class ExerciseForm.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class ExerciseForm extends ActionForm {
  /** The log. */
  private static final Logger log = Logger.getLogger(ExerciseForm.class);

  Parameter param = null;
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The status. */
  private String status = "null";

  /** The view. */
  private String view = "null";

  /**
   * Instantiates a new exercise parameter.
   * 
   * @param request
   *            the request
   */
  public ExerciseForm(HttpServletRequest request) {
    if (log.isInfoEnabled()) 
      log.info("ExerciseForm" );
    this.param  = new Parameter (request);
    this.status = ParamUtil.setString("status", request);
    this.view   = ParamUtil.setString("view", request);
  }

  /**
   * Sets the request attributes.
   * 
   * @param request
   *            the new request attributes
   */
  public void setRequestAttributes(HttpServletRequest request) {
    request.setAttribute("status", this.status);
    request.setAttribute("view", this.view);
    request.setAttribute("scenario_id", this.param.getScenarioId());
    request.setAttribute("taskgroup_id", this.param.getTaskgroupId());
    request.setAttribute("task_id", this.param.getTaskId());
  }

  /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

  public String toString() {
    return "[status=" + this.status + " view=" + this.view+ 
    " taskgroup_id=" + ((this.param != null) ? this.param.getTaskgroupId() : null) +"]";
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
   * Gets the view.
   * 
   * @return the view
   */
  public String getView() {
    return this.view;
  }

  /**
   * Sets the view.
   * 
   * @param view
   *            the new view
   */
  public void setView(String view) {
    this.view = view;
  }

  /**
   * Gets the scenario id.
   * 
   * @return the scenario id
   */
  public String getScenarioId() {
    return this.param.scenarioId;
  }

  /**
   * Gets the taskgroup id.
   * 
   * @return the taskgroup id
   */
  public String getTaskgroupId() {
    return this.param.taskgroupId;
  }

  /**
   * Sets the taskgroup id.
   * 
   * @param taskgroupId
   *            the new taskgroup id
   */
  public void setTaskgroupId(String taskgroupId) {
    this.param.taskgroupId = taskgroupId;
  }

  /**
   * Gets the task id.
   * 
   * @return the task id
   */
  public String getTaskId() {
    return this.param.taskId;
  }

  /**
   * Sets the task id.
   * 
   * @param taskId
   *            the new task id
   */
  public void setTaskId(String taskId) {
    this.param.taskId = taskId;
  }
}
