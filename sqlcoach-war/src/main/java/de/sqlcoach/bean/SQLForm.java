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
