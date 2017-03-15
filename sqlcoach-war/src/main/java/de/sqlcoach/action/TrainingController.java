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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.bean.Alert;
import de.sqlcoach.bean.TrainingForm;
import de.sqlcoach.beans.DBAppStatisticService;
import de.sqlcoach.beans.DBAppUserService;
import de.sqlcoach.beans.DBConnectionService;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.beans.DBScenarioTableService;
import de.sqlcoach.beans.DBTaskService;
import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.AppStatistics;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.util.MetaTable;
import de.sqlcoach.util.ParamUtil;
import de.sqlcoach.util.ViewResultSet;

/**
 * The Class TrainingController.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class TrainingController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static final Logger log = Logger.getLogger(TrainingController.class);
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TrainingController.class);
	
	private static final Integer DEFAULT_SAMPLE_SOLUTION_HINT_COUNT = 3;

	@EJB
	private DBAppUserService dbAppUserService;

	@EJB
	private DBAppStatisticService dbAppStatisticService;

	@EJB
	private DBScenarioService dbScenarioService;

	@EJB
	private DBScenarioTableService dbScenarioTableService;

	@EJB
	private DBTaskService dbTaskService;

	@EJB
	private DBTaskgroupService dbTaskgroupService;

	@EJB
	private DBConnectionService dbConnectionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */

	public void init(ServletConfig poConfig) throws ServletException {
		log.info("MPA init");
		super.init(poConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("MPA service");

		TrainingForm tf = (TrainingForm) request.getAttribute("TrainingForm");

		if (tf == null) { // erster Aufruf von extern
			tf = new TrainingForm(request);
			request.setAttribute("TrainingForm", tf);
			log.info("service : tf == null => Neu erzeugt! ");
			// Falls Form null muss scenarioId woanders herkommen
		}

		final String scenarioId = (String) request.getSession().getAttribute("scenarioId");
		final String oldScenarioId = (String) request.getSession().getAttribute("oldScenarioId");

		if (log.isInfoEnabled())
			log.info("service ENTER scenarioId=" + scenarioId + " tf=" + tf);

		if (ParamUtil.isNull(scenarioId)) { // session timeout
			log.warn("service LEAVE scenarioId=" + scenarioId + " tf=" + tf);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		Scenario scenario = dbScenarioService.get(Long.valueOf(scenarioId));
		request.setAttribute("scenario", scenario);
		
		//check value sample solution hint count is set, else get default value
		Integer sampleSolutionHintCount = null;
		if(null != scenario.getSampleSolutionHintCount()) {
			sampleSolutionHintCount = scenario.getSampleSolutionHintCount();
		} else {
			sampleSolutionHintCount = DEFAULT_SAMPLE_SOLUTION_HINT_COUNT;
		}
		request.setAttribute("sampleSolutionHintCount", sampleSolutionHintCount);
		
		// Task
		Task task = ParamUtil.isNull(tf.getTaskId()) ? null : dbTaskService.get(Long.valueOf(tf.getTaskId()));
		request.setAttribute("task", task);

		// Taskgroup
		if (null != task) {
			tf.setTaskgroupId(task.getTaskgroup().getId().toString());
		}

		Taskgroup taskgroup = ParamUtil.isNull(tf.getTaskgroupId()) ? null
				: dbTaskgroupService.get(Long.valueOf(tf.getTaskgroupId()));

		request.setAttribute("taskgroup", taskgroup);

		// ScenarioTable Collection
		List<ScenarioTable> scenarioTableCol = null;
		List<MetaTable> metaTableCol = null;

		if (log.isInfoEnabled())
			log.info("service, scenarioId aus session scenarioId=" + scenarioId + " taskgroup=" + taskgroup);

		if (scenarioId != null && scenarioId.equals(oldScenarioId)) {
			scenarioTableCol = (List<ScenarioTable>) request.getSession().getAttribute("scenarioTableCol");
			metaTableCol = (List<MetaTable>) request.getSession().getAttribute("metaTableCol");
		} else { // Neues Szenario gewaehlt (bs)
			request.getSession().setAttribute("oldScenarioId", scenarioId);
			scenarioTableCol = dbScenarioTableService.getByScenarioId(Long.valueOf(scenarioId));
			request.getSession().setAttribute("scenarioTableCol", scenarioTableCol);
		}

		// MetaTable Collection
		// External Connection for scenario DataSource
		if (log.isInfoEnabled()) {
			log.info("external DataSource=" + scenario.getDatasource());
		}

		ViewResultSet viewResultSet = null;
		if (metaTableCol == null) { // Metainfo in Session puffern (bs)
			metaTableCol = dbConnectionService.readByScenarioTableCol(scenarioTableCol, scenario);
			request.getSession().setAttribute("metaTableCol", metaTableCol);
			final String db_prodname = dbConnectionService.getDatabaseProductName(scenario);
			final String db_prodversion = dbConnectionService.getDatabaseProductVersion(scenario);
			request.getSession().setAttribute("db_prodname", db_prodname);
			request.getSession().setAttribute("db_prodversion", db_prodversion);
		}

		// Sample Solution as ViewResultSet (based on admin query)
		if (null != task) { //if free training choosen then task == null
			try {
				log.info("task.getQuery(): " + task.getQuery());
				viewResultSet = (task != null) ? dbConnectionService.get(task.getQuery(), scenario) : null;
				request.getSession().setAttribute("sampleSolutionHint", task.getQuery());
			} catch (SQLException e) {
				log.error("viewResultSet: " + e);
			}
		}

		int resultCnt = (viewResultSet != null && viewResultSet.getRows() != null) ? viewResultSet.getRows().size() : 0;
		request.setAttribute("solutionViewResultSet", viewResultSet);
		request.setAttribute("solutionViewResultSetCount", resultCnt);

		// compare the two ViewResultSets of admin and trainee
		if (tf.getStatus().equals("check")) {
			ViewResultSet traineeSet = (ViewResultSet) request.getAttribute("traineeViewResultSet");
			boolean equals = false;
			if (log.isInfoEnabled())
				log.info("traineeSet.size=" + (traineeSet == null ? -1 : traineeSet.size()) + " viewResultSet.size="
						+ (viewResultSet == null ? -1 : viewResultSet.size()));

			if (traineeSet != null && viewResultSet != null) {
				if (traineeSet.equals(viewResultSet)) {
					equals = true;
					Alert.catchSuccess("alert.success.equal", request, response);
				} else {
					Alert.catchError("alert.error.notEqual", request);
				}
			}
			log.info("2 ViewResultSets are equal? -> " + equals);

			// add AppStatistic entry
			if (tf.getTaskId() != null && !tf.getTaskId().equals("null")) {
				final AppStatistics appStatistics = new AppStatistics();
				appStatistics.setQuery(tf.getQuery());
				appStatistics.setSessionID(request.getSession().getId());
				Date dateCreate = new Date();
				appStatistics.setDateCreate(dateCreate);
				Task taskTmp = new Task();
				taskTmp.setId(Long.valueOf(tf.getTaskId()));

				appStatistics.setTask(taskTmp);
				if(true == equals) {
					appStatistics.setSuccess('1');
				} else if(false == equals) {
					appStatistics.setSuccess('0');
				}
				

				dbAppStatisticService.insert(appStatistics);

				do_user_statistics(request, response, tf, equals);
			}
		}

		request.setAttribute("number", request.getParameter("number"));
		request.getRequestDispatcher("/training.jsp").forward(request, response);
	}

	// Administrates the number of trials in user_trials
	// as well as the number of successful attempts in user_success.
	// Depending on parameter success one or the other counter will be
	// increased.
	// The results are stored in HashMaps during the session.
	// If Cookies were allowed, the result is also stored in a Cookie,
	// so that the personal statistics will survive a closing
	// of the browser.
	private static void do_user_statistics(HttpServletRequest request, HttpServletResponse response, TrainingForm tf,
			boolean success) {
		if (log.isDebugEnabled())
			log.debug("do_user_statistics success=" + success);

		final Cookie[] userCookies = request.getCookies();

		HashMap<String, Integer> user_trials = checkInSession(request.getSession(), userCookies, "user_trials");
		HashMap<String, Integer> user_success = checkInSession(request.getSession(), userCookies, "user_success");

		LOG.info("user_trials: {}", user_trials);
		LOG.info("user_success: {}", user_success);

		final String trials_key = tf.getTaskId();
		Integer anz = (success) ? user_success.get(trials_key) : user_trials.get(trials_key);
		if (anz == null)
			anz = new Integer(1);
		else
			anz++;

		if (success)
			user_success.put(trials_key, anz);
		else
			user_trials.put(trials_key, anz);

		setCookie(response, "user_trials", user_trials);
		setCookie(response, "user_success", user_success);
	}

	public static void load_user_statistics(HttpServletRequest request) {
		if (log.isDebugEnabled())
			log.debug("load_user_statistics ");

		final Cookie[] userCookies = request.getCookies();

		checkInSession(request.getSession(), userCookies, "user_trials");
		checkInSession(request.getSession(), userCookies, "user_success");
	}

	// Read mapping from session / cookie
	@SuppressWarnings("unchecked")
	private static HashMap<String, Integer> checkInSession(final HttpSession session, final Cookie[] userCookies,
			final String name) {
		HashMap<String, Integer> map = (HashMap<String, Integer>) session.getAttribute(name);
		if (map == null) { // not in session - check cookie
			map = (HashMap<String, Integer>) getCookie(userCookies, name);

			if (map == null) { // no cookie found
				log.info("user_statistics no cookie found! " + name);
				map = new HashMap<String, Integer>();
			}
			session.setAttribute(name, map);
		}
		return map;
	}

	// Read serializable object from cookie
	private static Serializable getCookie(final Cookie[] userCookies, final String name) {
		Serializable result = null;
		final String cookie_str = getCookieValue(userCookies, name, null);
		if (log.isDebugEnabled())
			log.debug("getCookie name=" + name + " cookie_str=" + cookie_str);

		if (cookie_str == null) // not found
			return null;
		try {
			final byte[] buffer = DatatypeConverter.parseBase64Binary(cookie_str);
			final InputStream input = new ByteArrayInputStream(buffer);
			final ObjectInputStream in = new ObjectInputStream(input);
			while (in.available() > 0) {
				result = (Serializable) in.readObject();
			}
		} catch (Exception e) {
			log.error("getCookie ERROR at " + name + ": " + e);
		}
		return result;
	}

	// Write serializable object to cookie
	private static void setCookie(final HttpServletResponse response, final String name, final Serializable obj) {
		if (log.isDebugEnabled())
			log.debug("setCookie name=" + name + " obj=" + obj);

		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream out = new ObjectOutputStream(output);
			out.writeObject(obj);
			final String cookie_str = DatatypeConverter.printBase64Binary(output.toByteArray());
			if (log.isDebugEnabled())
				log.debug("setCookie out.writeObject(" + name + ")=" + cookie_str);
			final Cookie cookie = new Cookie(name, cookie_str);
			cookie.setMaxAge(3600 * 24 * 31);
			response.addCookie(cookie);
		} catch (IOException e) {
			log.error("setCookie ERROR at  " + name + ": " + e);
		}
	}

	// read Cookie value from array by its name
	private static String getCookieValue(Cookie[] cookies, String cookieName, String defaultValue) {
		if (log.isDebugEnabled())
			log.debug("getCookieValue ENTER cookieName=" + cookieName);

		if (cookies == null) {
			log.debug("getCookieValue cookies == NULL");
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
				return (cookie.getValue());
		}
		return (defaultValue);
	}
}