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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity AppUser
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Entity
@Table(name = "APP_USER")
public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AppUser.class);

	@Id
	private Long id = null;

	@Column(nullable = false)
	private String nickname = null;

	@Column(nullable = false)
	private String password = null;

	private String title = null;

	private String firstname = null;

	private String lastname = null;

	private String email = null;

	private String role = null;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date datecreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private java.util.Date dateLastMod;

	public AppUser() {
		LOG.debug("enter AppUser");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public java.util.Date getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(java.util.Date datecreate) {
		this.datecreate = datecreate;
	}

	public java.util.Date getDatelastmod() {
		return dateLastMod;
	}

	public void setDatelastmod(java.util.Date datelastmod) {
		this.dateLastMod = datelastmod;
	}

	/**
	 * Generated toString()
	 */
	@Override
	public String toString() {
		return "AppUser [id=" + id + ", nickname=" + nickname + ", password=" + password + ", title=" + title
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", role=" + role
				+ ", datecreate=" + datecreate + ", datelastmod=" + dateLastMod + "]";
	}

	/**
	 * Generated hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datecreate == null) ? 0 : datecreate.hashCode());
		result = prime * result + ((dateLastMod == null) ? 0 : dateLastMod.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		AppUser other = (AppUser) obj;
		if (datecreate == null) {
			if (other.datecreate != null)
				return false;
		} else if (!datecreate.equals(other.datecreate))
			return false;
		if (dateLastMod == null) {
			if (other.dateLastMod != null)
				return false;
		} else if (!dateLastMod.equals(other.dateLastMod))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
