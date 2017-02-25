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
 * Entity ScenarioTable, with Many To One relation to Scenario
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
@Table(name = "SCENARIO_TABLE")
public class ScenarioTable implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ScenarioTable.class);


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="scenario_id", referencedColumnName="id")
	private Scenario scenario = null;
	
	@Id
	private String scenario_table = null;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateCreate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateLastMod;

	public ScenarioTable() {
		LOG.debug("enter ScenarioTable");
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public String getScenarioTable() {
		return scenario_table;
	}

	public void setScenarioTable(String scenarioTable) {
		this.scenario_table = scenarioTable;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Generated toString()
	 */
	@Override
	public String toString() {
		return "ScenarioTable [id=" + scenario + ", scenarioTable=" + scenario_table + ", dateCreate="
				+ dateCreate + ", dateLastMod=" + dateLastMod + "]";
	}

	/**
	 * Generated hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((dateLastMod == null) ? 0 : dateLastMod.hashCode());
		result = prime * result + ((scenario == null) ? 0 : scenario.hashCode());
		result = prime * result + ((scenario_table == null) ? 0 : scenario_table.hashCode());
		return result;
	}

	/**
	 * Generated equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScenarioTable other = (ScenarioTable) obj;
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
		if (scenario == null) {
			if (other.scenario != null)
				return false;
		} else if (!scenario.equals(other.scenario))
			return false;
		if (scenario_table == null) {
			if (other.scenario_table != null)
				return false;
		} else if (!scenario_table.equals(other.scenario_table))
			return false;
		return true;
	}
}
