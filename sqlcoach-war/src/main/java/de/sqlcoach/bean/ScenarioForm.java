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
import org.apache.struts.action.ActionForm;

/**
 * The Class ScenarioForm.
 */
public class ScenarioForm extends ActionForm {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5066331541211175398L;

  /** The action. */
  private String action = "add";

  /** The id. */
  private String id;

  /** The app user id. */
  private int appUserId;

  /** The scenario id. */
  private String scenarioId;

  /** The status. */
  private int status;

  /** The description. */
  private String description;

  /** The datasource. */
  private String datasource;

  /**
   * Gets the action.
   * 
   * @return the action
   */
  public String getAction() {
    return this.action;
  }

  /**
   * Sets the action.
   * 
   * @param action
   *            the new action
   */
  public void setAction(String action) {
    this.action = action;
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
  public int getStatus() {
    return this.status;
  }

  /**
   * Sets the status.
   * 
   * @param status
   *            the new status
   */
  public void setStatus(int status) {
    this.status = status;
  }
}
