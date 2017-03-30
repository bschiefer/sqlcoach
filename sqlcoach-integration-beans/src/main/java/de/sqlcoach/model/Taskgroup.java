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
 * Created on 18.03.2007 at 16:41:28
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

import de.sqlcoach.model.Rankable;

/**
 * The Class Taskgroup.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class Taskgroup implements Rankable, Serializable {
	
  @Override
  public String toString() {
    return "[id=" + id + " scenarioId=" + scenarioId + " rank=" + rank + " description=" + description + " number=" + number + "]";
  }

  private static final long serialVersionUID = 1L;

  /** The id. */
	protected String id;

	/** The scenario id. */
	protected String scenarioId;

	/** The rank. */
	protected int rank;

	/** The description. */
	protected String description;

	/** The datecreate. */
	protected Timestamp datecreate;

	/** The datelastmod. */
	protected Timestamp datelastmod;

	/** The number. */
	protected int number;

	/**
	 * Gets the number.
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Sets the number.
	 * 
	 * @param number
	 *            the new number
	 */
	public void setNumber(int number) {
		this.number = number;
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

	/* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#getId()
	 */
	public String getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#setId(int)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#getRank()
	 */
	public int getRank() {
		return this.rank;
	}

	/* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#setRank(int)
	 */
	public void setRank(int rank) {
		this.rank = rank;
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

	/* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#getReferenceId()
	 */
	public String getReferenceId() {
		return this.scenarioId;
	}

	/* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#getReferenceIdName()
	 */
	public String getReferenceIdName() {
		return "scenario_id";
	}
}
