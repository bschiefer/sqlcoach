<%@ include file="include/loginCheck.jsp" %>  

<%@page import="de.sqlcoach.util.HeaderInfo"%>
<% HeaderInfo.setTitleTag("exerciseconfig.title"); %>
<%@ include file="include/header.jsp"%>

<%@page import="de.sqlcoach.db.entities.Scenario"%>
<%@page import="de.sqlcoach.util.LinkParameter"%>
<%@page import="de.sqlcoach.bean.ExerciseForm"%>
<%@page import="de.sqlcoach.db.entities.Taskgroup"%>

<%@page import="de.sqlcoach.db.entities.Task"%>
<%@page import="de.sqlcoach.db.entities.AppUser"%>
<%@page import="de.sqlcoach.db.entities.ScenarioTable"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="include/basicNaviAdmin.jsp"%>

<%@page import="de.sqlcoach.util.SQLCoachConf"%>
<h1><fmt:message key="exerciseconfig.title" /></h1>

<%@ include file="include/alert.jsp"%>

<c:set var="superAdminRoleName">
<%=SQLCoachConf.SUPERADMIN_ROLENAME%>
</c:set>
<% 
  // needed for line 244ff (HTML)
  final ExerciseForm param = new ExerciseForm(request); 
  pageContext.setAttribute("param",param);
%>

<!-- ADMINs SOLUTION -->
<c:if test="${(param.view == 'task') && ((param.status == null))}">
  <c:set var="inputViewResultSet" scope="page" value="adminViewResultSet"/>
  <div class="tableHeader1">
    <fmt:message key="training.traineeSolution"/>
  </div>
  <%@ include file="include/viewResultSet.jsp"%>
  <br>
</c:if>

<!-- 1) UPDATE, ADD AND DELETE -->
<!--    SCENARIO -->

<c:if test="${param.view == 'scenario'}">  
  <c:if test="${param.status == 'add' || param.status == 'update'}">  
<%
    //TODO alert need that??
    Scenario scenarioUpdate = new Scenario();
%>
    
    <c:if test="${param.status == 'update'}">  
    </c:if>
  <html:form action="/scenario" method="post" onsubmit="return validate(this);">
    <c:if test="${param.status == 'update'}">  
      <html:hidden property="action" value="update"/>
      <html:hidden property="id"     value="${scenarioUpdate.id}" />
    </c:if>
    <html:hidden property="appUserId"  value="${AppUser.id}"/>
    <html:hidden property="scenarioId" value="${param.scenario_id}"/>
    <html:hidden property="status"     value="${param.status}"/>
    <custom:tableForm>      
      <custom:trFormHeader>
        <td colspan="2">
        <c:if test="${param.status == 'add'}">
          <fmt:message key="exercise.form.scenario.add.headline" />
        </c:if>
        <c:if test="${param.status == 'update'}">
          <fmt:message key="exercise.form.scenario.update.headline" />
        </c:if>
        </td>
      </custom:trFormHeader>
      <custom:trHLine/>
      <tr>
        <custom:tdForm area="left" width="35%"><fmt:message key="exercise.form.name" /></custom:tdForm>
        <custom:tdForm area="right"><html:text property="description" maxlength="24" value="${scenarioUpdate.description}" />
        </custom:tdForm>
      </tr>
      <tr>
        <custom:tdForm area="left"><fmt:message key="exercise.form.datasource" /></custom:tdForm>
        <custom:tdForm area="right"><html:text property="datasource" maxlength="24" value="${scenarioUpdate.datasource}"/>
        </custom:tdForm>
      </tr>
      <custom:trHLine/>
      <tr>
        <td></td>
        <td><html:submit><fmt:message key="button.save" /></html:submit>
        </td>
      </tr>
    </custom:tableForm>  
  </html:form>
  </c:if>
  <c:if test="${param.status == 'delete'}">
    <html:form action="/scenario" method="post" onsubmit="return validate(this);">
      <html:hidden property="action" value="delete"/>
      <html:hidden property="id"     value="${param.scenario_id}" />
      <html:hidden property="view"   value="${param.view}" />
      <html:hidden property="scenario_id" value="${param.scenario_id}" />
      
      <custom:tableForm>      
        <custom:trFormHeader>
          <td colspan="2"><fmt:message key="exercise.form.scenario.delete.headline"/>
          </td>
        </custom:trFormHeader>
        <custom:trHLine/>
        <tr>
          <td colspan="2" align="center"><bean:message key="exercise.form.scenario.delete.text" arg0="${scenarioDelete.description}" />
          </td>
        </tr>
        <custom:trHLine/>
        <tr>
          <td colspan="2" align="center">
            <html:submit><fmt:message key="button.yes" /></html:submit>  
            &nbsp;&nbsp;          
            <html:cancel><fmt:message key="button.no" /></html:cancel>
          </td>
        </tr>
      </custom:tableForm>  
    </html:form>
    </c:if>
  </c:if>

<!-- 1b) ADD, UPDATE -->
<!--    SCENARIOTABLE -->
<c:if test="${requestScope.view == 'scenariotable'}">  
<c:if test="${param.status == 'add' || param.status == 'update'}">

    <c:if test="${param.status == 'update'}">
    
    </c:if>
  <html:form action="/scenariotable" method="post" onsubmit="return validate(this);">
    <c:if test="${param.status == 'update'}">
      <html:hidden property="action" value="update"/>
    </c:if>
    <html:hidden property="scenarioId" value="${requestScope.scenario_id}"/>
    <custom:tableForm>      
      <custom:trFormHeader>
        <td colspan="2"><fmt:message key="exercise.form.scenariotable.add.headline" /></td>
      </custom:trFormHeader>
      <custom:trHLine/>
      <tr>
        <custom:tdForm area="left" width="44%"><fmt:message key="exercise.form.tables" /></custom:tdForm>
        <custom:tdForm area="right">
          <c:forEach var="itemMetaTable" items="${metaTableCol}">
            <html:multibox style="width: 22px" property="scenarioTable">
            <c:out value="${itemMetaTable.name}" /></html:multibox>
            <c:out value="${itemMetaTable.name}" />
            <br>
          </c:forEach>
        </custom:tdForm>
      </tr>
      <custom:trHLine/>
      <tr>
        <td></td>
        <td><html:submit><fmt:message key="button.save" /></html:submit>
        </td>
      </tr>
    </custom:tableForm>  
  </html:form>
  </c:if>
</c:if>

<!-- 2) UPDATE, ADD AND DELETE -->
<!--    TASKGROUP -->
<html:form action="/taskgroup" method="post" onsubmit="return validate(this);">
  <html:hidden property="view" value="${param.view}" />
  <html:hidden property="scenario_id" value="${param.scenario_id}" />

<c:if test="${param.view == 'taskgroup'}">
<c:if test="${param.status == 'add' || param.status == 'update'}">  
    <c:if test="${param.status == 'update'}">
      <html:hidden property="id"     value="${taskgroupUpdate.id}" />
      <html:hidden property="rank"   value="${taskgroupUpdate.rank}" />
      <html:hidden property="action" value="update"/>
    </c:if>
    <html:hidden property="scenarioId" value="${param.scenario_id}" />
    
    <custom:tableForm>      
      <custom:trFormHeader>
      <c:if test="${param.status == 'update'}">
        <td colspan="2"><fmt:message key="exercise.form.taskgroup.update.headline" />
        </td>
      </c:if>
      <c:if test="${param.status == 'add'}">
        <td colspan="2"><fmt:message key="exercise.form.taskgroup.add.headline" />
        </td>
      </c:if>  
      </custom:trFormHeader>
      <custom:trHLine/>
      <tr>
        <custom:tdForm area="left" width="35%"><fmt:message key="exercise.form.name" /></custom:tdForm>
        <custom:tdForm area="right"><html:text property="description" maxlength="24" value="${taskgroupUpdate.description}" />
        </custom:tdForm>
      </tr>
      <custom:trHLine/>
      <tr>
        <td></td>
        <td><html:submit><fmt:message key="button.save" /></html:submit>
        </td>
      </tr>
    </custom:tableForm>  
    </c:if>
  <c:if test="${param.status == 'delete'}">
      <html:hidden property="id" value="${param.taskgroup_id}" />      
      <html:hidden property="action" value="delete"/>
      
      <custom:tableForm>      
        <custom:trFormHeader>
          <td colspan="2"><fmt:message key="exercise.form.taskgroup.delete.headline"/>
          </td>
        </custom:trFormHeader>
        <custom:trHLine/>
        <tr>
          <td colspan="2" align="center"><bean:message key="exercise.form.taskgroup.delete.text" arg0="${taskgroupDelete.description}" />
          </td>
        </tr>
        <custom:trHLine/>
        <tr>
          <td colspan="2" align="center">
            <html:submit><fmt:message key="button.yes" /></html:submit>  
            &nbsp;&nbsp;          
            <html:cancel><fmt:message key="button.no" /></html:cancel>
          </td>
        </tr>
      </custom:tableForm>  
  </c:if>
</c:if>
</html:form>

<!-- 3) UPDATE, ADD AND DELETE -->
<!--    TASK -->
<html:form action="/task" method="post" onsubmit="return addsql(this,cp1);">
  <html:hidden property="view" value="${param.view}" />
  <html:hidden property="scenario_id" value="${param.scenario_id}" />
  <html:hidden property="taskgroup_id" value="${param.taskgroup_id}" />
<c:if test="${param.view == 'task'}">
<c:if test="${param.status == 'add' || param.status == 'update'}">  
<%
    Task taskUpdate = new Task();
    if(param.getStatus().equals("update")){
      taskUpdate = (Task) request.getAttribute("taskUpdate");    
    }
%>    
    <c:if test="${param.status == 'update'}">
      <html:hidden property="id"     value="${taskUpdate.id}" />
      <html:hidden property="rank"   value="${taskUpdate.rank}" />
      <html:hidden property="action" value="update"/>
    </c:if>
    <html:hidden property="taskgroupId" value="${param.taskgroup_id}" />
    
    <custom:tableForm>      
      <custom:trFormHeader>
      <td colspan="2">
        <c:if test="${param.status == 'add'}">
          <fmt:message key="exercise.form.task.add.headline" />
        </c:if>
        <c:if test="${param.status == 'update'}">
          <fmt:message key="exercise.form.task.update.headline" />
        </c:if>
        </td>
      </custom:trFormHeader>
      <custom:trHLine/>
      <tr>
        <custom:tdForm area="left" width="18%"><fmt:message key="exercise.form.tasktext" /></custom:tdForm>
        <custom:tdForm area="right">
          <html:textarea rows="5" property="description" value="${taskUpdate.description}" />
        </custom:tdForm>
      </tr>
      <tr>
        <custom:tdForm area="left"><fmt:message key="exercise.form.query" /></custom:tdForm>
        <custom:tdForm area="right">
        <c:if test="${param.status == 'update'}">
          <c:set value="${taskUpdate.query}" var="query" scope="page"/>
        </c:if>
          <%@ include file="include/sqlform.jsp" %>
        </custom:tdForm>
      </tr>
      <tr>
        <custom:tdForm area="left"><fmt:message key="exercise.form.hint" /></custom:tdForm>
        <custom:tdForm area="right">
          <html:textarea rows="2" property="hint" value="${taskUpdate.hint}" />
        </custom:tdForm>
      </tr>
      <custom:trHLine/>
      <tr>
        <td></td>
        <td><html:submit><fmt:message key="button.save" /></html:submit>
        </td>
      </tr>
    </custom:tableForm>  
  </c:if>
  <c:if test="${param.status == 'delete'}">
  <% Task taskDelete = (Task) request.getAttribute("taskDelete"); %>
      <html:hidden property="id" value="${taskDelete.id}" />
      <html:hidden property="action" value="delete"/>
      
      <custom:tableForm>      
        <custom:trFormHeader>
          <td colspan="2"><fmt:message key="exercise.form.task.delete.headline"/>
          </td>
        </custom:trFormHeader>
        <custom:trHLine/>
        <tr>
          <td colspan="2" align="center"><bean:message key="exercise.form.task.delete.text" arg0="${taskDelete.description}" />
          </td>
        </tr>
        <custom:trHLine/>
        <tr>
          <td colspan="2" align="center">
            <html:submit><fmt:message key="button.yes" /></html:submit>  
            &nbsp;&nbsp;          
            <html:cancel><fmt:message key="button.no" /></html:cancel>
          </td>
        </tr>
      </custom:tableForm>  
    </c:if>
</c:if>
</html:form>
<br>

<!-- GET -->
<custom:twoBox area="right">
  <c:choose>
  <c:when test="${param.view == 'taskgroup' || param.view == 'task'}">  
  <div class="boxStandard"><div class="boxHeadline"><fmt:message key="exercise.Taskgroups" /></div></div>
  
  <div class="boxStandard">
    <table class="plain boxTable">
      <tr class="boxText">
        <td align="right">
          <% LinkParameter.createTaskgroupAddLink(pageContext, param, "linkParams"); %>
           <html:link page="/exerciseconfig" name="linkParams">
            <fmt:message key="exercise.newTaskgroup" />
          </html:link>
        </td>
        <td width="38">
           <html:link page="/exerciseconfig" name="linkParams">
            <img src="./pics/button_new.png" alt="<fmt:message key="button.new" />" title="<fmt:message key="button.new" />">
          </html:link>
        </td>
      </tr>
    </table>
  </div>
  <c:forEach var="itemTaskgroup" items="${taskgroupCol}">
      <c:set var="imgPostfix" value=""/>
    <c:choose>
    <c:when test="${param.taskgroup_id == itemTaskgroup.id }">
      <c:set var="imgPostfix" value="_dark"/>
      <div class="boxSelected">
    </c:when>
    <c:otherwise>
      <div class="boxStandard">
    </c:otherwise>
    </c:choose>
    <table class="plain boxTable">
      <tr class="boxText">
        <td width="38">
          <% LinkParameter.createTaskgroupLink(pageContext, "taskgroup", "rank_up", "itemTaskgroup", "linkParams"); %>
          <html:link page="/rank" name="linkParams">
            <img src="./pics/button_rank_up${imgPostfix}.png" alt="<fmt:message key="button.rankUp" />" title="<fmt:message key="button.rankUp" />">
          </html:link>
          <% LinkParameter.createTaskgroupLink(pageContext, "taskgroup", "rank_down", "itemTaskgroup", "linkParams"); %>
          <html:link page="/rank" name="linkParams">
            <img src="./pics/button_rank_down${imgPostfix}.png" alt="<fmt:message key="button.rankDown" />" title="<fmt:message key="button.rankDown" />">
          </html:link>        
        </td>
        <td width="17">
          <b><c:out value="${itemTaskgroup.number}" />.</b>
        </td>
        <td>
          <% LinkParameter.createTaskgroupLink(pageContext, "task", "get", "itemTaskgroup", "linkParams"); %>
          <html:link page="/exerciseconfig" name="linkParams">
            <c:out value="${itemTaskgroup.description}" />
          </html:link> 
        </td>
        <td width="76"> 
          <% LinkParameter.createTaskgroupLink(pageContext, "taskgroup", "update", "itemTaskgroup", "linkParams"); %>
          <html:link page="/exerciseconfig" name="linkParams">
            <img src="./pics/button_edit${imgPostfix}.png" alt="<fmt:message key="button.edit" />" title="<fmt:message key="button.edit" />">
          </html:link>          
          
          <% LinkParameter.createTaskgroupLink(pageContext, "taskgroup", "delete", "itemTaskgroup", "linkParams"); %>
          <html:link page="/exerciseconfig" name="linkParams">
            <img src="./pics/button_delete${imgPostfix}.png" alt="<fmt:message key="button.delete" />" title="<fmt:message key="button.delete" />">
          </html:link>
        </td>
      </tr>
    </table>
    
    <c:if test="${(param.view == 'task') && (param.taskgroup_id == itemTaskgroup.id)}">
     <div class="boxInset">
      <table class="plain boxTable">
        <tr class="boxText">
          <td align="right">
            <% LinkParameter.createTaskAddLink(pageContext, param, "linkParams"); %>
             <html:link page="/exerciseconfig" name="linkParams">
              <fmt:message key="exercise.newTask" />
            </html:link>
          </td>
          <td width="38">
             <html:link page="/exerciseconfig" name="linkParams">
              <img src="./pics/button_new_dark.png" alt="<fmt:message key="button.new" />" title="<fmt:message key="button.new" />">
            </html:link>
          </td>
        </tr>
      </table>
    </div>
    
    <c:forEach var="itemTask" items="${taskCol}">
      <div class="boxInset">      
        <table class="plain boxTable">
          <tr>
            <td width="38">&nbsp;</td>
            <td width="38" valign="top">
              <% LinkParameter.createTaskLink(pageContext, "task", "rank_up", "itemTask", "itemTaskgroup", "linkParams"); %>
               <html:link page="/rank" name="linkParams">
                <img src="./pics/button_rank_up_dark.png" alt="<fmt:message key="button.rankUp" />" title="<fmt:message key="button.rankUp" />">
              </html:link>
              <% LinkParameter.createTaskLink(pageContext, "task", "rank_down", "itemTask", "itemTaskgroup", "linkParams"); %>
               <html:link page="/rank" name="linkParams">
                <img src="./pics/button_rank_down_dark.png" alt="<fmt:message key="button.rankDown" />" title="<fmt:message key="button.rankDown" />">
              </html:link>  
            </td>
            <td width="24" valign="top">
              <% LinkParameter.createTaskLink(pageContext, "task", "get", "itemTask", "itemTaskgroup", "linkParams"); %>
              <b>${itemTaskgroup.number}.${itemTask.number}</b>
            </td>
            <td valign="top">
                <c:out value="${itemTask.description}" />
            </td>
            <td width="76" valign="top"> 
              <% LinkParameter.createTaskLink(pageContext, "task", "update", "itemTask", "itemTaskgroup", "linkParams"); %>
               <html:link page="/exerciseconfig" name="linkParams">
                <img src="./pics/button_edit_dark.png" alt="<fmt:message key="button.edit" />" title="<fmt:message key="button.edit" />">
              </html:link>          
              
              <% LinkParameter.createTaskLink(pageContext, "task", "delete", "itemTask", "itemTaskgroup", "linkParams"); %>
               <html:link page="/exerciseconfig" name="linkParams">
                <img src="./pics/button_delete_dark.png" alt="<fmt:message key="button.delete" />" title="<fmt:message key="button.delete" />">
              </html:link>
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
    <fmt:message key="exerciseconfig.startAdvice" />
    <br><br>
    <img src="./pics/big_db.png" alt="SQLcoach">
  </div>
  </c:otherwise>
  </c:choose>
</custom:twoBox>

<custom:twoBox area="left">  
  <div class="iconDBSelect"></div>
  <div class="clear"></div>
    
  <div class="leftBoxStandard leftBoxHeadline"><fmt:message key="exercise.Scenarios" /></div>
  <div class="leftBoxStandard">
    <table class="plain leftBoxTable">
      <tr class="leftBoxText">
        <td>
          <% LinkParameter.createScenarioAddLink(pageContext, "linkParams"); %>     
           <html:link page="/exerciseconfig" name="linkParams">
            <fmt:message key="exercise.newScenario" />
          </html:link>
        </td>
        <td width="40">     
           <html:link page="/exerciseconfig" name="linkParams">
            <img src="./pics/button_new_left.png" alt="<fmt:message key="button.new" />" title="<fmt:message key="button.new" />">
          </html:link>        
        </td>
      </tr>
    </table>
  </div>
  
  <c:forEach var="itemScenario" items="${scenarioCol}">
  <c:if test="${(AppUser.role == superAdminRoleName) || (itemScenario.appUser.id == AppUser.id)}">
  <!-- MPA BUGFIX comment shows error itemScenario.appUserId -->  
  <!-- AppUser.role:<c:out value="${AppUser.role}"/>
  itemScenario.appUserId:<c:out value="${itemScenario.appUser.id}"/>
  superAdminRoleName: <c:out value="${superAdminRoleName}"/> -->
  
<% //Scenario
    Scenario scenario = (Scenario) pageContext.getAttribute("itemScenario");
%>
      <c:set var="imgPostfix" value=""/>
    <c:choose>
    <c:when test="${(param.scenario_id == itemScenario.id)}">
      <c:set var="imgPostfix" value="_selected"/>
      <div class="leftBoxSelected">
    </c:when>
    <c:otherwise>
      <div class="leftBoxStandard">
    </c:otherwise>
    </c:choose>
     <table class="plain leftBoxTable">
      <tr class="leftBoxText">
        <td align="left">
          <% LinkParameter.createScenarioLink(pageContext, "scenario", "export", "itemScenario", "linkParams"); %>
          <html:link page="/exerciseconfig" name="linkParams" target="_blank">
           <img src="./pics/xls.gif" alt="<fmt:message key="button.export" />" title="<fmt:message key="button.export" />">
          </html:link> 
        </td>
        <td>
          <% LinkParameter.createScenarioLink(pageContext, "taskgroup", "get", "itemScenario", "linkParams"); %>
          <html:link page="/exerciseconfig" name="linkParams">
            <c:out value="${itemScenario.description}" />
          </html:link>
        </td>
        <td width="114"> 
          <% LinkParameter.createScenarioLink(pageContext, "scenario", "update", "itemScenario", "linkParams"); %>
          <html:link page="/exerciseconfig" name="linkParams">
            <img src="./pics/button_edit_left${imgPostfix}.png" alt="<fmt:message key="button.edit" />" title="<fmt:message key="button.edit" />">
          </html:link>          
          
          <% LinkParameter.createScenarioLink(pageContext, "scenario", "delete", "itemScenario", "linkParams"); %>
          <html:link page="/exerciseconfig" name="linkParams">
            <img src="./pics/button_delete_left${imgPostfix}.png" alt="<fmt:message key="button.delete" />" title="<fmt:message key="button.delete" />">
          </html:link>
                    
          <% LinkParameter.createScenarioLink(pageContext, "scenario", "statistic", "itemScenario", "linkParams"); %>
          <html:link page="/statistic" name="linkParams">
            <img src="./pics/button_statistic_left${imgPostfix}.png" alt="<fmt:message key="button.statistic" />" title="<fmt:message key="button.statistic" />">
          </html:link>
        </td>
      </tr>
    </table>
    </div>
    </c:if>
  </c:forEach>
</custom:twoBox>
<%@ include file="include/footer.jsp" %>
