<%@ include file="include/loginCheck.jsp" %>
<% 
request.setAttribute("referrer","user");
%>
<jsp:forward page='/userconfig' /> 