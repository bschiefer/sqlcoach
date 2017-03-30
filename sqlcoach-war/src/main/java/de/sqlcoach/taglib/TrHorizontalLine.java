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
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Constructed with the help of:
 * http://www.torsten-horn.de/techdocs/jsp-taglibs.htm
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class TrHorizontalLine extends BodyTagSupport {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3709025777434011578L;

  /** The colspan. */
  private String colspan = "2";

  /**
   * Gets the colspan.
   * 
   * @return the colspan
   */
  public String getColspan() {
    return this.colspan;
  }

  /**
   * Sets the colspan.
   * 
   * @param colspan
   *            the new colspan
   */
  public void setColspan(String colspan) {
    this.colspan = colspan;
  }

  /* (non-Javadoc)
   * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
   */
  public int doStartTag() throws JspTagException {
    // reset alternating rows
    TrAlternate.resetRow();
    try {
      String s = "<tr><td colspan='" +this.colspan+ "'><div class='hLine'></div></td></tr>";
      JspWriter out = this.pageContext.getOut();
      out.println(s);
      return EVAL_BODY_BUFFERED;
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
