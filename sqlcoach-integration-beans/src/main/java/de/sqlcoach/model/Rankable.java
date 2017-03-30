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
 * Created on 22.03.2007 at 11:39:07
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


/**
 * The Interface Rankable.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public interface Rankable {
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId();

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id);

	/**
	 * Gets the rank.
	 * 
	 * @return the rank
	 */
	public int getRank();

	/**
	 * Sets the rank.
	 * 
	 * @param rank
	 *            the new rank
	 */
	public void setRank(int rank);
	
	/**
	 * Gets the reference id name.
	 * 
	 * @return the reference id name
	 */
	public String getReferenceIdName();
	
	/**
	 * Gets the reference id.
	 * 
	 * @return the reference id
	 */
	public String getReferenceId();
}
