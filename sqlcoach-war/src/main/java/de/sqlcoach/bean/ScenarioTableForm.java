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
 * The Class ScenarioTableForm.
 */
public class ScenarioTableForm extends ActionForm {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5066331541211175398L;

  /** The action. */
  private String action = "update";

  /** The scenario id. */
  protected String scenarioId;

  /** The scenario table. */
  protected String[] scenarioTable;

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
   * Gets the scenario table.
   * 
   * @return the scenario table
   */
  public String[] getScenarioTable() {
    return this.scenarioTable;
  }

  /**
   * Sets the scenario table.
   * 
   * @param scenarioTable
   *            the new scenario table
   */
  public void setScenarioTable(String[] scenarioTable) {
    this.scenarioTable = scenarioTable;
  }
}
