<%@ include file="include/header.jsp" %>	
<%@ include file="include/loginCheck.jsp" %>	

	<%@page import="org.apache.struts.Globals"%>
	<h1>ADMIN CONTENT TOP SECRET</h1>
	<%
			out.println(session.getAttribute(Globals.LOCALE_KEY));
	%>
<a href="lang?lang=en">english</a>
<a href="lang?lang=de">deutsch</a>
	<br>
	<br>
	
	<custom:box width="90%">
	  ADMIN CONTENT TOP SECRET<br>
	  ADMIN CONTENT TOP SECRET<br>
	  ADMIN CONTENT TOP SECRET<br>
	  ADMIN CONTENT TOP SECRET<br>
	  ADMIN CONTENT TOP SECRET<br>
	  ADMIN CONTENT TOP SECRET<br>
	  ADMIN CONTENT TOP SECRET<br>
	</custom:box>

<%@ include file="include/footer.jsp" %>	