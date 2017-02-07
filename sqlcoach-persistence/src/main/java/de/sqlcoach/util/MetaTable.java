/* 
 * Created on 22.03.2007 at 20:32:11
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
import java.util.Collection;
import java.util.Iterator;

/**
 * The Class MetaTable.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class MetaTable implements Serializable {
  private static final long serialVersionUID = 1L;

  /** The name. */
	private String name;

	/** The column col. */
	private Collection<MetaTableColumn> columnCol;

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the column col.
	 * 
	 * @return the column col
	 */
	public Collection<MetaTableColumn> getColumnCol() {
		return this.columnCol;
	}

	/**
	 * Sets the column col.
	 * 
	 * @param columnCol
	 *            the new column col
	 */
	public void setColumnCol(Collection<MetaTableColumn> columnCol) {
		this.columnCol = columnCol;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
  public String toString() {
    String s = "\n\n--------MetaTableName=" + this.name.toString() + "-----------------------\n";

    if (this.columnCol != null) {
      Iterator<MetaTableColumn> it = this.columnCol.iterator();
      while (it.hasNext()) {
        MetaTableColumn column = it.next();
        s += " ->" + column.toString() + "\n";
      }
    }
    return s;
  }

}
