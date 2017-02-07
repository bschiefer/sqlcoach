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
import de.sqlcoach.bean.ScenarioForm;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;

/**
 * The Class ScenarioAction.
 * 
 * @author Florian Moritz, <a href="http://www.christophgerstle.de">Christoph
 *         Gerstle</a>
 */
public class

ScenarioAction extends Action {

	/** The log. */
	private static final Logger log = Logger.getLogger(ScenarioAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping , org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */

	@SuppressWarnings("boxing")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		final ExerciseForm param = new ExerciseForm(request);
		if (log.isInfoEnabled()) {
			log.info("ScenarioAction, " + param);
		}

		param.setRequestAttributes(request);
		request.removeAttribute("status");

		// cancel button pressed
		if (this.isCancelled(request)) {
			return mapping.findForward("forward");
		}

		final ScenarioForm scenarioForm = (ScenarioForm) form;

		Scenario model = new Scenario();
		DBScenarioService dbScenarioService = DBRemoteEJBClient.getEJB(DBScenarioService.class.getName(),
				DBScenarioService.BEANNAME);

		if (scenarioForm.getAction().equals("add") || scenarioForm.getAction().equals("update")) {
			if (!scenarioForm.getDescription().equals("") || !scenarioForm.getDatasource().equals("")) {

				model.getAppUser().setId(Long.valueOf(scenarioForm.getAppUserId()));
				model.setDescription(scenarioForm.getDescription());
				model.setDatasource(scenarioForm.getDatasource());

				// add
				if (scenarioForm.getAction().equals("add")) {
					dbScenarioService.insert(model);
				}

				// update
				if (scenarioForm.getAction().equals("update")) {
					model.setId(Long.valueOf(scenarioForm.getId()));
					dbScenarioService.update(model);
				}

				// request for 2nd page scenariotables
				request.setAttribute("view", "scenariotable");
				request.setAttribute("status", scenarioForm.getStatus());

				// if add we have no ScenarioId, because it is just created
				// - so we have to find it by the unique description
				if (scenarioForm.getAction().equals("add")) {
					scenarioForm
							.setScenarioId(dbScenarioService.getByDescription(scenarioForm.getDescription()).getId().toString());
				}
				request.setAttribute("scenario_id", scenarioForm.getScenarioId());
			}
		} else if (scenarioForm.getAction().equals("delete")) {
			model.setId(Long.valueOf(scenarioForm.getId()));
			dbScenarioService.delete(model);
		}

		return mapping.findForward("forward");
	}
}