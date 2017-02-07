<%@ include file="include/loginCheck.jsp" %>  

<%@page import="de.sqlcoach.util.HeaderInfo"%>
<%
HeaderInfo.setTitleTag("statistic.title");
%>
<%@ include file="include/header.jsp"%>

<%@page import="de.sqlcoach.db.entities.Scenario"%>
<%@page import="de.sqlcoach.util.LinkParameter"%>
<%@page import="de.sqlcoach.bean.ExerciseForm"%>
<%@page import="de.sqlcoach.db.entities.Taskgroup"%>

<%@page import="de.sqlcoach.db.entities.Task"%>
<%@page import="de.sqlcoach.db.entities.AppUser"%>
<%@page import="de.sqlcoach.bean.StatisticParameter"%>

<%
StatisticParameter param = new StatisticParameter(request);
try {
  param.setDates(request);
} catch(ParseException e) {
  param.resetDates();
}
%>

<%@ include file="include/basicNaviAdmin.jsp"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="de.sqlcoach.db.entities.TaskgroupWithTasks"%>
<%@page import="de.sqlcoach.util.DBUtil"%>
<%@page import="de.sqlcoach.util.TextUtil"%>
<%@page import="java.text.ParseException"%>
<h1><fmt:message key="statistic.title" /></h1>

<%@ include file="include/alert.jsp"%>

<br/>

<!-- GET -->
<custom:twoBox area="right">
<%
  int  maxValue = Integer.parseInt((String) request.getAttribute("maxValue"));
%>  
  <c:forEach var="twt" items="${taskgroupWithTasksCol}">  
    <div class="boxStandard">
    <table class="plain boxTable">
      <tr class="boxText">
        <td width="17"><b>${twt.taskgroup.number}.</b></td>
        <td>${twt.taskgroup.description}</td>
      </tr>
    </table>

    <c:forEach var="t" items="${twt.taskCol}">  
      <div class="boxInset">
      <table class="plain boxTable">
        <tr>
          <td width="18"></td>
          <td width="24">
            <b>${twt.taskgroup.number}.${t.number}</b>
          </td>
          <td valign="top">
          <c:choose>
          <c:when test="${t.successQueries == 0}">
            <div class="statisticEmptySuccess"><div class="statisticText">${t.successQueries}</div></div>
          </c:when>
          <c:otherwise>
            <c:set var="success_queries" value="${t.successQueries}" scope="request"/>
            <% int sq = ((Integer) request.getAttribute("success_queries")).intValue();%> 
            <div class="statisticSuccess" style="width: <%= sq*100/maxValue %>%"><div class="statisticText">${t.successQueries}</div></div>
          </c:otherwise>
          </c:choose>
          <c:choose>
          <c:when test="${t.failedQueries == 0}">
            <div class="statisticEmptyFailed"><div class="statisticText">${t.failedQueries}</div></div>
          </c:when>
          <c:otherwise>
            <c:set var="failed_queries" value="${t.failedQueries}" scope="request"/>
            <% int fq = ((Integer) request.getAttribute("failed_queries")).intValue(); %> 
            <div class="statisticFailed" style="width: <%= fq*100/maxValue %>%"><div class="statisticText">${t.failedQueries}</div></div>
          </c:otherwise>
          </c:choose>
          </td>
        </tr>
      </table>
      </div>
    </c:forEach>
    </div>
  </c:forEach>
</custom:twoBox>


<custom:twoBox area="left">
  <div class="iconStatistic"></div>
  <div class="clear"></div>
  
  <div class="leftBoxStandard leftBoxHeadline"><c:out value="${scenario.description}" /></div>
  
  <div class="leftBoxStandard leftBoxText" style="text-align: left">
    <br>
    <fmt:message key="statistic.chooseSpaceOfTime" />
  </div>  
  
  <html:form action="/statistic" method="post" onsubmit="return validate(this);">
    
    <html:hidden property="scenarioId" value="<%= param.getScenarioId() %>"/>
    
    <div class="leftBoxText" style="width: 300px">
      <table class="plain boxTable">
        <tr class="leftBoxText">
          <td width="77">
            <fmt:message key="statistic.dateFrom" />
          </td>
          <td width="220">
            <html:text property="dateFrom" maxlength="24" style="width: 205px" value="<%=TextUtil.dateToString(param.getDateFrom()) %>"/>
          </td>
        </tr>
      </table>
      <table class="plain boxTable">
        <tr class="leftBoxText">
          <td width="77">
            <fmt:message key="statistic.dateTill" />
          </td>
          <td width="220">
            <html:text property="dateTill" maxlength="24" style="width: 205px" value="<%=TextUtil.dateToString(param.getDateTill()) %>"/>
          </td>
        </tr>
      </table>
    </div>
    
    <div class="leftBoxText">
      <html:submit><fmt:message key="button.showSpaceOfTime" /></html:submit>
    </div>
  </html:form>
</custom:twoBox>

<%@ include file="include/footer.jsp"%>