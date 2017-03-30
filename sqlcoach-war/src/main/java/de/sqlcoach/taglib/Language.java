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
 * Sets one of the here defined Languages.
 * 
 * Constructed with the help of:
 * http://www.torsten-horn.de/techdocs/jsp-taglibs.htm
 * 
 * @author Florian Moritz
 * @version 0.1
 */

public class Language extends BodyTagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6584333582736551196L;

	/** The all. */
	private String[] all = { "en", "de" };

	/** The all full. */
	private String[] allFull = { "english", "deutsch" };

	/** The current. */
	private String current = this.all[0];

	/**
	 * Gets the current.
	 * 
	 * @return the current
	 */
	public String getCurrent() {
		return this.current;
	}

	/**
	 * Sets the current.
	 * 
	 * @param value
	 *            the new current
	 */
	public void setCurrent(String value) {
		if (value != null && value != "") {
			for (int i = 0; i < this.all.length; i++) {
				// only if language is accepted by our language
				if (this.all[i].equals(value)) {
					//System.out.println(value);
					this.current = value;
					break;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	public int doStartTag() throws JspTagException {
		try {
			String s = "";
			for (int i = 0; i < this.all.length; i++) {
				s += "<a href=\"lang?lang=" + this.all[i] + "\"><img src=\"./pics/flag_" + this.all[i];
				// if other language disable flag
				if (!this.all[i].equals(this.current)) {
					s += "_disabled";
				}
				s += ".png\" alt=\"" + this.allFull[i] + "\" title=\"" + this.allFull[i] + "\"></a> ";
			}
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
