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

TdForm extends BodyTagSupport {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8667666074371709797L;

  /** The Constant LEFT. */
  private static final String LEFT = "formLeft";

  /** The Constant RIGHT. */
  private static final String RIGHT = "formRight";

  /** The area. */
  private String area = LEFT;

  /** The width. */
  private String width = null;

  /**
   * Gets the width.
   * 
   * @return the width
   */
  public String getWidth() {
    return this.width;
  }

  /**
   * Sets the width.
   * 
   * @param width
   *            the new width
   */
  public void setWidth(String width) {
    this.width = width;
  }

  /**
   * Gets the area.
   * 
   * @return the area
   */
  public String getArea() {
    return this.area;
  }

  /**
   * Sets the area.
   * 
   * @param area
   *            the new area
   */
  public void setArea(String area) {
    if (area.equals("left")) {
      this.area = LEFT;
    }
    if (area.equals("right")) {
      this.area = RIGHT;
    }
  }

  /* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */

  public int doStartTag() throws JspTagException {
    //reset alternating rows
    try {
      String s = "<td class='" + this.area + "'" + 
                 ((this.width != null && this.width.length() > 0) 
                                      ? "width ='" + this.width + "' >" : ">");
      JspWriter out = this.pageContext.getOut();
      out.println(s);

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
      String s = "</td>";
      JspWriter out = this.pageContext.getOut();
      out.println(s);
      return EVAL_BODY_BUFFERED;
    } catch (IOException ex) {
      return EVAL_PAGE;
    }
  }
}
