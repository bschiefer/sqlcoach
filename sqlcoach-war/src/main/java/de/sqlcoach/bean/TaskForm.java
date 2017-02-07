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

package de.sqlcoach.bean;

/**
 * Form bean for the Login Entry Screen.
 * 
 */
import org.apache.struts.action.ActionForm;

/**
 * The Class TaskForm.
 */
public class TaskForm extends ActionForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5066331541211175398L;

	/** The action. */
	private String action = "add";

	/** The id. */
	private String id;

	/** The taskgroup id. */
	private String taskgroupId;

	/** The rank. */
	private int rank;

	/** The description. */
	private String description;

	/** The query. */
	private String query;

	/** The datasource. */
	private String datasource;

  private String hint;
  private int hint_trials;

	@Override
  public String toString() {
    return "[action=" + action + ", id=" + id + ", taskgroupId=" + taskgroupId + ", rank=" + rank
        + ", description=" + description + ", query=" + query + ", datasource=" + datasource + ", hint=" + hint
        + ", hint_trials=" + hint_trials + "]";
  }

  /**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action
	 *            the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the datasource.
	 * 
	 * @return the datasource
	 */
	public String getDatasource() {
		return this.datasource;
	}

	/**
	 * Sets the datasource.
	 * 
	 * @param datasource
	 *            the new datasource
	 */
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the query.
	 * 
	 * @return the query
	 */
	public String getQuery() {
		return this.query;
	}

	/**
	 * Sets the query.
	 * 
	 * @param query
	 *            the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Gets the rank.
	 * 
	 * @return the rank
	 */
	public int getRank() {
		return this.rank;
	}

	/**
	 * Sets the rank.
	 * 
	 * @param rank
	 *            the new rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Gets the taskgroup id.
	 * 
	 * @return the taskgroup id
	 */
	public String getTaskgroupId() {
		return this.taskgroupId;
	}

	/**
	 * Sets the taskgroup id.
	 * 
	 * @param taskgroupId
	 *            the new taskgroup id
	 */
	public void setTaskgroupId(String taskgroupId) {
		this.taskgroupId = taskgroupId;
	}

  public void setHint(String hint) {
    this.hint = hint;
  }

  public String getHint() {
    return hint;
  }

  public void setHint_trials(int hint_trials) {
    this.hint_trials = hint_trials;
  }

  public int getHint_trials() {
    return hint_trials;
  }
}
