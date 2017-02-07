/* 
 * Created on 17.03.2007 at 15:52:43
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


/**
 * The Class AppUser.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class AppUser implements Serializable{
	
  private static final long serialVersionUID = 1L;

  /** The id. */
	protected int id;

	/** The nickname. */
	protected String nickname;

	/** The password. */
	protected String password;

	/** The title. */
	protected String title;

	/** The firstname. */
	protected String firstname;

	/** The lastname. */
	protected String lastname;

	/** The email. */
	protected String email;

	/** The role. */
	protected String role;

	/** The datecreate. */
	protected Timestamp datecreate;

	/** The datelastmod. */
	protected Timestamp datelastmod;

	/**
	 * Gets the datecreate.
	 * 
	 * @return the datecreate
	 */
	public Timestamp getDatecreate() {
		return this.datecreate;
	}

	/**
	 * Sets the datecreate.
	 * 
	 * @param datecreate
	 *            the new datecreate
	 */
	public void setDatecreate(Timestamp datecreate) {
		this.datecreate = datecreate;
	}

	/**
	 * Gets the datelastmod.
	 * 
	 * @return the datelastmod
	 */
	public Timestamp getDatelastmod() {
		return this.datelastmod;
	}

	/**
	 * Sets the datelastmod.
	 * 
	 * @param datelastmod
	 *            the new datelastmod
	 */
	public void setDatelastmod(Timestamp datelastmod) {
		this.datelastmod = datelastmod;
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
	 *            the new email
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
	 *            the new firstname
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
	 *            the new id
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
	 *            the new lastname
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
	 *            the new nickname
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
	 *            the new password
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
	 *            the new role
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
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
