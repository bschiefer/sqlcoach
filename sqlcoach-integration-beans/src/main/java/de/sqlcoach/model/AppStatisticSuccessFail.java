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
 * Created on 24.03.2007 at 17:26:00
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

/**
 * The Class AppStatisticSuccessFail.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class AppStatisticSuccessFail implements Serializable {
	
  private static final long serialVersionUID = 1L;

  /** The success. */
	private int success;

	/** The fail. */
	private int fail;

	/**
	 * Gets the fail.
	 * 
	 * @return the fail
	 */
	public int getFail() {
		return this.fail;
	}

	/**
	 * Sets the fail.
	 * 
	 * @param fail
	 *            the new fail
	 */
	public void setFail(int fail) {
		this.fail = fail;
	}

	/**
	 * Gets the success.
	 * 
	 * @return the success
	 */
	public int getSuccess() {
		return this.success;
	}

	/**
	 * Sets the success.
	 * 
	 * @param success
	 *            the new success
	 */
	public void setSuccess(int success) {
		this.success = success;
	}
}
