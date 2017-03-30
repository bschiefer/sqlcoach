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
 * Created on 22.03.2007 at 01:23:42
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

package de.sqlcoach.util;

import javax.servlet.http.HttpSession;

import de.sqlcoach.db.entities.AppUser;

/**
 * The Class LoginCheck.
 * 
 * @author Florian Moritz, Christoph Gerstle
 * @version 0.1
 */
public class LoginCheck {
	
	/**
	 * Loggedin.
	 * 
	 * @param session
	 *            the session
	 * 
	 * @return true, if successful
	 */
	public static boolean loggedin(HttpSession session) {
		AppUser user = (AppUser) session.getAttribute("AppUser");
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is super admin.
	 * 
	 * @param session
	 *            the session
	 * 
	 * @return true, if is super admin
	 */
	public static boolean isSuperAdmin(HttpSession session) {
		AppUser user = (AppUser) session.getAttribute("AppUser");
		if (user == null) {
			return false;
		} else {
			if (user.getRole().equals(SQLCoachConf.SUPERADMIN_ROLENAME)) {
				return true;
			} else {
				return false;
			}
		}

	}

}
