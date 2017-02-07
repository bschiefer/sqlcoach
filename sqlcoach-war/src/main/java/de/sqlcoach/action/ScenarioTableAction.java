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
import de.sqlcoach.bean.ScenarioTableForm;
import de.sqlcoach.beans.DBScenarioTableService;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;

/**
 * The Class ScenarioTableAction.
 * 
 * @author Florian Moritz, <a href="http://www.christophgerstle.de">Christoph
 *         Gerstle</a>
 */
public class ScenarioTableAction extends Action {

	/** The log. */
	private static final Logger log = Logger.getLogger(ScenarioTableAction.class);

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
		if (log.isInfoEnabled()) {
			log.info("ScenarioTableAction, " + param);
		}

		param.setRequestAttributes(request);
		request.removeAttribute("status");

		// cancel button pressed
		if (this.isCancelled(request)) {

			return mapping.findForward("forward");
		}

		ScenarioTableForm scenarioTableForm = (ScenarioTableForm) form;
		ScenarioTable model = new ScenarioTable();

		DBScenarioTableService dbScenarioTableService = DBRemoteEJBClient.getEJB(DBScenarioTableService.class.getName(),
				DBScenarioTableService.BEANNAME);

		if (scenarioTableForm.getAction().equals("add") || scenarioTableForm.getAction().equals("update")) {

			// TODO rename getId to getScenarioID
			model.getId().setId(Long.valueOf(scenarioTableForm.getScenarioId()));

			// delete all with this scenarioId
			// resultDelete = DBScenarioTable.delete(cn.getCn(),
			// scenarioTableForm.getScenarioId());
			/**
			 * TODO check delete
			 */
			dbScenarioTableService.delete(model);

			String[] tableAr = scenarioTableForm.getScenarioTable();
			for (int i = 0; i < tableAr.length; i++) {
				model.setScenarioTable(tableAr[i]);
				System.out.println(model);
				// add each
				dbScenarioTableService.insert(model);
			}
		}

		return mapping.findForward("forward");
	}
}
