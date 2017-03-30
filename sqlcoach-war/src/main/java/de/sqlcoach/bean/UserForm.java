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
/* 
 * Created on 23.03.2007 at 17:03:56
 *  
 * Florian Moritz, Chistoph Gerstle
 *
 * Project SQLcoach
 * Subject Project Digital Media
 * University of Applied Sciences Kaiserslautern
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */
package de.sqlcoach.bean;

import org.apache.struts.action.ActionForm;

/**
 * The Class UserForm.
 * 
 * @author <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 */
public class UserForm extends ActionForm {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5186060651729435883L;

	/** The action. */
	private String action;
	
	/** The app user id. */
	private int appUserId;
	
	/** The id. */
	private int id;

	/** The nickname. */
	private String nickname;

	/** The password. */
	private String password;

	/** The title. */
	private String title;

	/** The firstname. */
	private String firstname;

	/** The lastname. */
	private String lastname;

	/** The email. */
	private String email;

	/** The role. */
	private String role;

	/**
	 * Gets the serial version UID.
	 * 
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the app user id.
	 * 
	 * @return the appUserId
	 */
	public int getAppUserId() {
		return this.appUserId;
	}

	/**
	 * Sets the app user id.
	 * 
	 * @param appUserId
	 *            the appUserId to set
	 */
	public void setAppUserId(int appUserId) {
		this.appUserId = appUserId;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the firstname.
	 * 
	 * @return the firstname
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * Sets the firstname.
	 * 
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the lastname.
	 * 
	 * @return the lastname
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * Sets the lastname.
	 * 
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Gets the nickname.
	 * 
	 * @return the nickname
	 */
	public String getNickname() {
		return this.nickname;
	}

	/**
	 * Sets the nickname.
	 * 
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role.
	 * 
	 * @return the role
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * Sets the role.
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
