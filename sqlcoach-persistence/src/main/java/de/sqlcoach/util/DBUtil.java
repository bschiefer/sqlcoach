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
