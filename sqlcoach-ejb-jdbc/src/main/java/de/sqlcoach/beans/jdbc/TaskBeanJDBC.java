package de.sqlcoach.beans.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.jdbc.interfaces.DBTaskService;
import de.sqlcoach.db.jdbc.DBTask;
import de.sqlcoach.model.Task;

/**
 * Stateless Bean serves as interface between sqlcoach-integration-test-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBTaskService.class)
public class TaskBeanJDBC extends BaseBeanJDBC implements DBTaskService {
	private static final Logger LOG = LoggerFactory.getLogger(TaskBeanJDBC.class);

	@Override
	public Task get(String id) {
		Task task = null;
		try (Connection connection = getConnection()) {
			task =  DBTask.get(connection, id);
		} catch (SQLException e) {
			LOG.error("Task get id: " + e);
		}

		return task;
	}

	@Override
	public List<Task> getAll() {
		List<Task> tasks = null;
		try (Connection connection = getConnection()) {
			tasks =  DBTask.getAll(connection);
		} catch (SQLException e) {
			LOG.error("Task getAll: " + e);
		}

		return tasks;
	}

	@Override
	public List<Task> getByTaskgroupId(String id) {
		List<Task> tasks = null;
		try (Connection connection = getConnection()) {
			tasks =  DBTask.getByTaskgroupId(connection, id);
		} catch (SQLException e) {
			LOG.error("Task getByTaskgroupId: " + e);
		}

		return tasks;
	}

	@Override
	public List<Task> getByScenarioId(String id) {
		List<Task> tasks = null;
		try (Connection connection = getConnection()) {
			tasks =  DBTask.getByScenarioId(connection, id);
		} catch (SQLException e) {
			LOG.error("Task getByScenarioId: " + e);
		}

		return tasks;
	}

	@Override
	public int add(Task model) {
		int addResults = -2;
		try (Connection connection = getConnection()) {
			addResults =  DBTask.add(connection, model);
		} catch (SQLException e) {
			LOG.error("Task getByScenarioId: " + e);
		}

		return addResults;
	}

	@Override
	public int update(Task model) {
		int updateResults = -2;
		try (Connection connection = getConnection()) {
			updateResults =  DBTask.update(connection, model);
		} catch (SQLException e) {
			LOG.error("Task getByScenarioId: " + e);
		}

		return updateResults;
	}

	@Override
	public int delete(Task model) {
		int updateResults = -2;
		try (Connection connection = getConnection()) {
			updateResults =  DBTask.delete(connection, model);
		} catch (SQLException e) {
			LOG.error("Task getByScenarioId: " + e);
		}

		return updateResults;
	}
}
