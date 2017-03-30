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
import de.sqlcoach.bean.ScenarioTableForm;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.beans.DBScenarioTableService;
import de.sqlcoach.db.entities.Scenario;
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
		ScenarioTable scenarioTable = new ScenarioTable();

		DBScenarioTableService dbScenarioTableService = DBRemoteEJBClient.getEJB(DBScenarioTableService.class.getName(),
				DBScenarioTableService.BEANNAME);

		if (scenarioTableForm.getAction().equals("add") || scenarioTableForm.getAction().equals("update")) {

			Scenario scenario = new Scenario();
			scenario.setId(Long.valueOf(scenarioTableForm.getScenarioId()));
			scenarioTable.setScenario(scenario);

			// delete all with this scenarioId
			// resultDelete = DBScenarioTable.delete(cn.getCn(),
			// scenarioTableForm.getScenarioId());

			dbScenarioTableService.deleteByScenarioId(Long.valueOf(scenarioTableForm.getScenarioId()));

			String[] tableAr = scenarioTableForm.getScenarioTable();
			for (int i = 0; i < tableAr.length; i++) {
				scenarioTable.setScenarioTable(tableAr[i]);
				System.out.println(scenarioTable);
				// add each
				dbScenarioTableService.insert(scenarioTable);
			}
		}
		
		//update scenarioCol
		DBScenarioService dbScenarioService = DBRemoteEJBClient.getEJB(DBScenarioService.class.getName(),
				DBScenarioService.BEANNAME);	
		request.setAttribute("scenarioCol", dbScenarioService.getAll());

		return mapping.findForward("forward");
	}
}
