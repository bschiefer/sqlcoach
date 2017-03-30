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

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import de.sqlcoach.util.ParamUtil;


/**
 * The Class StatisticParameter.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class StatisticParameter extends Parameter {
  private static final long serialVersionUID = 1L;

  private final static Logger log = Logger.getLogger(StatisticParameter.class);
  
  /** The date from. */
  private Date dateFrom;

  /** The date till. */
  private Date dateTill;

  /**
   * Reset dates.
   */
  public void resetDates() {
    // week in milliseconds
    long oneWeek = 7 * 24 * 60 * 60 * 1000;
    this.dateTill = new Date();
    this.dateFrom = new Date(this.dateTill.getTime() - oneWeek);
  }

  /**
   * Instantiates a new statistic parameter.
   */
  public StatisticParameter() {
    super();
    this.resetDates();
  }
  /**
   * Instantiates a new statistic parameter.
   * 
   * @param request
   *            the request
   */
  public StatisticParameter(HttpServletRequest request) {
    super(request);
    this.resetDates();
  }

  /**
   * Sets the dates.
   * 
   * @param request
   *            the new dates
   * 
   * @throws ParseException
   *             the parse exception
   */
  public void setDates(HttpServletRequest request) throws ParseException {
    Date from = ParamUtil.setDate("dateFrom", request);
    Date till = ParamUtil.setDate("dateTill", request);
    if (log.isDebugEnabled()) 
      log.debug("setDates, from=" + from+ " till=" + till);

    if (from != null) {
      this.setDateFrom(from);
    }
    if (till != null) {
      this.setDateTill(till);
    }
  }

  /**
   * Sets the request attributes.
   * 
   * @param request
   *            the new request attributes
   */
  public void setRequestAttributes(HttpServletRequest request) {
    request.setAttribute("dateFrom", this.dateFrom);
    request.setAttribute("dateTill", this.dateTill);
    request.setAttribute("scenario_id", (ParamUtil.isNull(this.scenarioId) ? null : this.scenarioId));
    request.setAttribute("taskgroup_id", (ParamUtil.isNull(this.taskgroupId) ? null : this.taskgroupId));
    request.setAttribute("task_id", (ParamUtil.isNull(this.taskId) ? null : this.taskId));
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "dateFrom=" + this.dateFrom + ", dateTill=" + this.dateTill + ", scenarioId=" + this.scenarioId + 
      ", taskgroupId=" + this.taskgroupId + ", taskId=" + this.taskId;
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
  public void setDateFrom(Date dateFrom) {
    this.dateFrom = dateFrom;
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
  public void setDateTill(Date dateTill) {
    this.dateTill = dateTill;
  }
}
