<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html:html>
<head>
  <title><fmt:message key="login.title" /></title>
  <html:base />
</head>

<body bgcolor="white">
  <p align="center"><font size="3" color="red">Sorry your UserId/Password is incorrect</font></p>
  <div class="advice">
    <p><html:link page="/login.jsp"><font size="3" color="blue">Retry click here!</font></html:link></p>
  </div>
</body>
</html:html>