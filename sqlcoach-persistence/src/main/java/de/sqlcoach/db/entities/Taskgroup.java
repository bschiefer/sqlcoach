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
 * Entity Taskgroup, with Many To One relation to Scenario,
 * with Transient(didn't exists in database) field number. 
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
@Table(name = "TASKGROUP")
public class Taskgroup implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(Taskgroup.class);

	@Id
	private Long id = null;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="scenario_id", referencedColumnName="id")
	private Scenario scenario = null;

	@Column(nullable = false)
	private Integer rank = null;

	@Column(nullable = false)
	private String description = null;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateLastMod;
	
	/**
	 * http://stackoverflow.com/questions/4115861/how-to-use-or-annotate-a-dummy-field-in-a-jpa-entity-bean-which-is-not-supposed
	 */
	@Transient
	private Integer number;

	public Taskgroup() {
		LOG.debug("enter ScenarioTable");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenarioId) {
		this.scenario = scenarioId;
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
	
	@Transient
	public String getReferenceIdName() {
		return "scenario_id";
	}

	@Transient
	public Long getReferenceId() {
		return getScenario().getId();
	}

	@Override
	public String toString() {
		return "Taskgroup [id=" + id + ", scenarioId=" + scenario + ", rank=" + rank + ", description=" + description
				+ ", dateCreate=" + dateCreate + ", dateLastMod=" + dateLastMod + ", number=" + number + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((dateLastMod == null) ? 0 : dateLastMod.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((scenario == null) ? 0 : scenario.hashCode());
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
		Taskgroup other = (Taskgroup) obj;
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
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (scenario == null) {
			if (other.scenario != null)
				return false;
		} else if (!scenario.equals(other.scenario))
			return false;
		return true;
	}
}
