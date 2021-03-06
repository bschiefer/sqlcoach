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
 * Entity Scenario, with Many To One relation to AppUser,
 * with Transient(didn't exists in database) field appUserName. 
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
@Table(name = "SCENARIO")
public class Scenario implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(Scenario.class);

	@Id
	private Long id = null;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "app_user_id", referencedColumnName = "id")
	private AppUser appUser = null;

	@Column(nullable = false)
	private String description = null;

	@Column(nullable = false)
	private String datasource = null;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateLastMod;

	private String databaseProductName = null;

	private Integer sampleSolutionHintCount = null;

	@Transient
	private String appUserName;

	public Scenario() {
		LOG.debug("enter Scenario");
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
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

	public String getDatabaseProductName() {
		return databaseProductName;
	}

	public void setDatabaseProductName(String databaseProductName) {
		this.databaseProductName = databaseProductName;
	}
	
	public Integer getSampleSolutionHintCount() {
		return sampleSolutionHintCount;
	}

	public void setSampleSolutionHintCount(Integer sampleSolutionHintCount) {
		this.sampleSolutionHintCount = sampleSolutionHintCount;
	}

	public String getAppUserName() {
		return appUserName;
	}

	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}

	@Override
	public String toString() {
		return "Scenario [id=" + id + ", appUser=" + appUser + ", description=" + description + ", datasource=" + datasource
				+ ", dateCreate=" + dateCreate + ", dateLastMod=" + dateLastMod + ", databaseProductName=" + databaseProductName
				+ ", sampleSolutionHintCount=" + sampleSolutionHintCount + ", appUserName=" + appUserName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appUser == null) ? 0 : appUser.hashCode());
		result = prime * result + ((appUserName == null) ? 0 : appUserName.hashCode());
		result = prime * result + ((databaseProductName == null) ? 0 : databaseProductName.hashCode());
		result = prime * result + ((datasource == null) ? 0 : datasource.hashCode());
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((dateLastMod == null) ? 0 : dateLastMod.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sampleSolutionHintCount == null) ? 0 : sampleSolutionHintCount.hashCode());
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
		Scenario other = (Scenario) obj;
		if (appUser == null) {
			if (other.appUser != null)
				return false;
		} else if (!appUser.equals(other.appUser))
			return false;
		if (appUserName == null) {
			if (other.appUserName != null)
				return false;
		} else if (!appUserName.equals(other.appUserName))
			return false;
		if (databaseProductName == null) {
			if (other.databaseProductName != null)
				return false;
		} else if (!databaseProductName.equals(other.databaseProductName))
			return false;
		if (datasource == null) {
			if (other.datasource != null)
				return false;
		} else if (!datasource.equals(other.datasource))
			return false;
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
		if (sampleSolutionHintCount == null) {
			if (other.sampleSolutionHintCount != null)
				return false;
		} else if (!sampleSolutionHintCount.equals(other.sampleSolutionHintCount))
			return false;
		return true;
	}
}
