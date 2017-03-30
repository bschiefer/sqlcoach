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
import java.sql.Timestamp;

import de.sqlcoach.model.ScenarioTable;

/**
 * The Class ScenarioTable.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class ScenarioTable implements Serializable{
  private static final long serialVersionUID = 1L;

  /** The scenario id. */
  protected String scenarioId;

  /** The scenario table. */
  protected String scenarioTable;

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
  public String getScenarioTable() {
    return this.scenarioTable;
  }

  /**
   * Sets the scenario table.
   * 
   * @param scenarioTable
   *            the new scenario table
   */
  public void setScenarioTable(String scenarioTable) {
    this.scenarioTable = scenarioTable;
  }

  /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

  public String toString() {
    return "[scenarioId=" + this.scenarioId + " scenarioTable=" + this.scenarioTable+"]";
  }

  /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj instanceof ScenarioTable) {
      ScenarioTable st = (ScenarioTable)obj;
      if (this.scenarioId == st.scenarioId && this.scenarioTable.equals(st.scenarioTable)) {
        return true;
      }
    }
    return false;
  }

  //TODO override hashcode!!

}
