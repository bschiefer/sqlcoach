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
