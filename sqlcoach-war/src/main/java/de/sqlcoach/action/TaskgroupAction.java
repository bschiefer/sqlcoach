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

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.bean.TaskgroupForm;
import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.Scenario;
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

		final Taskgroup taskgroup = new Taskgroup();

		DBTaskgroupService dbTaskgroupService = DBRemoteEJBClient.getEJB(DBTaskgroupService.class.getName(),
				DBTaskgroupService.BEANNAME);

		if (action.equals("add") || action.equals("update")) {
			if (!taskgroupForm.getDescription().equals("")) {
				Scenario scenario = new Scenario();
				scenario.setId(Long.valueOf(taskgroupForm.getScenarioId()));
				taskgroup.setScenario(scenario);
				taskgroup.setDescription(taskgroupForm.getDescription());

				if (action.equals("add")) {
					dbTaskgroupService.insert(taskgroup);
				}

				if (action.equals("update")) {
					taskgroup.setId(Long.valueOf(taskgroupForm.getId()));
					dbTaskgroupService.update(taskgroup);
				}
			}
		}

		if (action.equals("delete")) {
			taskgroup.setId(Long.valueOf(taskgroupForm.getId()));
			dbTaskgroupService.delete(taskgroup);
		}

		return mapping.findForward("forward");
	}
}
