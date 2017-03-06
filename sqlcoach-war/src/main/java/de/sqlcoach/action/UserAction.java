/*
 * Created on 23.03.2007 at 17:09:34
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.bean.UserForm;
import de.sqlcoach.beans.DBAppUserService;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.util.DBUtil;
import de.sqlcoach.util.LoginCheck;

/**
 * The Class UserAction.
 * 
 * @author <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 */
public class UserAction extends Action {
	private static final Logger log = Logger.getLogger(UserAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// cancel button pressed
		if (this.isCancelled(request)) {

			ExerciseForm param = new ExerciseForm(request);
			param.setRequestAttributes(request);
			request.removeAttribute("status");

			return mapping.findForward("forward");
		}

		UserForm userForm = (UserForm) form;
		AppUser appUserModel = new AppUser();
		HttpSession session = request.getSession(true);

		// only super admins are allowed to change admin data
		final boolean isSuperAdmin = LoginCheck.isSuperAdmin(session);
		if (!isSuperAdmin) {
			Alert.catchError("alert.error.denied" + userForm.getAction(), request);
			return mapping.findForward("forward");
		}

		appUserModel.setNickname(userForm.getNickname());
		appUserModel.setFirstname(userForm.getFirstname());
		appUserModel.setLastname(userForm.getLastname());
		appUserModel.setRole(userForm.getRole());
		appUserModel.setEmail(userForm.getEmail());
		appUserModel.setTitle(userForm.getTitle());

		DBAppUserService dbAppUserService = DBRemoteEJBClient.getEJB(DBAppUserService.class.getName(),
				DBAppUserService.BEANNAME);

		try {
			if (userForm.getAction().equals("update")) {
				appUserModel.setId(Long.valueOf(userForm.getAppUserId()));
				dbAppUserService.update(appUserModel);
			} else if (userForm.getAction().equals("add")) { // add new user
				appUserModel.setPassword(DBUtil.encrypt(userForm.getPassword()));
				dbAppUserService.insert(appUserModel);
			} else if (userForm.getAction().equals("delete")) { // delete user
				appUserModel.setId(Long.valueOf(userForm.getAppUserId()));
				dbAppUserService.delete(appUserModel);
			}
		} catch (Exception e) {
			Alert.catchError("alert.error.user", request);
			log.error("execute: " + e);
		}
		

		return mapping.findForward("forward");
	}
}