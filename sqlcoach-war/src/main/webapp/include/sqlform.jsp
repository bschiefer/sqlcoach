<%@ page import="javax.servlet.*" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  //MPA BUGFIX textarea null on initialize sqlform
  String query = (String)session.getAttribute("query");

  String browserType = request.getHeader("User-Agent");
  boolean msieOrGecko = false;
  String version = new String("");
  browserType = browserType.toLowerCase();

  if(browserType != null ){
    // out.println("<div class=\"debug\">(debug) Browser Type: "+browserType+"</div>");
    //if((browserType.indexOf("msie") != -1)||(browserType.indexOf("gecko") != -1)) change to 
    //if((browserType.indexOf("gecko") != -1))
    if((browserType.indexOf("gecko") != -1) && (browserType.indexOf("like") == -1)||(browserType.indexOf("msie") != -1)||(browserType.indexOf("opera") != -1)){
    //out.print("MSIE or GECKO");  
%>
  <script type="text/javascript">
      function addsql(objForm,cp1){      
        objForm.query.value=cp1.getCode();
        return true;
      }
  </script>
    
    <!-- codepress use -->
  <textarea id="cp1" class="codepress sql" style="width: 93%; height: 130px;"><c:out value="${query}"/></textarea>
  <html:hidden property="query"/>    
    <!-- codepress import moved to header -->
<%        
  }  
  // not (msie or ) gecko or sql parameter set -> codepress can not be used
  else        
  {
%> 
<!-- MPA BUGFIX textarea null on initialize sqlform --> 
<c:if test="${empty query}">
    <html:textarea style='width: 93%; height: 130px;' property='query' value=''/>
</c:if>
<c:if test="${not empty query}">
    <html:textarea style='width: 93%; height: 130px;' property='query' value='<%=(String)session.getAttribute("query") %>'/>
</c:if>
<%    
  }
}
%>


