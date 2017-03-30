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
 * Created on 13.03.2007 at 21:31:32
 *
 * Florian Moritz, Chistoph Gerstle
 *
 * Project SQLcoach
 * Subject Project Digital Media
 * University of Applied Sciences Kaiserslautern
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */
package de.sqlcoach.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * The Class SQLForm.
 * 
 * @author <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 */
public class SQLForm extends ActionForm {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The sql. */
  private String sql = null;

  /* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.sql = null;
  }

  public String toString() {
    return "SQLForm (sql=" + this.sql +")";
  }

  /**
   * Gets the sql.
   * 
   * @return the sql
   */
  public String getSql() {
    return this.sql;
  }

  /**
   * Sets the sql.
   * 
   * @param sql
   *            the new sql
   */
  public void setSql(String sql) {
    this.sql = sql;
  }
}
