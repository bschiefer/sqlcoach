/* 
 * Created on 12.03.2007 at 12:21:21
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * The Class TextUtil.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class TextUtil {
	
	/**
	 * Split.
	 * 
	 * @param original
	 *            the original
	 * @param separator
	 *            the separator
	 * 
	 * @return the string[]
	 */
	public static String[] split(String original, String separator) {
		Vector<String> nodes = new Vector<String>();
		String temp;
		// Parse nodes into vector
		int index = original.indexOf(separator);
		while (index >= 0) {
			nodes.addElement(original.substring(0, index));
			temp = original.substring(index + separator.length());
			index = temp.indexOf(separator);
		}
		// Get the last node
		nodes.addElement(original);

		// Create splitted string array
		String[] result = new String[nodes.size()];
		if (nodes.size() > 0) {
			for (int loop = 0; loop < nodes.size(); loop++)
				result[loop] = nodes.elementAt(loop);
		}
		return result;
	}

	/** The Constant DATE_PATTERN. */
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Date to string.
	 * 
	 * @param date
	 *            the date
	 * 
	 * @return the string
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_PATTERN); // 2006-10-27 um 23:54:10
		return sdf.format(date);
	}

	/**
	 * String to date.
	 * 
	 * @param s
	 *            the s
	 * 
	 * @return the date
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date stringToDate(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		Date date;
		date = format.parse(s);
		return date;
	}
}
