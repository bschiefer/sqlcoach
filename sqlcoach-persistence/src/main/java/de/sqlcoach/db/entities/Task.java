package de.sqlcoach.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity Task, with Many To One relation to Taskgroup,
 * with Transient(didn't exists in database) fields: 
 * number, successQueries, failedQueries, solution_trials, idToString. 
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
@Table(name = "TASK")
public class Task implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(Task.class);

	@Id
	private Long id = null;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="taskgroup_id", referencedColumnName="id")
	private Taskgroup taskgroup = null;

	@Column(nullable = false)
	private Integer rank = null;

	@Column(nullable = false)
	private String description = null;

	@Column(nullable = false)
	private String query = null;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateLastMod;
	
	@Column(nullable = false)
	private String hint = null;

	@Column(nullable = false)
	private Integer hint_trials = null;
	
	/**
	 * http://stackoverflow.com/questions/4115861/how-to-use-or-annotate-a-dummy-field-in-a-jpa-entity-bean-which-is-not-supposed
	 */
	@Transient
	private Integer number;
	
	@Transient
	private Integer successQueries;
	
	@Transient
	private Integer failedQueries;
	
	@Transient
	private Integer solution_trials;
	
	/**
	 * BUGFIX for exercise.jsp jstl variable (user_trials and user_success) need data type String 
	 */
	@Transient
	private String idToString = null;

	public Task() {
		LOG.debug("enter ScenarioTable");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Taskgroup getTaskgroup() {
		return taskgroup;
	}

	public void setTaskgroup(Taskgroup taskgroupId) {
		this.taskgroup = taskgroupId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Integer getHint_trials() {
		return hint_trials;
	}

	public void setHint_trials(Integer hint_trials) {
		this.hint_trials = hint_trials;
	}

	public java.util.Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(java.util.Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public java.util.Date getDateLastMod() {
		return dateLastMod;
	}

	public void setDateLastMod(java.util.Date dateLastMod) {
		this.dateLastMod = dateLastMod;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Integer getSuccessQueries() {
		return successQueries;
	}

	public void setSuccessQueries(Integer successQueries) {
		this.successQueries = successQueries;
	}

	public Integer getFailedQueries() {
		return failedQueries;
	}

	public void setFailedQueries(Integer failedQueries) {
		this.failedQueries = failedQueries;
	}

	public Integer getSolution_trials() {
		return solution_trials;
	}

	public void setSolution_trials(Integer solution_trials) {
		this.solution_trials = solution_trials;
	}
	
	@Transient
	public String getReferenceIdName() {
		return "taskgroup_id";
	}

	@Transient
	public Long getReferenceId() {
		return getTaskgroup().getId();
	}

	public String getIdToString() {
		return id.toString();
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskgroupId=" + taskgroup + ", rank=" + rank + ", description=" + description
				+ ", query=" + query + ", dateCreate=" + dateCreate + ", dateLastMod=" + dateLastMod + ", hint=" + hint
				+ ", hint_trials=" + hint_trials + ", number=" + number + ", successQueries=" + successQueries
				+ ", failedQueries=" + failedQueries + ", solution_trials=" + solution_trials + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((dateLastMod == null) ? 0 : dateLastMod.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((failedQueries == null) ? 0 : failedQueries.hashCode());
		result = prime * result + ((hint == null) ? 0 : hint.hashCode());
		result = prime * result + ((hint_trials == null) ? 0 : hint_trials.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((solution_trials == null) ? 0 : solution_trials.hashCode());
		result = prime * result + ((successQueries == null) ? 0 : successQueries.hashCode());
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
		Task other = (Task) obj;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (dateLastMod == null) {
			if (other.dateLastMod != null)
				return false;
		} else if (!dateLastMod.equals(other.dateLastMod))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (failedQueries == null) {
			if (other.failedQueries != null)
				return false;
		} else if (!failedQueries.equals(other.failedQueries))
			return false;
		if (hint == null) {
			if (other.hint != null)
				return false;
		} else if (!hint.equals(other.hint))
			return false;
		if (hint_trials == null) {
			if (other.hint_trials != null)
				return false;
		} else if (!hint_trials.equals(other.hint_trials))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (solution_trials == null) {
			if (other.solution_trials != null)
				return false;
		} else if (!solution_trials.equals(other.solution_trials))
			return false;
		if (successQueries == null) {
			if (other.successQueries != null)
				return false;
		} else if (!successQueries.equals(other.successQueries))
			return false;
		if (taskgroup == null) {
			if (other.taskgroup != null)
				return false;
		} else if (!taskgroup.equals(other.taskgroup))
			return false;
		return true;
	}
}
