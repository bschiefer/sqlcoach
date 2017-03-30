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
 * Created on 22.03.2007 at 20:35:31
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

/**
 * The Class MetaTableColumn.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class MetaTableColumn implements Serializable {
  private static final long serialVersionUID = 1L;

  /** The name. */
	private String name;

	/** The datatype. */
	private String datatype;

	/** The datasize. */
	private int datasize;

	/** The digits. */
	private int digits;

	/** The nullable. */
	private boolean nullable;

	/** The primary key. */
	private boolean primaryKey;

	/** The foreign key table name. */
	private String foreignKeyTableName;

	/** The foreign key column name. */
	private String foreignKeyColumnName;

	/**
	 * Gets the foreign key column name.
	 * 
	 * @return the foreign key column name
	 */
	public String getForeignKeyColumnName() {
		return this.foreignKeyColumnName;
	}

	/**
	 * Sets the foreign key column name.
	 * 
	 * @param foreignKeyColumnName
	 *            the new foreign key column name
	 */
	public void setForeignKeyColumnName(String foreignKeyColumnName) {
		this.foreignKeyColumnName = foreignKeyColumnName;
	}

	/**
	 * Gets the foreign key table name.
	 * 
	 * @return the foreign key table name
	 */
	public String getForeignKeyTableName() {
		return this.foreignKeyTableName;
	}

	/**
	 * Sets the foreign key table name.
	 * 
	 * @param foreignKeyTableName
	 *            the new foreign key table name
	 */
	public void setForeignKeyTableName(String foreignKeyTableName) {
		this.foreignKeyTableName = foreignKeyTableName;
	}

	/**
	 * Checks if is primary key.
	 * 
	 * @return true, if is primary key
	 */
	public boolean getPrimaryKey() {
		return this.primaryKey;
	}

	/**
	 * Sets the primary key.
	 * 
	 * @param primaryKey
	 *            the new primary key
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the datasize.
	 * 
	 * @return the datasize
	 */
	public int getDatasize() {
		return this.datasize;
	}

	/**
	 * Sets the datasize.
	 * 
	 * @param datasize
	 *            the new datasize
	 */
	public void setDatasize(int datasize) {
		this.datasize = datasize;
	}

	/**
	 * Gets the datatype.
	 * 
	 * @return the datatype
	 */
	public String getDatatype() {
		return this.datatype;
	}

	/**
	 * Sets the datatype.
	 * 
	 * @param datatype
	 *            the new datatype
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	/**
	 * Gets the digits.
	 * 
	 * @return the digits
	 */
	public int getDigits() {
		return this.digits;
	}

	/**
	 * Sets the digits.
	 * 
	 * @param digits
	 *            the new digits
	 */
	public void setDigits(int digits) {
		this.digits = digits;
	}

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
	 * Checks if is nullable.
	 * 
	 * @return true, if is nullable
	 */
	public boolean isNullable() {
		return this.nullable;
	}

	/**
	 * Sets the nullable.
	 * 
	 * @param nullable
	 *            the new nullable
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name + " [" + this.datatype + ", " + this.datasize + ", "
				+ this.digits + ", NULL=" + this.nullable + ", PRI.KEY="
				+ this.primaryKey + ", FOR.KEY.TAB=" + this.foreignKeyTableName
				+ ", FOR.KEY.COLUMN=" + this.foreignKeyColumnName + "]";
	}

}
