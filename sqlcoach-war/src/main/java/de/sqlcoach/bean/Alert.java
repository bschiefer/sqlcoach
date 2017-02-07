/* 
 * Created on 20.03.2007 at 13:27:47
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

package de.sqlcoach.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The Class Alert.
 * 
 * @author Florian Moritz
 * @version 0.1
 */
public class Alert implements Serializable{
  private static final long serialVersionUID = 1L;

  /** The Constant TYPE_SUCCESS. */
	public static final String TYPE_SUCCESS = "success";

	/** The Constant TYPE_ERROR. */
	public static final String TYPE_ERROR = "error";

	/** The type. */
	private String type;

	/** The message tag. */
	private String messageTag;

	/** The message list. */
	private ArrayList<String> messageList = new ArrayList<String>();

	/** The stack trace. */
	private StackTraceElement[] stackTrace;
	
	@Override
	public String toString() {
	  return "[type="+type+" messageTag="+messageTag+"]";
	}

	/**
	 * Instantiates a new alert.
	 * 
	 * @param type
	 *            the type
	 * @param messageTag
	 *            the message tag
	 * @param stackTrace
	 *            the stack trace
	 * @param messageList
	 *            the message list
	 */
	public Alert(String type, String messageTag,
			StackTraceElement[] stackTrace, ArrayList<String> messageList) {
		this.setType(type);
		this.setMessage(messageTag);
		this.setStackTrace(stackTrace);
		this.setMessageList(messageList);
	}

	/**
	 * Instantiates a new alert.
	 * 
	 * @param type
	 *            the type
	 * @param message
	 *            the message
	 */
	public Alert(String type, String message) {
		this(type, message, null, new ArrayList<String>());
	}

	/**
	 * Gets the stack trace.
	 * 
	 * @return the stack trace
	 */
	public StackTraceElement[] getStackTrace() {
		return this.stackTrace;
	}

	/**
	 * Sets the stack trace.
	 * 
	 * @param stackTrace
	 *            the new stack trace
	 */
	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return this.messageTag;
	}

	/**
	 * Sets the message.
	 * 
	 * @param messageTag
	 *            the new message
	 */
	public void setMessage(String messageTag) {
		this.messageTag = messageTag;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Catch error and forward.
	 * 
	 * @param messageTag
	 *            the message tag
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
  public static void catchErrorAndForward(String messageTag, 
                                          HttpServletRequest request, 
                                          HttpServletResponse response) throws ServletException, IOException {
    catchErrorAndForward(messageTag, null, request, response);
  }

	/**
	 * Catch error and forward.
	 * 
	 * @param messageTag
	 *            the message tag
	 * @param stackTrace
	 *            the stack trace
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
  public static void catchErrorAndForward(String messageTag, 
                                          StackTraceElement[] stackTrace, 
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws ServletException, IOException {
    final HttpSession session = request.getSession(true);
    session.setAttribute("alert", new Alert(Alert.TYPE_ERROR, messageTag, stackTrace, new ArrayList<String>()));
    request.getRequestDispatcher("/alert.jsp").forward(request, response);
  }

	/**
	 * Catch error.
	 * 
	 * @param messageTag
	 *            the message tag
	 * @param request
	 *            the request
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
  public static void catchError(String messageTag, HttpServletRequest request) throws ServletException, IOException {
    catchError(messageTag, new ArrayList<String>(), request);
	}


	/**
	 * Catch error with list.
	 * 
	 * @param messageTag
	 *            the message tag
	 * @param messageList
	 *            the message list
	 * @param request
	 *            the request
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void catchErrorWithList(String messageTag,
			                                  ArrayList<String> messageList, 
			                                  HttpServletRequest request)	throws ServletException, IOException {
		catchError(messageTag, messageList, request);
	}

	/**
	 * Catch error.
	 * 
	 * @param messageTag
	 *            the message tag
	 * @param messageList
	 *            the message list
	 * @param request
	 *            the request
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
  public static void catchError(String messageTag, 
                                ArrayList<String> messageList, 
                                HttpServletRequest request) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    session.setAttribute("alert", new Alert(Alert.TYPE_ERROR, messageTag, null, messageList));
	}


	/**
	 * Catch error stack trace.
	 * 
	 * @param messageTag
	 *            the message tag
	 * @param stackTrace
	 *            the stack trace
	 * @param request
	 *            the request
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void catchErrorStackTrace(String messageTag,
			                                    StackTraceElement[] stackTrace, 
			                                    HttpServletRequest request) throws ServletException, IOException {
		final HttpSession session = request.getSession(true);
    session.setAttribute("alert", new Alert(Alert.TYPE_ERROR, messageTag, stackTrace, new ArrayList<String>()));
	}

	/**
	 * Catch success.
	 * 
	 * @param messageTag
	 *            the message tag
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void catchSuccess(String messageTag,
			                            HttpServletRequest request, 
			                            HttpServletResponse response) throws ServletException, IOException {
    final HttpSession session = request.getSession(true);
    session.setAttribute("alert", new Alert(Alert.TYPE_SUCCESS, messageTag));
  }

	/**
	 * Gets the message list.
	 * 
	 * @return the message list
	 */
	public ArrayList<String> getMessageList() {
		return this.messageList;
	}

	/**
	 * Sets the message list.
	 * 
	 * @param messageList
	 *            the new message list
	 */
	public void setMessageList(ArrayList<String> messageList) {
		this.messageList = messageList;
	}
}
