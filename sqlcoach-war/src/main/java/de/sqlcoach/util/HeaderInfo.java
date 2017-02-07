/* 
 * Created on 14.03.2007 at 00:56:21
 * 
 * Authors: 
 * Florian Moritz - http://www.flomedia.de
 * Christoph Gerstle - http://www.christophgerstle.de
 *
 * Project: SQLcoach
 * Subject: Project Digital Media
 * Institution: University of Applied Sciences Kaiserslautern, Zweibruecken - http://www.hs-kl.de
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */

package de.sqlcoach.util;

/**
 * The Class HeaderInfo.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class HeaderInfo {
	
	/** The Constant SEPARATOR. */
	public static final String SEPARATOR = " - ";

	/** The Constant DEFAULT_TITLE_TAG. */
	private static final String DEFAULT_TITLE_TAG = "header.empty";

	/** The title tag. */
	private static String titleTag = DEFAULT_TITLE_TAG;

	/**
	 * Gets the title tag.
	 * 
	 * @return the title tag
	 */
	public static String getTitleTag() {
		return titleTag;
	}

	/**
	 * Sets the title tag.
	 * 
	 * @param titleTag
	 *            the new title tag
	 */
	public static void setTitleTag(String titleTag) {
		HeaderInfo.titleTag = titleTag;
	}

	/**
	 * Reset title tag.
	 */
	public static void resetTitleTag() {
		HeaderInfo.titleTag = DEFAULT_TITLE_TAG;
	}

	/**
	 * Gets the separator.
	 * 
	 * @return the separator
	 */
	public static String getSeparator() {
		if (HeaderInfo.titleTag.equals(DEFAULT_TITLE_TAG)) {
			return "";
		} else {
			return SEPARATOR;
		}
	}
}
