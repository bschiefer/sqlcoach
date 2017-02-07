/*
 * Created on 17.03.2007 at 19:07:37
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

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;

import org.apache.log4j.Logger;


/**
 * The Class LinkParameter.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class LinkParameter {
  private final static Logger log = Logger.getLogger(LinkParameter.class);

  /**
   * Creates the scenario link.
   * 
   * @param pageContext
   *            the page context
   * @param view
   *            the view
   * @param status
   *            the status
   * @param itemName
   *            the item name
   * @param paramsName
   *            the params name
   */
  public static void createScenarioLink(PageContext pageContext, String view, String status, String itemName, 
                                        String paramsName) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    
    Scenario scenario = (Scenario)pageContext.getAttribute(itemName);
    if (scenario == null){
      log.warn("createScenarioLink IS NULL itemName=" + itemName+ " view=" + view+ " status=" + status+ " paramsName=" + paramsName);
    } else {
      params.put("scenario_id", scenario.getId());
    }

    params.put("status", status);
    params.put("view", view);
    pageContext.setAttribute(paramsName, params);
  }


  /**
   * Creates the taskgroup link.
   * 
   * @param pageContext
   *            the page context
   * @param view
   *            the view
   * @param status
   *            the status
   * @param itemName
   *            the item name
   * @param paramsName
   *            the params name
   */
  public static void createTaskgroupLink(PageContext pageContext, String view, String status, String itemName, 
                                         String paramsName) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    
    Taskgroup taskgroup = (Taskgroup)pageContext.getAttribute(itemName);
    if (taskgroup == null){
      log.warn("createTaskgroupLink IS NULL itemName=" + itemName+ " view=" + view+ " status=" + status+ " paramsName=" + paramsName);
    } else {
      // MPA taskgroup.getScenarioId() changed to taskgroup.getScenarioId().getId()
      params.put("scenario_id", taskgroup.getScenario().getId());
      params.put("taskgroup_id", taskgroup.getId());
    }

    params.put("status", status);
    params.put("view", view);
    pageContext.setAttribute(paramsName, params);
  }

  /**
   * Creates the taskgroup link request.
   * 
   * @param request
   *          the request
   * @param view
   *          the view
   * @param status
   *          the status
   * @param itemName
   *          the item name
   * @param paramsName
   *          the params name
   */
  public static void createTaskgroupLinkRequest(HttpServletRequest request, String view, String status,
                                                String itemName, String paramsName) {
    final Taskgroup taskgroup = (Taskgroup)request.getAttribute(itemName);

    final HashMap<String, Object> params = new HashMap<String, Object>();
    if (taskgroup == null) {
      log.warn("createTaskgroupLinkRequest IS NULL itemName=" + itemName + " view=" + view 
                                                 + " status=" + status + " paramsName=" + paramsName);
    } else {
      params.put("scenario_id", taskgroup.getScenario().getId());
      params.put("taskgroup_id", taskgroup.getId());
    }

    params.put("status", status);
    params.put("view", view);

    request.setAttribute(paramsName, params);
  }

  /**
   * Creates the task link.
   * 
   * @param pageContext
   *            the page context
   * @param view
   *            the view
   * @param status
   *            the status
   * @param itemName
   *            the item name
   * @param itemNameHead
   *            the item name head
   * @param paramsName
   *            the params name
   */
  public static void createTaskLink(PageContext pageContext, String view, String status, String itemName, 
                                    String itemNameHead, String paramsName) {
    final Task task = (Task)pageContext.getAttribute(itemName);
    final Taskgroup taskgroup = (Taskgroup)pageContext.getAttribute(itemNameHead);

    final HashMap<String, Object> params = new HashMap<String, Object>();
    
    params.put("status", status);
    params.put("view", view);
    if (taskgroup != null) {
      params.put("scenario_id", taskgroup.getScenario().getId());
    }
    if (task != null) {
      params.put("taskgroup_id", task.getTaskgroup().getId());
      params.put("task_id", task.getId());
    }
    pageContext.setAttribute(paramsName, params);
 }

  /**
   * Creates the training link.
   * 
   * @param pageContext
   *            the page context
   * @param view
   *            the view
   * @param itemName
   *            the item name
   * @param itemNameHead
   *            the item name head
   * @param paramsName
   *            the params name
   */
  public static void createTrainingLink(PageContext pageContext, String view, String itemName, String itemNameHead, 
                                        String paramsName) {
    Task task = (Task)pageContext.getAttribute(itemName);
    Taskgroup taskgroup = (Taskgroup)pageContext.getAttribute(itemNameHead);

    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("view", view);
    params.put("scenario_id", taskgroup.getScenario().getId());
    params.put("taskgroup_id", task.getTaskgroup().getId());
    params.put("task_id", task.getId());
    params.put("number", taskgroup.getNumber() + "." + task.getNumber());
    pageContext.setAttribute(paramsName, params);
  }


  /**
   * Creates the scenario add link.
   * 
   * @param pageContext
   *            the page context
   * @param paramsName
   *            the params name
   */
  public static void createScenarioAddLink(PageContext pageContext, String paramsName) {
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("status", "add");
    params.put("view", "scenario");
    pageContext.setAttribute(paramsName, params);
  }

  /**
   * Creates the taskgroup add link.
   * 
   * @param pageContext
   *            the page context
   * @param param
   *            the param
   * @param paramsName
   *            the params name
   */
  public static void createTaskgroupAddLink(PageContext pageContext, ExerciseForm param, String paramsName) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("status", "add");
    params.put("view", "taskgroup");
    params.put("scenario_id", param.getScenarioId());
    pageContext.setAttribute(paramsName, params);
  }

  /**
   * Creates the task add link.
   * 
   * @param pageContext
   *            the page context
   * @param param
   *            the param
   * @param paramsName
   *            the params name
   */
  public static void createTaskAddLink(PageContext pageContext, ExerciseForm param, String paramsName) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("status", "add");
    params.put("view", "task");
    params.put("taskgroup_id", new Integer(param.getTaskgroupId()));
    params.put("scenario_id", new Integer(param.getScenarioId()));
    pageContext.setAttribute(paramsName, params);
  }

  /**
   * Creates the user add link.
   * 
   * @param pageContext
   *            the page context
   * @param paramsName
   *            the params name
   */
  public static void createUserAddLink(PageContext pageContext, String paramsName) {
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("status", "add");
    pageContext.setAttribute(paramsName, params);
  }

  /**
   * Creates the user link.
   * 
   * @param pageContext
   *            the page context
   * @param itemName
   *            the item name
   * @param status
   *            the status
   * @param paramsName
   *            the params name
   */
  public static void createUserLink(PageContext pageContext, String itemName, String status, String paramsName) {
    AppUser appUser = (AppUser)pageContext.getAttribute(itemName);
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("status", status);
    // MPA appUser.getId() changed to appUser.getId().intValue()
    params.put("user_id", new Integer(appUser.getId().intValue()));
    pageContext.setAttribute(paramsName, params);
  }

  /**
   * Creates the password link.
   * 
   * @param pageContext
   *            the page context
   * @param paramsName
   *            the params name
   */
  public static void createPasswordLink(PageContext pageContext, String paramsName) {
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("status", "change");
    pageContext.setAttribute(paramsName, params);
  }


}
