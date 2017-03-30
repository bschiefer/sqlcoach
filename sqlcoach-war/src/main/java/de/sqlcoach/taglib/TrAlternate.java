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
 * Created on 11.03.2007 at 19:43:59
 *
 * Florian Moritz, Chistoph Gerstle
 *
 * Project SQLcoach
 * Subject Project Digital Media
 * University of Applied Sciences Kaiserslautern
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */
package de.sqlcoach.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Constructed with the help of:
 * http://www.torsten-horn.de/techdocs/jsp-taglibs.htm
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class

TrAlternate extends BodyTagSupport {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -7191499392570656512L;

  /** The row count. */
  private static int rowCount = 0;

  /**
   * Gets the row.
   * 
   * @return the row
   */
  public int getRow() {
    return rowCount;
  }

  /**
   * Sets the row.
   * 
   * @param rowCount
   *            the new row
   */
  public void setRow(int rowCount) {
    if (rowCount == 0 || rowCount == 1) {
      TrAlternate.rowCount = rowCount;
    }
  }

  /**
   * Reset row.
   */
  public static void resetRow() {
    rowCount = 0;
  }

  /**
   * Next row.
   */
  public void nextRow() {
    rowCount++;
    rowCount %= 2;
  }

  /* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */

  public int doStartTag() throws JspTagException {
    try {
      String s = "<tr class=\"alternate" + rowCount + "\">";
      JspWriter out = this.pageContext.getOut();
      out.println(s);
      nextRow();

      return EVAL_BODY_BUFFERED;
    } catch (IOException ex) {
      return SKIP_BODY;
    }
  }

  /* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doAfterBody()
	 */

  public int doAfterBody() throws JspTagException {
    try {
      BodyContent bc = getBodyContent();
      if (null != bc) {
        String s = bc.getString();
        bc.clearBody();
        bc.getEnclosingWriter().write(s);
      }
      return SKIP_BODY;
    } catch (IOException ex) {
      return SKIP_BODY;
    }
  }

  /* (non-Javadoc)
   * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
   */

  public int doEndTag() throws JspTagException {
    try {
      String s = "</tr>";
      JspWriter out = this.pageContext.getOut();
      out.println(s);
      return EVAL_BODY_BUFFERED;
    } catch (IOException ex) {
      return EVAL_PAGE;
    }
  }

}
