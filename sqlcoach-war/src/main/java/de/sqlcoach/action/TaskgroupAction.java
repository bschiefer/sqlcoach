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

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.bean.TaskgroupForm;
import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;

/**
 * The Class TaskgroupAction.
 */
public class TaskgroupAction extends Action {

	/** The log. */
	private static final Logger log = Logger.getLogger(TaskgroupAction.class);

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

		ExerciseForm param = new ExerciseForm(request);
		param.setRequestAttributes(request);
		request.removeAttribute("status");
		final TaskgroupForm taskgroupForm = (TaskgroupForm) form;
		final String action = taskgroupForm.getAction();

		if (log.isInfoEnabled()) {
			log.info("TaskgroupAction ENTER param=" + param + " action=" + action);
		}
		// cancel button pressed
		if (this.isCancelled(request)) {
			return mapping.findForward("forward");
		}

		final Taskgroup model = new Taskgroup();

		DBTaskgroupService dbTaskgroupService = DBRemoteEJBClient.getEJB(DBTaskgroupService.class.getName(),
				DBTaskgroupService.BEANNAME);

		if (action.equals("add") || action.equals("update")) {
			if (!taskgroupForm.getDescription().equals("")) {

				model.getScenario().setId(Long.valueOf(taskgroupForm.getScenarioId()));
				model.setDescription(taskgroupForm.getDescription());

				if (action.equals("add")) {
					dbTaskgroupService.insert(model);
				}

				if (action.equals("update")) {
					model.setId(Long.valueOf(taskgroupForm.getId()));
					dbTaskgroupService.update(model);
				}
			}
		}

		if (action.equals("delete")) {
			model.setId(Long.valueOf(taskgroupForm.getId()));
			dbTaskgroupService.delete(model);
		}

		return mapping.findForward("forward");
	}
}
