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

package de.sqlcoach.util;

import java.io.Serializable;
import java.util.ArrayList;

import de.sqlcoach.util.ViewResultRow;
import de.sqlcoach.util.ViewResultSet;

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
