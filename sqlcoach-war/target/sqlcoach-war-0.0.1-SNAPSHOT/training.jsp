<%@page import="de.sqlcoach.util.HeaderInfo"%>
<% HeaderInfo.setTitleTag("training.title"); %>
<%@ include file="include/header.jsp"%>

<%@page import="de.sqlcoach.util.MetaTable"%>
<%@page import="de.sqlcoach.util.MetaTableColumn"%>
<%@page import="de.sqlcoach.util.ViewResultSet"%>
<%@page import="de.sqlcoach.util.ViewResultRow"%>
<%@page import="de.sqlcoach.util.ViewResultValue"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>

<div class="basicNavi">
  <div class="basicNaviIcon"><img src="./pics/header_icon_db.png"></div>
  <div class="basicNaviText"><!-- scenario = ${scenario} -->
  <c:if test="${not empty requestScope.taskgroup}"> <%-- in Aufgabengruppe --%>
    <c:url var="url" value="exercise" >
      <c:param name="view" value="task" />
      <c:param name="status" value="get" />
      <c:param name="scenario_id" value="${taskgroup.scenario.id}" />
      <c:param name="taskgroup_id" value="${taskgroup.id}" />
    </c:url>
  </c:if>
  <c:if test="${empty requestScope.taskgroup}"> <%-- free training --%>
    <c:url var="url" value="exercise" >
      <c:param name="view" value="taskgroup" />
      <c:param name="status" value="get" />
      <c:param name="scenario_id" value="${scenario}" />
    </c:url>
  </c:if>
    <a href='<c:out value="${url}"/>'><fmt:message key="training.backToTask" /></a>
  </div>
</div>

<h1><fmt:message key="training.title" /></h1>

<%@ include file="include/alert.jsp"%>

<!-- RELATED TABLES -->
<div class="tableHeader1">
  <fmt:message key="training.tablesOverview"/>
</div>                
<custom:table>
  <thead>
    <tr>
      <td><fmt:message key="training.tables"/></td>
      <td><fmt:message key="training.columns"/></td>
      <td><fmt:message key="training.foreignKeys"/></td>
    </tr>
  </thead>
  <c:forEach var="itemMetaTable" items="${metaTableCol}">
    <custom:tr>
      <td><span class="sqlTables">${itemMetaTable.name}</span></td>
      <td>
        <% String foreignKeysInfo = ""; %>
        (
        <c:forEach var="col" items="${itemMetaTable.columnCol}" varStatus="status" >
          <c:if test="${not empty col.foreignKeyColumnName}">
          <%
            MetaTableColumn column = (MetaTableColumn) pageContext.getAttribute("col");
            foreignKeysInfo += " <span class='sqlForeignKey'>" + column.getName() + "</span> &raquo; " +
            "<span class='sqlTables'>" + column.getForeignKeyTableName() + "</span>." +
            column.getForeignKeyColumnName() + "<br>";
          %>
          </c:if>
          <c:if test="${col.primaryKey}"><span class="sqlPrimaryKey"></c:if>
          <c:if test="${not empty col.foreignKeyColumnName}"><span class="sqlForeignKey"></c:if>   
          <c:out value="${col.name}"/>
          <c:if test="${not empty col.foreignKeyColumnName}"></span></c:if> 
          <c:if test="${col.primaryKey}"></span></c:if>
          <c:if test="${not status.last}">,</c:if>
        </c:forEach>
        )
      </td>
      <td>
        <div><%=foreignKeysInfo %></div>
      </td>
    </custom:tr>
  </c:forEach>  
</custom:table>
<!-- content table end -->
<div class="tableSub">
  DB: <c:out value="${db_prodname}" /> &nbsp;&nbsp;&nbsp;
  <fmt:message key="training.sqlExplanation"/>
</div>
  
<!-- FORM -->
<html:form action="/training" method="post" onsubmit="return addsql(this,cp1);">
  <html:hidden property="status" value="check"/>
  <html:hidden property="scenarioId" />
  <html:hidden property="taskgroupId" />
  <html:hidden property="taskId" />
  <html:hidden property="number" />

  <custom:tableForm>      
    <custom:trFormHeader>
      <td colspan="2"><c:out value ="${scenario.description}" /> 
<c:choose><c:when test="${not empty taskgroup.description}">
      &rarr; <c:out value ="${taskgroup.description}" /> 
</c:when><c:otherwise>
      &rarr; <fmt:message key='exercise.freeTraining' />
</c:otherwise></c:choose>
<c:if test="${not empty number}">
      &rarr; <fmt:message key="training.task" /> ${number} 
</c:if>
      </td>
    </custom:trFormHeader>
    <custom:trHLine/>
<c:if test="${not empty task.description}">
    <tr>
      <custom:tdForm area="left"><span style="white-space:nowrap"><fmt:message key="training.task" /></span></custom:tdForm>
      <custom:tdForm area="right"><c:out value="${task.description}" /></custom:tdForm>
    </tr>
</c:if>
    <tr>
      <custom:tdForm area="left"><fmt:message key="exercise.form.query" /></custom:tdForm>
      <custom:tdForm area="right"><%@ include file="include/sqlform.jsp" %></custom:tdForm>
    </tr>
    <c:set var="no" value="${user_trials[task.id]}" />
    <c:if test="${no > task.hint_trials && not empty task.hint && currentAlert.message=='alert.error.notEqual'}" >
    <tr><!-- solution hint -->
      <custom:tdForm area="left">&nbsp;</custom:tdForm>
      <custom:tdForm area="right"><span style="color:red">
          <fmt:message key="exercise.form.hint" />: <c:out value="${task.hint}" /></span>
      </custom:tdForm>
    </tr>
    </c:if>

    <tr>
      <td>&nbsp;</td>
      <td><html:submit><fmt:message key="button.check" /></html:submit></td>
    </tr>
    <custom:trHLine/>
  </custom:tableForm>  
</html:form>  

<!-- EXPLAIN PLAN -->
<button id="explainBtn" onclick="myFunction()">Ausf&uumlhrungsplan</button>
<div id="explain" style="display: none;">
<c:set var="explainViewResultSet" scope="request" value="explainResultSet"/>

<c:if test="${TrainingForm.status == 'check'}">
<div class="tableHeader1">
  <fmt:message key="training.traineeSolution"/> &mdash; <fmt:message key="training.records"/> : ${explainResultSetCount}
</div>
<c:set var="nameResultSet" scope="request" value="explainResultSet"/>
<%@ include file="include/viewResultSet.jsp"%>
<br>
</c:if>
</div>

<!-- TRAINEE SOLUTION -->
<c:set var="inputViewResultSet" scope="request" value="traineeViewResultSet"/>

<c:if test="${TrainingForm.status == 'check'}">
<div class="tableHeader1">
  <fmt:message key="training.traineeSolution"/> &mdash; <fmt:message key="training.records"/> : ${traineeViewResultSetCount}
</div>
<c:set var="nameResultSet" scope="request" value="traineeViewResultSet"/>
<%@ include file="include/viewResultSet.jsp"%>
<br>
</c:if>  

<c:if test="${not empty solutionViewResultSet}">
<!-- SAMPLE SOLUTION -->
<div class="tableHeader1">
  <fmt:message key="training.sampleSolution"/> &mdash; <fmt:message key="training.records"/> : ${solutionViewResultSetCount}
</div>
<c:set var="nameResultSet" scope="request" value="solutionViewResultSet"/>
<%@ include file="include/viewResultSet.jsp"%>
</c:if>
<!-- ${currentAlert} -->
<%@ include file="include/footer.jsp"%>

<script>
function myFunction() {
    var yourUl = document.getElementById("explain");
    yourUl.style.display = yourUl.style.display === 'none' ? '' : 'none';
}
</script>