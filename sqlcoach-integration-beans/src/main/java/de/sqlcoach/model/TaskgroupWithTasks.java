/*
 * Created on 24.03.2007 at 16:53:41
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

package de.sqlcoach.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import de.sqlcoach.model.Task;
import de.sqlcoach.model.Taskgroup;

/**
 * The Class TaskgroupWithTasks.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class TaskgroupWithTasks implements Serializable {

  private static final long serialVersionUID = 1L;

  /** The taskgroup. */
  private Taskgroup taskgroup;

  /** The task col. */
  private Collection<Task> taskCol = new ArrayList<Task>();

  /**
   * Add.
   * 
   * @param task
   *            the task
   */
  public void add(Task task) {
    this.taskCol.add(task);
  }

  /**
   * Gets the task col.
   * 
   * @return the task col
   */
  public Collection<Task> getTaskCol() {
    return this.taskCol;
  }

  /**
   * Sets the task col.
   * 
   * @param taskCol
   *            the new task col
   */
  public void setTaskCol(Collection<Task> taskCol) {
    this.taskCol = taskCol;
  }

  /**
   * Gets the taskgroup.
   * 
   * @return the taskgroup
   */
  public Taskgroup getTaskgroup() {
    return this.taskgroup;
  }

  /**
   * Sets the taskgroup.
   * 
   * @param taskgroup
   *            the new taskgroup
   */
  public void setTaskgroup(Taskgroup taskgroup) {
    this.taskgroup = taskgroup;
  }
}
