/*
 * Created on 8.10.2007 at 13:08:46
 *
 * Authors:
 * Bernhard Schiefer
 *
 * Project: SQLcoach
 * Subject: Project Digital Media
 * Insitution: University of Applied Sciences Kaiserslautern, Zweibruecken - http://www.hs-kl.de
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */

package de.sqlcoach.util;

import de.sqlcoach.util.TextUtil;

import java.text.ParseException;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * The util-Class Parameter.
 * 
 * @author Bernhard Schiefer
 * @version 0.1
 */
public class ParamUtil {
  /** The log. */
  // private static final Logger log = Logger.getLogger(ParamUtil.class);

  /**
   * returns the value of a parameter, whose name is passed in "key".
   * if no parameter with that key-value exists, the value of an attribute with the given key is returned 
   * 
   * @param key
   *            the key of th value
   * @param request
   *            the request
   * 
   * @return the string
   */
  public static String setString(String key, HttpServletRequest request) {
    String member = "null";

    // look first for a parameter than for a attribute
    if (request.getParameter(key) != null) {
      member = request.getParameter(key);
    } else {
      if (request.getAttribute(key) != null) {
        member = (String)request.getAttribute(key);
      }
    }
    return member;
  }
  
  public static boolean isNull (String value) {
    return (value == null || value.equals("null"));
  }

  /**
   * Sets the int.
   * 
   * @param key
   *            the value
   * @param request
   *            the request
   * 
   * @return the int
   */
  public static Integer setInt(String key, HttpServletRequest request) {
    Integer member = null;

    // look first for a parameter than for a attribute
    if (request.getParameter(key) != null) {
      member = new Integer(request.getParameter(key));
    } else {
      if (request.getAttribute(key) != null) {
        member = (Integer) request.getAttribute(key);
      }
    }
    return member;
  }


  /**
   * Sets the date.
   * 
   * @param key
   *            the value
   * @param request
   *            the request
   * 
   * @return the date
   * 
   * @throws ParseException
   *             the parse exception
   */
  public static Date setDate(String key, HttpServletRequest request) throws ParseException {
    Date member = null;

    // look first for a parameter than for a attribute
    if (request.getParameter(key) != null) {
      member = TextUtil.stringToDate(request.getParameter(key));
    } else {
      if (request.getAttribute(key) != null) {
        System.out.println("value " + request.getAttribute(key));
        member = (Date)request.getAttribute(key);
      }
    }
    return member;
  }
}
