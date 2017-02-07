<%@page import="de.sqlcoach.bean.Alert"%>
<%@page import="de.sqlcoach.util.LoginCheck"%>
<%
  if (! LoginCheck.loggedin(session)) { 
  Alert.catchError("alert.error.loggedout" , request);
%>
  <jsp:forward page='/login.jsp' /> 
<% 
  } 
%>
