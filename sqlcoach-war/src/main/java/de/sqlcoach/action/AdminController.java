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
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import de.sqlcoach.bean.ExerciseForm;
import de.sqlcoach.beans.DBConnectionService;
import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.beans.DBScenarioTableService;
import de.sqlcoach.beans.DBTaskService;
import de.sqlcoach.beans.DBTaskgroupService;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;
import de.sqlcoach.util.MetaTable;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminController.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class AdminController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static final Logger log = Logger.getLogger(AdminController.class);

	/** The page. */
	private String page = null;

	private List<Task> taskCol = null;
	private List<Scenario> scenarioCol = null;
	private List<Taskgroup> taskgroupCol = null;
	private AppUser user = null;
	private String nextPage = null;
	private List<ScenarioTable> scenarioTableCol = null;

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
		super.init(poConfig);
		this.page = poConfig.getInitParameter("page");
	}

	@SuppressWarnings("unchecked")
	private void exerciseConfigAdminAndExercise(final HttpSession session, final ExerciseForm param,
			HttpServletRequest request, HttpServletResponse response, Scenario scenario)
			throws NamingException, SQLException, Exception {
		// exerciseconfig (admin) + exercise
		if (this.page.equals("exerciseconfig") || this.page.equals("exercise")) {
			// get
			scenarioCol = (List<Scenario>) session.getAttribute("scenarioCol");
			if (scenarioCol == null) {
				scenarioCol = dbScenarioService.getAll();
				session.setAttribute("scenarioCol", scenarioCol);
			}

			if (param.getView().equals("task")) {
				Task task = new Task();
				Taskgroup taskGroup = new Taskgroup();
				taskGroup.setId(Long.valueOf(param.getTaskgroupId()));
				task.setTaskgroup(taskGroup);
				taskCol = dbTaskService.getByTaskgroupId(task);
				TrainingController.load_user_statistics(request);
				request.setAttribute("taskCol", taskCol);
			}

			if (param.getView().equals("taskgroup") || param.getView().equals("task")) {
				taskgroupCol = dbTaskgroupService.getByScenarioId(scenario);
				request.setAttribute("taskgroupCol", taskgroupCol);
				// store choosen scenarioId in session
				session.setAttribute("scenarioId", scenario.getId().toString());
			}

			if (param.getView().equals("scenariotable")) {
				scenarioTableCol = dbScenarioTableService.getByScenarioId(scenario.getId());
				request.setAttribute("scenarioTableCol", scenarioTableCol);

				final Scenario scenarioTmp = dbScenarioService.get(scenario.getId());
				if (log.isInfoEnabled()) {
					log.info("external DataSource=" + scenarioTmp.getDatasource());
				}

				// Connection for scenario DataSource
				final List<MetaTable> metaTableCol = dbConnectionService.readAllTables(scenarioTmp.getDatasource());
					final String db_prodname = dbConnectionService.getDatabaseProductName(scenarioTmp.getDatasource());
					session.setAttribute("db_prodname", db_prodname);
					// update ScenarioTable
					if (param.getStatus().equals("update") && param.getView().equals("scenariotable")) {
						final Collection<ScenarioTable> updateCol = dbScenarioTableService
								.getByScenarioId(scenario.getId());

						final String[] scenarioTableUpdate = new String[updateCol.size()];
						int i = 0;
						for (ScenarioTable updateTable : updateCol) {
							scenarioTableUpdate[i++] = updateTable.getScenarioTable();
							System.out.println(scenarioTableUpdate[i - 1]);
						}
						request.setAttribute("scenarioTable", scenarioTableUpdate);
					}

					request.setAttribute("metaTableCol", metaTableCol);
			}

			exerciseConfigAdmin(param, request, response, scenario);
		}
	}

	private void exerciseConfigAdmin(final ExerciseForm param, HttpServletRequest request, HttpServletResponse response,
			Scenario scenario) throws ServletException, IOException {
		// exerciseconfig (admin)
		if (this.page.equals("exerciseconfig")) {
			if (user == null) { // Nicht angemeldet!
				log.warn("AdminController.service ILLEGAL, page=" + this.page + " param=" + param + " user=" + user);
				request.getRequestDispatcher("/failure.jsp").forward(request, response);
				return;
			}

			// update Scenario
			if (param.getStatus().equals("update") && param.getView().equals("scenario")) {
				Scenario scenarioUpdate = new Scenario();
				for (Scenario tmp : scenarioCol) {
					if (tmp.getId().equals(scenario.getId())) {
						scenarioUpdate = tmp;
					}
				}
				request.setAttribute("scenarioUpdate", scenarioUpdate);
			}

			// delete Scenario
			if (param.getStatus().equals("delete") && param.getView().equals("scenario")) {
				Scenario scenarioDelete = new Scenario();
				scenarioDelete = dbScenarioService.get(scenario.getId());
				request.setAttribute("scenarioDelete", scenarioDelete);
			}

			// update Taskgroup
			if (param.getStatus().equals("update") && param.getView().equals("taskgroup")) {
				Taskgroup taskgroupUpdate = new Taskgroup();
				for (Taskgroup tmp : taskgroupCol) {
					if (tmp.getId().equals(param.getTaskgroupId())) {
						taskgroupUpdate = tmp;
					}
				}
				request.setAttribute("taskgroupUpdate", taskgroupUpdate);
			}

			// delete Taskgroup
			if (param.getStatus().equals("delete") && param.getView().equals("taskgroup")) {
				Taskgroup taskgroupDelete = new Taskgroup();
				taskgroupDelete = dbTaskgroupService.get(param.getTaskgroupId());
				request.setAttribute("taskgroupDelete", taskgroupDelete);
			}

			// update Task
			if (param.getStatus().equals("update") && param.getView().equals("task")) {
				Task taskUpdate = new Task();
				for (Task tmp : taskCol) {
					if (tmp.getId().equals(param.getTaskId())) {
						taskUpdate = tmp;
					}
				}
				request.setAttribute("taskUpdate", taskUpdate);
			}

			// delete Task
			if (param.getStatus().equals("delete") && param.getView().equals("task")) {
				Task taskDelete = new Task();
				taskDelete = dbTaskService.get(Long.valueOf(param.getTaskId()));
				request.setAttribute("taskDelete", taskDelete);
			}

			if (param.getStatus().equals("export") && param.getView().equals("scenario")) {
				taskCol = dbTaskService.getByScenarioId(scenario.getId());
				request.setAttribute("taskCol", taskCol);
				request.setAttribute("scenarioId", scenario.getId());
				nextPage = "/export.jsp";
				if (log.isDebugEnabled()) {
					for (Task t : taskCol) {
						log.debug(t);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest , javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final ExerciseForm param = new ExerciseForm(request);
		Scenario scenario = new Scenario();
		if (!param.getScenarioId().equals("null")) {
			scenario.setId(Long.valueOf(param.getScenarioId()));
		}

		final HttpSession session = request.getSession();
		user = (AppUser) session.getAttribute("AppUser");

		if (log.isInfoEnabled()) {
			log.info("service ENTER, page=" + this.page + " param=" + param + " user=" + user);
		}

		nextPage = (this.page.equals("exerciseconfig")) ? "/exerciseconfig.jsp" : "/exercise.jsp";

		try {
			exerciseConfigAdminAndExercise(session, param, request, response, scenario);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (log.isInfoEnabled()) {
			log.info("service LEAVE, nextPage=" + nextPage);
		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}
