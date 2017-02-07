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
