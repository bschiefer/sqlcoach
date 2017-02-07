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

public class TableAlternate extends BodyTagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3824018140794224431L;
	
	/** The width. */
	private String width = "100%";
	
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
		//reset alternating rows
		TrAlternate.resetRow();
		try {
			String s = "<table width=\"" + this.width + "\">";
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
			String s = "</table>";
			JspWriter out = this.pageContext.getOut();
			out.println(s);
			return EVAL_BODY_BUFFERED;
		} catch (IOException ex) {
			return EVAL_PAGE;
		}
	}

}
