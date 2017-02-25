/*
 * Created on 23.03.2007 at 11:48:06
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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.ChangePasswordForm;
import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.beans.DBAppUserService;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.util.DBUtil;
import de.sqlcoach.util.LoginCheck;

/**
 * The Class ChangePasswordAction.
 * 
 * @author <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 */
public class ChangePasswordAction extends Action {

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
			// request.removeAttribute("status");

			return mapping.findForward("forward");
		}

		// new passwords match
		ChangePasswordForm changePasswordForm = (ChangePasswordForm) form;
		AppUser appUser = null;
		HttpSession session = request.getSession(true);
		if (changePasswordForm.getNewPassword().equals(changePasswordForm.getNewPasswordConfirm())) {
			DBAppUserService dbAppUserService = DBRemoteEJBClient.getEJB(DBAppUserService.class.getName(),
					DBAppUserService.BEANNAME);
			appUser = dbAppUserService.get(Long.valueOf(changePasswordForm.getAppUserId()));

			// super-admins are allowed to change password without giving
			// the old one
			if ((LoginCheck.isSuperAdmin(session)) ||
					(appUser.getPassword().equals(DBUtil.encrypt(changePasswordForm.getOldPassword())))) {

				appUser.setPassword(DBUtil.encrypt(changePasswordForm.getNewPassword()));
				dbAppUserService.update(appUser);
			} else {
				Alert.catchError("alert.error.oldpwdontmatch", request);
			}

		} else { // passwords do not match
			Alert.catchError("alert.error.newpwdontmatch", request);
		}
		return mapping.findForward("forward");
	}
}