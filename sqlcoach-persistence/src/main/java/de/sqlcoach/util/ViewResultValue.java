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
 * Created on 23.03.2007 at 15:57:19
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

package de.sqlcoach.util;

import java.io.Serializable;

import de.sqlcoach.util.ViewResultValue;

/**
 * The Class ViewResultValue.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class ViewResultValue implements Serializable{

  private static final long serialVersionUID = 1L;
  
  /** The value. */
  private String value;

  /**
   * Gets the value.
   * 
   * @return the value
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Sets the value.
   * 
   * @param value
   *            the new value
   */
  public void setValue(String value) {
    this.value = value;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj instanceof ViewResultValue) {
      ViewResultValue o = (ViewResultValue) obj;
      if (this.value.equals(o.value)) {
        return true;
      }
    }
    return false;
  }

  // TODO override hashcode!!
}
