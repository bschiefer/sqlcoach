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
 * Created on 24.03.2007 at 19:06:20
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

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import de.sqlcoach.util.TextUtil;

/**
 * The Class StatisticForm.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class StatisticForm extends ActionForm {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9121027826628748483L;

	/** The log. */
	private static final Logger log = Logger.getLogger(StatisticForm.class);
	
	/** The scenario id. */
	private String scenarioId;

	/** The date from. */
	private Date dateFrom;

	/** The date till. */
	private Date dateTill;

	@Override
  public String toString() {
    return "[scenarioId=" + scenarioId + ", dateFrom=" + dateFrom + ", dateTill=" + dateTill + "]";
  }

  /**
	 * Gets the date from.
	 * 
	 * @return the date from
	 */
	public Date getDateFrom() {
		return this.dateFrom;
	}

	/**
	 * Sets the date from.
	 * 
	 * @param dateFrom
	 *            the new date from
	 */
	public void setDateFrom(String dateFrom) {
		try {
			this.dateFrom = TextUtil.stringToDate(dateFrom);
		} catch (ParseException e) {
			log.error("ParseException: " + e);
		}
	}

	/**
	 * Gets the date till.
	 * 
	 * @return the date till
	 */
	public Date getDateTill() {
		return this.dateTill;
	}

	/**
	 * Sets the date till.
	 * 
	 * @param dateTill
	 *            the new date till
	 */
	public void setDateTill(String dateTill) {
		try {
			this.dateTill = TextUtil.stringToDate(dateTill);
		} catch (ParseException e) {
			log.error("ParseException: " + e);
		}
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
}
