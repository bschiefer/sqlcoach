<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <meta http-equiv="Content-Language" content="de">
  <title><fmt:message key="header.title" /></title>
</head>

<body>
<h1>Export</h1>
<c:if test="{empty taskCol}">
  <h2><fmt:message key="export.notFound"/></h2>
</c:if>
<c:if test="{not empty taskCol}">
  <h2>Export Szenario ${scenarioId}</h2>
</c:if>
  
  <c:forEach var="t" items="${taskCol}">
    <p>${t.id};${t.taskgroup};<br/>
    ${t.description};<br/>
    ${t.hint};${t.hint_trials};<br/>
    ${t.query};</p>
  </c:forEach>
</body>
</html>