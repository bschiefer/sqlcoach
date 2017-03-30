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

package de.sqlcoach.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.LoginForm;
import de.sqlcoach.beans.DBAppUserService;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.util.DBUtil;

/**
 * The Class LoginAction.
 */
public class LoginAction extends Action {
	private static final Logger log = Logger.getLogger(LoginAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping , org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		final HttpSession session = request.getSession();

		final LoginForm loginform = (LoginForm) form;

		if (log.isInfoEnabled())
			log.info("LoginAction.execute ");

		AppUser user = null;
		DBAppUserService dbAppUserService = DBRemoteEJBClient.getEJB(DBAppUserService.class.getName(),
				DBAppUserService.BEANNAME);
		user = dbAppUserService.get(loginform.getUsername());

		final String password = loginform.getPassword();
		if (user != null && password != null && DBUtil.encrypt(password).equals(user.getPassword())) {
			session.setAttribute("AppUser", user); // we are in
			return mapping.findForward("success");
		} else if (password == null) {
			// Change Language Bug Fix
			return mapping.findForward("success");
		} else { // not allowed
			if (user == null) {
				Alert.catchError("alert.error.connection", request);
			} else {
				Alert.catchError("alert.error.password", request);
			}
			return mapping.findForward("failure");
		}
	}
}