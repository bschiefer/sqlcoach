<%@page import="de.sqlcoach.util.ViewResultSet"%>
<%@page import="de.sqlcoach.util.ViewResultRow"%>
<%@page import="de.sqlcoach.util.ViewResultValue"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="custom" uri="/WEB-INF/sqlcoach-custom.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   "http://www.w3.org/TR/html4/loose.dtd">

<%  
    String nameResultSet = (String) request.getAttribute("nameResultSet");
    if(nameResultSet != null){
      ViewResultSet set = (ViewResultSet) request.getAttribute(nameResultSet);
      request.setAttribute ("set",set);
    }
%>
<custom:table>
    <thead>
      <tr>
      <c:forEach var="v" items="${set.fieldNames.row}" >
          <td>${v.value}</td>
      </c:forEach>
      </tr>
    </thead>
    <c:forEach var="r" items="${set.rows}" >
     <custom:tr>
      <c:forEach var="val" items="${r.row}" >
        <td>${val.value}</td>
      </c:forEach>
     </custom:tr>
    </c:forEach>
<c:if test="${empty set.rows}">
    <custom:tr>
      <td><fmt:message key="training.noResult" /></td>
    </custom:tr>
</c:if>
</custom:table>
