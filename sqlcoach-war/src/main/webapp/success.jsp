<%@page import="de.sqlcoach.util.DBUtil"%>
<%
	//getting values
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String usertype = request.getParameter("usertype");
%>

<html>
<head>
  <title>Success</title>
</head>
<body>
 <p>Hi , <%=username%></p>
 <p align="center"><font size="3" color="#000080">You Are
 Successfully Loged in as <font color="red"><%=usertype%><br>
  <i>md5 fingerprint:<%=DBUtil.encrypt(password)%></i> </font></font></p>
</body>
</html>