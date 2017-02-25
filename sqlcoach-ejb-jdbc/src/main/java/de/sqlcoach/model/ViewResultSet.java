/*
 * Created on 23.03.2007 at 15:54:44
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
import java.util.ArrayList;

import de.sqlcoach.model.ViewResultRow;
import de.sqlcoach.model.ViewResultSet;

/**
 * The Class ViewResultSet.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class ViewResultSet implements Serializable{
  private static final long serialVersionUID = 1L;

  int num_rows = 0;

  /** The field names. */
  ViewResultRow fieldNames;

  /** The rows. */
  ArrayList<ViewResultRow> rows = new ArrayList<ViewResultRow>();

  /**
   * Add.
   * 
   * @param row
   *            the row
   */
  public void add(ViewResultRow row) {
    this.num_rows++;
    this.rows.add(row);
  }

  /**
   * Gets the field names.
   * 
   * @return the field names
   */
  public ViewResultRow getFieldNames() {
    return this.fieldNames;
  }

  /**
   * Sets the field names.
   * 
   * @param fieldNames
   *            the new field names
   */
  public void setFieldNames(ViewResultRow fieldNames) {
    this.fieldNames = fieldNames;
  }

  /**
   * Gets the rows.
   * 
   * @return the rows
   */
  public ArrayList<ViewResultRow> getRows() {
    return this.rows;
  }

  /**
   * Sets the rows.
   * 
   * @param rows
   *            the new rows
   */
  public void setRows(ArrayList<ViewResultRow> rows) {
    this.rows = rows;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj instanceof ViewResultSet) {
      ViewResultSet o = (ViewResultSet)obj;
      if (this.fieldNames.equals(o.fieldNames) && this.rows.equals(o.rows)) {
        return true;
      }
    }
    return false;
  }
  
  public int size() {
    return num_rows;
  }

  // TODO override hashcode!!
}
