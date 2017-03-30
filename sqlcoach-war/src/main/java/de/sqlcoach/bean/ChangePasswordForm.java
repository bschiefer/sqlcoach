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
 * Created on 23.03.2007 at 11:45:21
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
 * The Class ChangePasswordForm.
 * 
 * @author <a href="http://www.christophgerstle.de">Christoph Gerstle</a>
 */
public class ChangePasswordForm extends ActionForm {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8608775134849315073L;

  /** The action. */
  private String action;

  /** The app user id. */
  private int appUserId;

  /** The old password. */
  private String oldPassword;

  /** The new password. */
  private String newPassword;

  /** The new password confirm. */
  private String newPasswordConfirm;

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
   * Gets the app user id.
   * 
   * @return the app user id
   */
  public int getAppUserId() {
    return this.appUserId;
  }

  /**
   * Sets the app user id.
   * 
   * @param appUserId
   *            the new app user id
   */
  public void setAppUserId(int appUserId) {
    this.appUserId = appUserId;
  }

  /**
   * Gets the new password.
   * 
   * @return the new password
   */
  public String getNewPassword() {
    return this.newPassword;
  }

  /**
   * Sets the new password.
   * 
   * @param newPassword
   *            the new new password
   */
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  /**
   * Gets the new password confirm.
   * 
   * @return the new password confirm
   */
  public String getNewPasswordConfirm() {
    return this.newPasswordConfirm;
  }

  /**
   * Sets the new password confirm.
   * 
   * @param newPasswordConfirm
   *            the new new password confirm
   */
  public void setNewPasswordConfirm(String newPasswordConfirm) {
    this.newPasswordConfirm = newPasswordConfirm;
  }

  /**
   * Gets the old password.
   * 
   * @return the old password
   */
  public String getOldPassword() {
    return this.oldPassword;
  }

  /**
   * Sets the old password.
   * 
   * @param oldPassword
   *            the new old password
   */
  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }
}
