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
package de.sqlcoach.db.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.db.entities.Task;
import de.sqlcoach.db.entities.Taskgroup;

/**
 * Entity TaskgroupWithTasks didn't exists in application Database
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
public class TaskgroupWithTasks implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(TaskgroupWithTasks.class);

	@Transient
	private Taskgroup taskgroup = null;

	@Transient
	private Collection<Task> taskCol = new ArrayList<Task>();

	public TaskgroupWithTasks() {
		LOG.debug("enter TaskgroupWithTasks");
	}

	public void add(Task task) {
		this.taskCol.add(task);
	}

	public Taskgroup getTaskgroup() {
		return taskgroup;
	}

	public void setTaskgroup(Taskgroup taskgroup) {
		this.taskgroup = taskgroup;
	}

	public Collection<Task> getTaskCol() {
		return taskCol;
	}

	public void setTaskCol(Collection<Task> taskCol) {
		this.taskCol = taskCol;
	}

	@Override
	public String toString() {
		return "TaskgroupWithTasks [taskgroup=" + taskgroup + ", taskCol=" + taskCol + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskCol == null) ? 0 : taskCol.hashCode());
		result = prime * result + ((taskgroup == null) ? 0 : taskgroup.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskgroupWithTasks other = (TaskgroupWithTasks) obj;
		if (taskCol == null) {
			if (other.taskCol != null)
				return false;
		} else if (!taskCol.equals(other.taskCol))
			return false;
		if (taskgroup == null) {
			if (other.taskgroup != null)
				return false;
		} else if (!taskgroup.equals(other.taskgroup))
			return false;
		return true;
	}
}
