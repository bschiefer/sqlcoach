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
 * Created on 24.03.2007 at 13:28:32
 *  
 * Florian Moritz, Chistoph Gerstle
 *
 * Project SQLcoach
 * Subject Project Digital Media
 * University of Applied Sciences Kaiserslautern
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */
package de.sqlcoach.action;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import de.sqlcoach.bean.Alert;
import de.sqlcoach.beans.DBAppUserService;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.util.LoginCheck;

/**
 * The Class UserConfigController.
 * 
 * @author <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 */
public class UserConfigController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static final Logger log = Logger.getLogger(AdminController.class);

	/** The page. */
	private String page = null;

	@EJB
	private DBAppUserService dbAppUserService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig poConfig) throws ServletException {
		super.init(poConfig);
		this.page = poConfig.getInitParameter("page");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest , javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isInfoEnabled()) {
			log.info("UserConfigController, Page=" + this.page);
		}
		// [update | delete | add]
		final String status = request.getParameter("status");
		final String user_id = request.getParameter("user_id");
		if (log.isInfoEnabled()) {
			log.info("UserConfigController, status=" + status);
		}

		HttpSession session = request.getSession(true);
		if (!LoginCheck.loggedin(session)) { // not logged in
			Alert.catchError("alert.error.loggedout", request);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

		// user , param page is set at web.xml
		if (this.page.equals("user")) {
			final boolean isSuperAdmin = LoginCheck.isSuperAdmin(session);
			// status and user_id must be set to proceed update
			if (user_id != null && status != null) {
				int user_idIntValue = new Integer(user_id).intValue();
				if (status.equals("update")) {
					// get specific user only
					AppUser appUserUpdate = dbAppUserService.get(Long.valueOf(user_idIntValue));
					request.setAttribute("appUserUpdate", appUserUpdate);

				}
				if (((isSuperAdmin)) && (status.equals("delete"))) {
					// get specific user only
					AppUser appUserDelete = dbAppUserService.get(Long.valueOf(user_idIntValue));
					request.setAttribute("appUserDelete", appUserDelete);
				}
			}
			// superAdmin gets UserList at frontend
			if (isSuperAdmin) {
				Collection<AppUser> appUserCol = dbAppUserService.getAll();
				request.setAttribute("appUserCol", appUserCol);
				if (request.getParameter("user_id") != null) {
					request.setAttribute("user_id", request.getParameter("user_id"));
				}
			}
		}

		request.getRequestDispatcher("/userconfig.jsp").forward(request, response);
	}
}
