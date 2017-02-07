/* 
 * Created on 11.03.2007 at 23:45:39
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

package de.sqlcoach.internationalization;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;

import de.sqlcoach.util.TextUtil;

/**
 * The Class Language.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class Language extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5089285823998287019L;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lang = request.getParameter("lang");
		
		HttpSession session = request.getSession(false);
		Locale sLocale = new Locale(lang);
		session.setAttribute(Globals.LOCALE_KEY, sLocale);
		
		response.sendRedirect(request.getHeader("Referer"));
		//request.getRequestDispatcher(request.getHeader("Referer")).forward(request, response);
	}
	
	/**
	 * Read language.
	 * 
	 * @param session
	 *            the session
	 * @param request
	 *            the request
	 * 
	 * @return the string
	 */
	public static String readLanguage(HttpSession session, HttpServletRequest request){
		String lang = "";
		try {
			//try to get local key from Session
			lang = session.getAttribute(Globals.LOCALE_KEY).toString();
		} catch (NullPointerException e) {
			//read local key form browser
			Locale default_locale = request.getLocale();
			//extract language - e.g. de from de_AT
			String[] split = TextUtil.split(default_locale.toString(), "_");
			lang = split[0];
			session.setAttribute(Globals.LOCALE_KEY, new Locale(lang));
		}
		return lang;
	}
}
