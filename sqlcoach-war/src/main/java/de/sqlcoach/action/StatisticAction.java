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

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.StatisticForm;
import de.sqlcoach.bean.StatisticParameter;

/**
 * The Class StatisticAction.
 */
public class StatisticAction extends Action {

	/** The log. */
	private static final Logger log = Logger.getLogger(StatisticAction.class);

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
		
		log.info("MPA execute");
		
		final StatisticForm thisForm = (StatisticForm) form;
		StatisticParameter param = new StatisticParameter(request);
		try {
			param.setDates(request);
			param.setDateFrom(thisForm.getDateFrom()); // Form Value
			param.setDateTill(thisForm.getDateTill());
		} catch (ParseException e) {
			param = new StatisticParameter();
			// param.resetDates();
			Alert.catchError("alert.error.dateFormat", request);
		} finally {
			param.setScenarioId(thisForm.getScenarioId());
		}

		param.setRequestAttributes(request);
		if (log.isInfoEnabled()) {
			log.info("StatisticAction, param=" + param);
		}

		return mapping.findForward("forward");
	}
}