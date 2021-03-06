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
 * Created on 13.03.2007 at 21:35:06
 *
 * Florian Moritz, Chistoph Gerstle
 *
 * Project SQLcoach
 * Subject Project Digital Media
 * University of Applied Sciences Kaiserslautern
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */
package de.sqlcoach.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.SQLForm;

/**
 * The Class SQLAction.
 * 
 * @author <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 */
public class SQLAction extends Action {

  /** The log. */
  private static final Log log = LogFactory.getLog(SQLAction.class);

  /* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
                               HttpServletResponse response) throws Exception {
    SQLForm sqlform = (SQLForm)form;
    if (sqlform.getSql().equals("")) {
      log.info("empty string");
      return mapping.findForward("failure");

    } else {
      if (log.isInfoEnabled()) {
        log.info(sqlform.getSql());
      }
      return mapping.findForward("success");
    }
  }
}
