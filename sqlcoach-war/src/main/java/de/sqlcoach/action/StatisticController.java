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

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import de.sqlcoach.db.entities.AppStatisticSuccessFail;
import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.StatisticParameter;
import de.sqlcoach.beans.DBAppStatisticService;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.beans.DBTaskService;
import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.db.entities.TaskgroupWithTasks;

/**
 * The Class StatisticController.
 * 
 * @author Florian Moritz, Christoph Gerstle
 * @version 0.1
 */
public class StatisticController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static final Logger log = Logger.getLogger(StatisticController.class);

	@EJB
	private DBAppStatisticService dbAppStatisticService;

	@EJB
	private DBScenarioService dbScenarioService;

	@EJB
	private DBTaskService dbTaskService;

	@EJB
	private DBTaskgroupService dbTaskgroupService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig poConfig) throws ServletException {
		super.init(poConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest , javax.servlet.http.HttpServletResponse)
	 */

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final StatisticParameter param = new StatisticParameter(request);
		try {
			param.setDates(request);
		} catch (ParseException e1) {
			log.error("execute Error: ", e1);
			Alert.catchError("alert.error.dateFormat", request);
		}
		if (log.isInfoEnabled())
			log.info("StatisticController ENTER param=" + param);

		param.setRequestAttributes(request);

		final Collection<TaskgroupWithTasks> taskgroupWithTasksCol = new ArrayList<TaskgroupWithTasks>();
		int maxValue = 1;

		// get
		final Scenario scenario = dbScenarioService.get(Long.valueOf(param.getScenarioId()));
		request.setAttribute("scenario", scenario);
		final Collection<Taskgroup> taskgroupCol = dbTaskgroupService.getByScenarioId(scenario);

		for (Taskgroup tg : taskgroupCol) { // run through all Task groups
			Task task = new Task();
			task.setId(tg.getId());
			Collection<Task> taskColTmp = dbTaskService.getByTaskgroupId(task);
			Collection<Task> taskColFinal = new ArrayList<Task>();

			for (Task t : taskColTmp) { // run through each task
				AppStatisticSuccessFail sf = dbAppStatisticService.getByTaskId(t.getId(), param.getDateFrom(),
						param.getDateTill());

				t.setSuccessQueries(sf.getSuccess());
				t.setFailedQueries(sf.getFail());

				if (sf.getFail() > maxValue) {
					maxValue = sf.getFail();
				}

				if (sf.getSuccess() > maxValue) {
					maxValue = sf.getSuccess();
				}
				taskColFinal.add(t);
			}

			TaskgroupWithTasks twt = new TaskgroupWithTasks();
			twt.setTaskgroup(tg);
			twt.setTaskCol(taskColFinal);

			taskgroupWithTasksCol.add(twt);
		}

		request.setAttribute("taskgroupWithTasksCol", taskgroupWithTasksCol);
		request.setAttribute("maxValue", "" + maxValue);

		request.setAttribute("dateFrom", param.getDateFrom());
		request.setAttribute("dateTill", param.getDateTill());
		if (log.isInfoEnabled()) {
			log.info("StatisticController LEAVE param=" + param);
		}

		request.getRequestDispatcher("/statistic.jsp").forward(request, response);
	}
}
