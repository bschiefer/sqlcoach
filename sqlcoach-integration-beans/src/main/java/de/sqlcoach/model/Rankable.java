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
