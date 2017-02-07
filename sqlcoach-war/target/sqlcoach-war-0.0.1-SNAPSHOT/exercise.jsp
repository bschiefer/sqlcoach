<%@page import="de.sqlcoach.util.HeaderInfo"%>
<% HeaderInfo.setTitleTag("exercise.title"); %>
<%@ include file="include/header.jsp"%>

<%@page import="de.sqlcoach.db.entities.Scenario"%>
<%@page import="de.sqlcoach.util.LinkParameter"%>
<%@page import="de.sqlcoach.db.entities.Taskgroup"%>

<%@page import="de.sqlcoach.db.entities.Task"%>
<%@page import="de.sqlcoach.db.entities.AppUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${requestScope.referrer == 'index'}">
  <img align="right" src="./pics/sqlcoach_logo.png" alt="SQLcoach">
  <h1 style="white-space:nowrap"><fmt:message key="header.headline" /></h1>
  <fmt:message key="index.welcome"/>
  <br/><br/><br/>
</c:if>

<c:choose>
<c:when test="${param.view == 'task' }"> 
<h1><fmt:message key="exercise.chooseTask" /></h1>
</c:when>
<c:when test="${param.view == 'taskgroup' }"> 
<h1><fmt:message key="exercise.chooseTaskGroup" /></h1>
</c:when>
<c:otherwise>
<h1><fmt:message key="exercise.chooseScenario" /></h1>
</c:otherwise>
</c:choose>

<%@ include file="include/alert.jsp"%>

<br/>
<!-- GET -->
<custom:twoBox area="right">
  <c:choose>
  <c:when test="${param.view == 'taskgroup' || param.view == 'task'}">  
  
  <div class="boxStandard"><div class="boxHeadline"><fmt:message key="exercise.Taskgroups" /></div></div>
  <%-- Free training is now possible for every scenario --%>
  <div class='boxStandard'>
      <table class="plain boxTable">
       <tr class="boxText">
         <td width="17"><b>0.</b></td>
         <td>
         <html:link page="/training" >
           <fmt:message key="exercise.freeTraining" />
         </html:link></td>
       </tr>
     </table>
  </div>
<c:forEach var="itemTaskgroup" items="${taskgroupCol}">
  <div class='${(param.taskgroup_id == itemTaskgroup.id) ? "boxSelected" : "boxStandard"}'>
    <table class="plain boxTable">
      <tr class="boxText">
        <td width="17"><b><c:out value="${itemTaskgroup.number}" />.</b></td>
        <td>
          <% LinkParameter.createTaskgroupLink(pageContext, "task", "get", "itemTaskgroup", "linkParams"); %> 
          <html:link page="/exercise" name="linkParams">
            <c:out value="${itemTaskgroup.description}" />
          </html:link></td>
      </tr>
    </table>
    <c:if test="${param.view == 'task' && param.taskgroup_id == itemTaskgroup.id }"> 
        <c:forEach var="itemTask" items="${taskCol}">
          <div class="boxInset">
          <table class="plain boxTable">
            <tr>
              <td width='18' title='<fmt:message key="exercise.trials" />'>
              <!-- BUGFIX jstl variable need data type String, changed itemTask.id (type Long) to itemTask.idToString -->
              <c:if test="${not empty user_trials[itemTask.idToString]}" ><span style="color:#FF0000">${user_trials[itemTask.idToString]}&nbsp;</span></c:if>
              <c:if test="${not empty user_success[itemTask.idToString]}" ><span style="color:#00BB00">${user_success[itemTask.idToString]}&nbsp;</span></c:if>
              </td>
              <td width="24">
               <% LinkParameter.createTaskLink(pageContext, "task", "get", "itemTask", "itemTaskgroup", "linkParams"); %>
               <b>${itemTaskgroup.number}.${itemTask.number}</b>
              </td>
              <td width="36" valign="MIDDLE"> 
              <% LinkParameter.createTrainingLink(pageContext, "get", "itemTask", "itemTaskgroup", "linkParams"); %>
               <html:link page="/training" name="linkParams">
                <img src="./pics/button_go_dark.png" alt="<fmt:message key="button.go"/>" title="<fmt:message key="button.go" />">
              </html:link>        
              </td>
              <td valign="top">
                <c:out value="${itemTask.description}"/>
              </td>
            </tr>
          </table>
          </div>
        </c:forEach>
    </c:if>
    </div>
  </c:forEach>
  </c:when>
  <c:otherwise>
  
  <div class="advice"><br>
    <br>
    <fmt:message key="exercise.startAdvice" />
    <br><br>
    <img src="./pics/big_db.png" alt="SQLcoach">
  </div>
  </c:otherwise>
  </c:choose>
</custom:twoBox>

<!-- GET Scenarios -->
<custom:twoBox area="left">
  <div class="iconDBSelect"></div>
  <div class="clear"></div>
  
  <div class="leftBoxStandard leftBoxHeadline"><fmt:message key="exercise.Scenarios" /></div>

  <c:forEach var="itemScenario" items="${scenarioCol}">
    <div class='${(param.scenario_id == itemScenario.id) ? "leftBoxSelected" : "leftBoxStandard"}'>
    <% LinkParameter.createScenarioLink(pageContext, "taskgroup", "get", "itemScenario", "linkParams"); %>
    <table class="plain leftBoxTable">
      <tr class="leftBoxText" style="text-align:left">
        <td><html:link page="/exercise" name="linkParams">
          <b><c:out value="${itemScenario.description}" /></b> <span style="font-size:smaller">[${itemScenario.appUserName}]</span>
        </html:link></td>
      </tr>
    </table>
    </div>
  </c:forEach>
</custom:twoBox>
<%@ include file="include/footer.jsp"%>