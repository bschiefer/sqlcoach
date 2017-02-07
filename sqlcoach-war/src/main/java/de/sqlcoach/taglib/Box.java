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

Box extends BodyTagSupport {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7606779498983830708L;

  /** The Constant CENTER. */
  private static final String CENTER = "center";

  /** The Constant LEFT. */
  private static final String LEFT = "left";

  /** The Constant RIGHT. */
  private static final String RIGHT = "right";

  /** The Constant SUCCESS. */
  private static final String SUCCESS = "success";

  /** The Constant ERROR. */
  private static final String ERROR = "error";

  /** The alert. */
  private String alert = "null";

  /** The align. */
  private String align = CENTER;

  /** The width. */
  private String width = "100%";

  /**
   * Gets the alert.
   * 
   * @return the alert
   */
  public String getAlert() {
    return this.alert;
  }

  /**
   * Sets the alert.
   * 
   * @param alert
   *            the new alert
   */
  public void setAlert(String alert) {
    if (SUCCESS.equals(alert)) {
      this.alert = SUCCESS;
    }
    if (ERROR.equals(alert)) {
      this.alert = ERROR;
    }
  }

  /**
   * Gets the align.
   * 
   * @return the align
   */
  public String getAlign() {
    return this.align;
  }

  /**
   * Sets the align.
   * 
   * @param align
   *            the new align
   */
  public void setAlign(String align) {
    if (CENTER.equals(align)) {
      this.align = CENTER;
    }
    if (LEFT.equals(align)) {
      this.align = LEFT;
    }
    if (RIGHT.equals(align)) {
      this.align = RIGHT;
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
        //align
        if (this.align.equals(CENTER)) {
          s += "<div align=\"" + CENTER + "\">";
        }
        if (this.align.equals(LEFT)) {
          s += "<div align=\"" + LEFT + "\">";
        }
        if (this.align.equals(RIGHT)) {
          s += "<div align=\"" + RIGHT + "\">";
        }
        s += 
            "<table class='plain box' width='" +this.width+ "' cellpadding='0' cellspacing='0'>" + 
            "  <tr>" + 
            "   <td class=\"plain boxTopLeft\"></td>" + 
            "   <td class=\"plain boxTop\"></td>" + 
            "    <td class=\"plain boxTopRight\"></td>" + 
            "  </tr>" + 
            "  <tr>" + 
            "    <td class='plain boxLeft'></td>" + 
            "    <td class='plain boxContent' valign='top' align='center'>";

        //alert
        if (this.alert.equals(SUCCESS)) {
          s += 
              "<table class=\"plain boxContent\">" + 
              "<tr>" + "<td class=\"alertOkIcon\"></td>" + 
              "<td class=\"alertOk\">";
        }
        if (this.alert.equals(ERROR)) {
          s += 
              "<table class=\"plain boxContent\">" + 
              "<tr>" + 
              "<td class=\"alertErrorIcon\"></td>" + 
              "<td class=\"alertError\">";
        }

        //body content
        s += bc.getString();

        //alert
        if (this.alert.equals(SUCCESS) || this.alert.equals(ERROR)) {
          s += "</td></tr>" + 
               "</table>";
        }
        s += 
            "    </td>" + 
            "    <td class='plain boxRight'></td>" + 
            "  </tr>" + 
            "  <tr><td class='plain boxBottomLeft'></td>" + 
            "      <td class='plain boxBottom'></td>" + 
            "    <td class=\"plain boxBottomRight\"></td>" + 
            "  </tr>" + 
            "</table>" + "</div>";
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
