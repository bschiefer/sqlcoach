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
import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.TrainingForm;
import de.sqlcoach.beans.DBConnectionService;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;

/**
 * The Class TrainingAction.
 */
public class TrainingAction extends Action {

	/** The log. */
	private static final Logger log = Logger.getLogger(TrainingAction.class);

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

		TrainingForm tf = (TrainingForm) form;

		if (tf == null || tf.getScenarioId() == null) { // erster Aufruf
			tf = new TrainingForm(request);
		}
		request.setAttribute("TrainingForm", tf);
		if (log.isInfoEnabled())
			log.info("TrainingAction ENTER form=" + tf);

		final String scenarioId = (String) request.getSession().getAttribute("scenarioId");

		// get Scenario
		Scenario scenario = (Scenario) request.getSession().getAttribute("scenario");
		if (scenario == null || scenario.getId() == null || !scenario.getId().equals(scenarioId)) {
			DBScenarioService dbScenarioService = DBRemoteEJBClient.getEJB(DBScenarioService.class.getName(),
					DBScenarioService.BEANNAME);
			scenario = dbScenarioService.get(Long.valueOf(scenarioId));
			request.getSession().setAttribute("scenario", scenario);
		}

		// establish connection with own Datasource
		// Trainee Solution as ViewResultSet

		/**
		 * TODO getDatasource mitgeben
		 */
		DBConnectionService dbConnectionService = DBRemoteEJBClient.getEJB(DBConnectionService.class.getName(),
				DBConnectionService.BEANNAME);
		final ViewResultSet traineeViewResultSet = dbConnectionService.get(tf.getQuery(), scenario.getDatasource());

		if (traineeViewResultSet == null)
			Alert.catchError("alert.error.wrongQuery", request);

		int resultCnt = (traineeViewResultSet != null && traineeViewResultSet.getRows() != null)
				? traineeViewResultSet.getRows().size() : 0;

		request.setAttribute("traineeViewResultSet", traineeViewResultSet);
		request.setAttribute("traineeViewResultSetCount", resultCnt);

		// explain plan
		final ViewResultSet explain = dbConnectionService.getExplainPlan(tf.getQuery(), scenario.getDatasource());

		if (explain == null)
			Alert.catchError("alert.error.wrongQuery", request);

		int resultExplainCnt = (explain != null && explain.getRows() != null) ? explain.getRows().size() : 0;

		request.setAttribute("explainResultSet", explain);
		request.setAttribute("explainResultSetCount", resultExplainCnt);

		request.setAttribute("query", tf.getQuery());
		return mapping.findForward("forward");
	}
}