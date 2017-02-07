package de.sqlcoach.db.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Entity AppStatisticSuccessFail didn't exists in application Database
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
public class AppStatisticSuccessFail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private int success;
	
	@Transient
	private int fail;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFail() {
		return fail;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}

	@Override
	public String toString() {
		return "AppStatisticSuccessFail [success=" + success + ", fail=" + fail + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fail;
		result = prime * result + success;
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
		AppStatisticSuccessFail other = (AppStatisticSuccessFail) obj;
		if (fail != other.fail)
			return false;
		if (success != other.success)
			return false;
		return true;
	}
}
