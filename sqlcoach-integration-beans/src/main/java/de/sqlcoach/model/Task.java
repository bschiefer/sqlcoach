/*
 * Created on 18.03.2007 at 16:41:28
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
import java.sql.Timestamp;

import de.sqlcoach.model.Rankable;

/**
 * The Class Task.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class Task implements Rankable, Serializable {

  private static final long serialVersionUID = 1L;

  /** The id. */
  protected String id;

  /** The taskgroup id. */
  protected String taskgroupId;

  /** The rank. */
  protected int rank;

  /** The description. */
  protected String description;

  /** The query. */
  protected String query;

  /** The datecreate. */
  protected Timestamp datecreate;

  /** The datelastmod. */
  protected Timestamp datelastmod;

  /** The number. */
  protected int number;

  /** The success queries. */
  protected int successQueries;

  /** The failed queries. */
  protected int failedQueries;

  /** The hint. */
  protected String hint;

  /** The number of trials after which the hint is displayed. */
  protected int hint_trials;

  /** The number of trials after which the solution is displayed. */
  protected int solution_trials; // 0 stands for never

  public String toString() {
    return "[id=" + this.id + " taskgroupId=" + this.taskgroupId + 
           " rank=" + this.rank + " description=" + this.description +
           " hint=" + this.hint + " hint_trials=" + this.hint_trials + " solution_trials=" + this.solution_trials+"]";
  }
  
  public String getHint() {
    return this.hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public int getHint_trials() {
    return this.hint_trials;
  }

  /**
   * Sets the number of trails until the hint is displayed.
   * 
   * @param hint_trials
   *            the  number of trials until the hint is displayed
   */
  public void setHint_trials(int hint_trials) {
    this.hint_trials = hint_trials;
  }

  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public Timestamp getDatecreate() {
    return this.datecreate;
  }

  public void setDatecreate(Timestamp datecreate) {
    this.datecreate = datecreate;
  }

  public Timestamp getDatelastmod() {
    return this.datelastmod;
  }

  public void setDatelastmod(Timestamp datelastmod) {
    this.datelastmod = datelastmod;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /* (non-Javadoc)
   * @see de.sqlcoach.model.Rankable#getId()
   */
  public String getId() {
    return this.id;
  }

  /* (non-Javadoc)
   * @see de.sqlcoach.model.Rankable#setId(int)
   */
  public void setId(String id) {
    this.id = id;
  }

  public String getQuery() {
    return this.query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  /* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#getRank()
	 */

  public int getRank() {
    return this.rank;
  }

  /* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#setRank(int)
	 */

  public void setRank(int rank) {
    this.rank = rank;
  }

  public String getTaskgroupId() {
    return this.taskgroupId;
  }

  public void setTaskgroupId(String taskgroupId) {
    this.taskgroupId = taskgroupId;
  }

  /* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#getReferenceId()
	 */

  public String getReferenceId() {
    return this.taskgroupId;
  }

  /* (non-Javadoc)
	 * @see de.sqlcoach.model.Rankable#getReferenceIdName()
	 */

  public String getReferenceIdName() {
    return "taskgroup_id";
  }

  public int getFailedQueries() {
    return this.failedQueries;
  }

  public void setFailedQueries(int failedQueries) {
    this.failedQueries = failedQueries;
  }

  public int getSuccessQueries() {
    return this.successQueries;
  }

  public void setSuccessQueries(int successQueries) {
    this.successQueries = successQueries;
  }

  public int getSolution_trials() {
    return solution_trials;
  }

  public void setSolution_trials(int solution_trials) {
    this.solution_trials = solution_trials;
  }
}
