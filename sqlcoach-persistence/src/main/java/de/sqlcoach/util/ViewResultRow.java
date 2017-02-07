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
import java.util.ArrayList;

import de.sqlcoach.util.ViewResultRow;
import de.sqlcoach.util.ViewResultValue;

/**
 * The Class ViewResultRow.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class ViewResultRow implements Serializable{

  private static final long serialVersionUID = 1L;
  
  /** The row. */
  ArrayList<ViewResultValue> row = new ArrayList<ViewResultValue>();

  /**
   * Add.
   * 
   * @param value
   *            the value
   */
  public void add(ViewResultValue value) {
    this.row.add(value);
  }

  /**
   * Gets the row.
   * 
   * @return the row
   */
  public ArrayList<ViewResultValue> getRow() {
    return this.row;
  }

  /**
   * Sets the row.
   * 
   * @param row
   *            the new row
   */
  public void setRow(ArrayList<ViewResultValue> row) {
    this.row = row;
  }

  /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj instanceof ViewResultRow) {
      ViewResultRow o = (ViewResultRow)obj;
      if (this.row.equals(o.row)) {
        return true;
      }
    }
    return false;
  }

  // TODO override hashcode!!
}
