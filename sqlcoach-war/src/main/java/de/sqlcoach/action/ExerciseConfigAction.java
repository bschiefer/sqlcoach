/*
 * Created on 15.03.2007 at 23:54:35
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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;

/**
 * The Class ExerciseConfigAction.
 * 
 * @author Florian Moritz, Christoph Gerstle
 * @version 0.1
 */
public class ExerciseConfigAction extends Action {

	private static final Logger log = Logger.getLogger(ExerciseConfigAction.class);

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

		final ExerciseForm param = (ExerciseForm) form;

		if (log.isInfoEnabled())
			log.info("ExerciseConfigAction.execute, param=" + param);

		String target = "success";

		if (param.getView().equals("taskgroup")) {
			@SuppressWarnings("unchecked")
			Collection<Scenario> scenarioCol = (Collection<Scenario>) request.getSession().getAttribute("scenarioCol");
			if (scenarioCol == null) { // pruefen, ob schon in Session
				DBScenarioService dbScenarioService = DBRemoteEJBClient.getEJB(DBScenarioService.class.getName(),
						DBScenarioService.BEANNAME);
				scenarioCol = dbScenarioService.getAll();
				request.getSession().setAttribute("scenarioCol", scenarioCol);
			}

			DBTaskgroupService dbTaskgroupService = DBRemoteEJBClient.getEJB(DBTaskgroupService.class.getName(),
					DBTaskgroupService.BEANNAME);
			final Collection<Taskgroup> taskgroupCol = dbTaskgroupService.getAll();
			request.setAttribute("taskgroupCol", taskgroupCol);
		}

		return mapping.findForward(target);
	}
}
