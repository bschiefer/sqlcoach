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

import de.sqlcoach.util.ViewResultSet;
import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.bean.TaskForm;
import de.sqlcoach.beans.DBConnectionService;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.beans.DBTaskService;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;

/**
 * The Class TaskAction.
 */
public class TaskAction extends Action {
	/** The log. */
	private static final Logger log = Logger.getLogger(TaskAction.class);

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

		final ExerciseForm param = new ExerciseForm(request);
		final TaskForm taskForm = (TaskForm) form;

		param.setRequestAttributes(request);
		request.removeAttribute("status");
		if (log.isInfoEnabled()) {
			log.info("TaskAction.execute, param=" + param + " taskForm=" + taskForm);
		}
		// cancel button pressed
		if (this.isCancelled(request)) {
			return mapping.findForward("forward");
		}

		Scenario scenario = null;

		final Task task = new Task();
		DBTaskService dbTaskService = DBRemoteEJBClient.getEJB(DBTaskService.class.getName(), DBTaskService.BEANNAME);
		DBScenarioService dbScenarioService = DBRemoteEJBClient.getEJB(DBScenarioService.class.getName(),
				DBScenarioService.BEANNAME);

		// get or update
		if (taskForm.getAction().equals("add") || taskForm.getAction().equals("update")) {
			if (!taskForm.getDescription().equals("")) {

				log.info("MPA taskForm.getQuery(): " + taskForm.getQuery());

				// TODO namen anpassen getTaskgroup
				Taskgroup taskgroup = new Taskgroup();
				taskgroup.setId(Long.valueOf(taskForm.getTaskgroupId()));
				task.setTaskgroup(taskgroup);
				task.setDescription(taskForm.getDescription());
				task.setQuery(taskForm.getQuery());
				task.setHint(taskForm.getHint());
				task.setHint_trials(taskForm.getHint_trials());

				// add
				if (taskForm.getAction().equals("add")) {
					dbTaskService.insert(task);
				}

				// update
				if (taskForm.getAction().equals("update")) {
					task.setId(Long.valueOf(taskForm.getId()));
					dbTaskService.update(task);
				}
			}

			// read ViewResultSet for admin query check
			// read Scenario (for reading Datasource)
			scenario = dbScenarioService.get(Long.valueOf(param.getScenarioId()));

		} else if (taskForm.getAction().equals("delete")) {
			task.setId(Long.valueOf(taskForm.getId()));
			dbTaskService.delete(task);
		}

		// get or update
		if (taskForm.getAction().equals("add") || taskForm.getAction().equals("update")) {
			// Trainee Solution as ViewResultSet
			DBConnectionService dbConnectionService = DBRemoteEJBClient.getEJB(DBConnectionService.class.getName(),
					DBConnectionService.BEANNAME);
			final ViewResultSet adminViewResultSet = dbConnectionService.get(taskForm.getQuery(), scenario);

			request.setAttribute("nameResultSet", "adminViewResultSet");
			request.setAttribute("adminViewResultSet", adminViewResultSet);
		}

		return mapping.findForward("forward");
	}
}
