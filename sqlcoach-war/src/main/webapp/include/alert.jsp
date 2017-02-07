<%@ page import="de.sqlcoach.bean.Alert"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="custom" uri="/WEB-INF/sqlcoach-custom.tld"%>

<c:set var="currentAlert" value="${sessionScope.alert}" />
<%
  Alert alert = (Alert) session.getAttribute("alert");
  if (alert != null) {
%>

<custom:box alert="<%=alert.getType() %>">
    <fmt:message key="<%=alert.getMessage() %>" />  
<%
    if(alert.getMessageList().size() > 0){
      ArrayList<String> list = alert.getMessageList();
      Iterator<String> i = list.iterator();
      while(i.hasNext()){
        String s =i.next();
        %><br><%=s %><%
      }        
    }    
%>  
  </custom:box>
  <br>
<% if (alert.getStackTrace() != null) { %>
      <br>
      <custom:box>
      <br><textarea style="width:96%; height: 160px; font-size: 7pt"><%
      out.print("System Error Message:\n");
      StackTraceElement[] stack = alert.getStackTrace();
      for (int i = 0; i < stack.length; i++) {
        out.print(stack[i].toString() + "\n");
      }
%>
      </textarea> <br><br>
      </custom:box>
      <br>
      <br>
<%    } 
  }
  session.removeAttribute("alert");
%>
