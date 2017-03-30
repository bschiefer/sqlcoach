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

TwoBox extends BodyTagSupport {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3536296375864226315L;

  /** The area. */
  private String area = "null";

  /** The width. */
  private String width = "100%";

  /** The Constant LEFT. */
  private static final String LEFT = "left";

  /** The Constant RIGHT. */
  private static final String RIGHT = "right";

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

    if (LEFT.equals(area)) {
      this.area = LEFT;
    }
    if (RIGHT.equals(area)) {
      this.area = RIGHT;
    }
  }

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

  /* (non-Javadoc)
   * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
   */
  public int doStartTag() throws JspTagException {
    return EVAL_BODY_BUFFERED;
  }

  /* (non-Javadoc)
   * @see javax.servlet.jsp.tagext.BodyTagSupport#doAfterBody()
   */
  public int doAfterBody() throws JspTagException {
    try {
      BodyContent bc = getBodyContent();
      if (null != bc) {
        String s = "";
        //area
        if (this.area.equals(RIGHT)) {
          s += 
              "<table class='plain box spread' cellpadding='0' cellspacing='0'>" + 
              "	<tr><td ></td>" + 
              "	  <td width='30%'></td>" + 
              "   <td class='plain boxTopLeft'></td>" + 
              "   <td class='plain boxTop'></td>" + 
              "   <td class='plain boxTopRight'></td>" + 
              " </tr>" + 
              " <tr>" + 
              "   <td class='plain leftBoxTopLeft'><div class='leftInnerBoxTopLeft'></div></td>" + 
              "   <td class='plain leftBoxTop'><div class='leftInnerBoxTop'></div></td>" + 
              "   <td class='plain leftBoxTopRight'><div class='leftInnerBoxTopRight'></div></td>" + 
              "   <td rowspan='4' valign='top' class='boxContent'>";

          s += bc.getString();

          s +="  </td>" + 
              "  <td rowspan='4' class='plain boxRight'></td>" + 
              " </tr>" + 
              " <tr>" + 
              "  <td class='plain leftBoxLeft'></td>" + 
              "  <td class='leftBoxContent' valign='top' height='11'>" + "";
        }
        if (this.area.equals(LEFT)) {
          s += bc.getString();
          s += 
              "  </td>" + 
              "	 <td class='plain leftBoxRight'></td>" + 
              " </tr>" + 
              " <tr><td class='plain leftBoxBottomLeft'><div class='leftInnerBoxBottomLeft'></div></td>" + 
              "     <td class='plain leftBoxBottom'><div class='leftInnerBoxBottom'></div></td>" + 
              "     <td class='plain leftBoxBottomRight'><div class='leftInnerBoxBottomRight'></div></td>" + 
              " </tr>" + 
              " <tr><td></td>" + 
              "     <td></td>" + 
              "     <td class='plain boxLeft'></td>" + 
              " </tr>" + 
              " <tr><td></td>" + 
              "	    <td></td>" + 
              "     <td class='plain boxBottomLeft'></td>" + 
              "     <td class='plain boxBottom'></td>" + 
              "     <td class='plain boxBottomRight'></td>" + 
              " </tr>" + 
              "</table>";
        }
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
    return EVAL_PAGE;
  }
}
