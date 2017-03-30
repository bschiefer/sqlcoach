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
