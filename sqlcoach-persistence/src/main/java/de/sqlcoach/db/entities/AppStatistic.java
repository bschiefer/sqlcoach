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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity AppStatistics, with Many To One relation to Task
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
@Table(name = "APP_STATISTIC")
public class AppStatistic implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AppStatistic.class);

	//TODO no autoincrement, need solution for insert
	@Id
	private Long id = null;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="task_id", referencedColumnName="id")
	private Task task = null;

	@Column(nullable = false)
	private Character success = null;
	
	@Column(nullable = false)
	private String query = null;
	
	@Column(nullable = false)
	private String session_id = null;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateCreate;

	
	public AppStatistic() {
		LOG.debug("enter AppStatistics");
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Task getTask() {
		return task;
	}


	public void setTask(Task taskId) {
		this.task = taskId;
	}


	public Character getSuccess() {
		return success;
	}


	public void setSuccess(Character success) {
		this.success = success;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getSessionID() {
		return session_id;
	}


	public void setSessionID(String sessionID) {
		this.session_id = sessionID;
	}


	public java.util.Date getDateCreate() {
		return dateCreate;
	}


	public void setDateCreate(java.util.Date dateCreate) {
		this.dateCreate = dateCreate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Generated toString()
	 */
	@Override
	public String toString() {
		return "AppStatistics [id=" + id + ", task=" + task + ", success=" + success + ", query=" + query
				+ ", sessionID=" + session_id + ", dateCreate=" + dateCreate + "]";
	}


	/**
	 * Generated hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + ((session_id == null) ? 0 : session_id.hashCode());
		result = prime * result + ((success == null) ? 0 : success.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
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
		AppStatistic other = (AppStatistic) obj;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (session_id == null) {
			if (other.session_id != null)
				return false;
		} else if (!session_id.equals(other.session_id))
			return false;
		if (success == null) {
			if (other.success != null)
				return false;
		} else if (!success.equals(other.success))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		return true;
	}
}
