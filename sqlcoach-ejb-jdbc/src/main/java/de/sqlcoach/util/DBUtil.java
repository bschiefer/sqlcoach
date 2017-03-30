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
 * Created on 14.03.2007 at 14:11:06
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.sqlcoach.util.DBUtil;

/**
 * DBUtil: Utility Class for Database actions.
 * 
 * @author Florian Moritz, <a href="http://www.christophgerstle.de">Christoph
 *         Gerstle</a>
 * @version 1.2
 */
public class DBUtil {
	
	/** The log. */
	private static Log log = LogFactory.getLog(DBUtil.class);

	/**
	 * Gets the now.
	 * 
	 * @return the now
	 */
	public static long getNow() {
		return System.currentTimeMillis();
	}

	/**
	 * returns a md5 fingerprinted string.
	 * 
	 * @param s
	 *            String to encrypt
	 * 
	 * @return encrypted String
	 */
  public static String encrypt(String s) {
    byte[] bytearr = s.getBytes();
    String encrypted = new String();
    try {
      bytearr = MessageDigest.getInstance("md5").digest(bytearr);
      StringBuffer sb = new StringBuffer(bytearr.length * 2);
      int i, upper, bottom; // halfbytes
      for (i = 0; i < bytearr.length; i++) {
        upper = (bytearr[i] & 0xf0) >>> 4;
        bottom = bytearr[i] & 0x0f;
        sb.append(Integer.toString(upper, 16) + Integer.toString(bottom, 16));
      }
      encrypted = sb.toString();
    } catch (NoSuchAlgorithmException nsa) {
      log.error("NoSuchAlgorithmException:" + nsa.getMessage());
    }
    return encrypted;
  }
}
