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
