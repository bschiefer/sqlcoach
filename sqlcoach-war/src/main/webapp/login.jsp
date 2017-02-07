<%@ page import="de.sqlcoach.util.HeaderInfo"%>
<% HeaderInfo.setTitleTag("login.title"); %>
<%@ include file="include/header.jsp"%>
<%@ include file="include/alert.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html:form action="/login" method="post" onsubmit="return validate(this);">
 <custom:tableForm>
  <custom:trFormHeader>
   <td colspan="2">
    <fmt:message key="login.headline"/>
   </td>
  </custom:trFormHeader>
  <custom:trHLine/>
  <tr>
   <custom:tdForm area="left" width="42%">
    <fmt:message key="login.username"/>
   </custom:tdForm>
   <custom:tdForm area="right">
    <html:text property="username" size="30" maxlength="24"/>
   </custom:tdForm>
  </tr>
  <tr>
   <custom:tdForm area="left">
    <fmt:message key="login.password"/>
   </custom:tdForm>
   <custom:tdForm area="right">
    <html:password property="password" size="30" maxlength="24"/>
   </custom:tdForm>
  </tr>
  <custom:trHLine/>
  <tr>
   <td>&nbsp;</td>
   <td>
    <html:submit>
     <fmt:message key="login.submit"/>
    </html:submit>
   </td>
  </tr>
 </custom:tableForm>
</html:form>
<%@ include file="include/footer.jsp"%>