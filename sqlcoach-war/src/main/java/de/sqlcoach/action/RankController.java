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

/**
 * @author Florian Moritz, <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 * 
 */

package de.sqlcoach.action;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.beans.DBTaskService;
import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;

/**
 * The Class RankController.
 */
public class RankController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	protected static Logger log = Logger.getLogger(RankController.class);

	@EJB
	private DBTaskService dbTaskService;

	@EJB
	private DBTaskgroupService dbTaskgroupService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest , javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final ExerciseForm param = new ExerciseForm(request);
		param.setRequestAttributes(request);
		request.removeAttribute("status");
		if (log.isInfoEnabled()) {
			log.info("RankController, param=" + param);
		}
		Boolean result = false;

		if (param.getView().equals("taskgroup")) {
			final Taskgroup taskgroup = dbTaskgroupService.get(param.getTaskgroupId());
			if (param.getStatus().equals("rank_up")) {
				result = dbTaskgroupService.rankUp(taskgroup);
			}
			if (param.getStatus().equals("rank_down")) {
				result = dbTaskgroupService.rankDown(taskgroup);
			}
		}

		if (param.getView().equals("task")) {
			final Task task = dbTaskService.get(Long.valueOf(param.getTaskId()));
			log.info("MPA taskId: " + param.getTaskId());
			if (param.getStatus().equals("rank_up")) {
				result = dbTaskService.rankUp(task);
			}
			if (param.getStatus().equals("rank_down")) {
				result = dbTaskService.rankDown(task);
			}
		}

		if (result == false) {
			Alert.catchError("alert.error.rank", request);
		} else {
			Alert.catchSuccess("alert.success.rank", request, response);
		}
		// forward
		request.getRequestDispatcher("/exerciseconfig").forward(request, response);
	}
}