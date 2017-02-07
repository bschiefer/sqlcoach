<%@ include file="include/loginCheck.jsp"%>

<%@page import="de.sqlcoach.util.HeaderInfo"%>
<% HeaderInfo.setTitleTag("user.title"); %>
<%@ include file="include/header.jsp"%>

<%@page import="de.sqlcoach.util.LinkParameter"%>
<%@page import="de.sqlcoach.util.SQLCoachConf"%>
<%@page import="de.sqlcoach.util.LoginCheck"%>


<%@page import="de.sqlcoach.db.entities.AppUser"%>
<%@ include file="include/basicNaviAdmin.jsp"%>

<h1><fmt:message key="user.header.headline" /></h1>

<%@ include file="include/alert.jsp"%>

<%-- change password --%>
<% 
String imgPostfix = "";
AppUser user = (AppUser) session.getAttribute("AppUser");
String requestStatus = request.getParameter("status");
pageContext.setAttribute("user",user);
pageContext.setAttribute("requestStatus",requestStatus);
%>

<custom:twoBox area="right">

<c:if test="${requestStatus == 'change'}">	
	<c:choose>
	<c:when test="${user_id != null}">	
	<c:set var="changePasswordHeadlineKey" value="user.form.changepw.headline.superadmin"/>
	<c:set var="isSuperAdminView" value="true"/>
	</c:when>
	<c:otherwise>
	<c:set var="changePasswordHeadlineKey" value="user.form.changepw.headline.admin"/>
	<c:set var="isSuperAdminView" value="false"/>
	</c:otherwise>
	</c:choose>

	<%-- change password  --%>
		
		<html:form action="/changepw" method="post" onsubmit="return validate(this);">
			<html:hidden property="action" value="change"/>
			<html:hidden property="appUserId" value="${AppUser.id}"/><!-- <%= user.getId() %> -->
		<custom:tableForm>			
			<custom:trFormHeader>
				<td colspan="2"><bean:message key='${changePasswordHeadlineKey}' arg0='${user.nickname}' /><!-- <%= pageContext.getAttribute("changePasswordHeadlineKey")%> <%=user.getNickname()%> -->
				</td>
			</custom:trFormHeader>
			<custom:trHLine/>
				<%-- super-admins can change without old password  --%>
			<c:if test="${!isSuperAdminView}">
				<tr>
				<custom:tdForm area="left" width="35%"><fmt:message key="user.form.oldpassword" /></custom:tdForm>
				<custom:tdForm area="right"><html:password property="oldPassword" maxlength="24"/>
				</custom:tdForm>
				</tr>
			</c:if>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.newpassword" /></custom:tdForm>
				<custom:tdForm area="right"><html:password property="newPassword" maxlength="24"/>
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.newpassword" /></custom:tdForm>
				<custom:tdForm area="right"><html:password property="newPasswordConfirm" maxlength="24"/>
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
<c:if test="${requestStatus == 'update'}">

<% AppUser appUserUpdate = (AppUser) request.getAttribute("appUserUpdate"); %>
	<%-- update user  --%>
	<html:form action="/updateuser" method="post">
			<html:hidden property="action" value="update"/>
			<html:hidden property="appUserId" value="${appUserUpdate.id}"/><!-- <%= appUserUpdate.getId()%> -->
		<custom:tableForm>			
			<custom:trFormHeader>
				<td colspan="2"><fmt:message key="user.form.update.headline"/>
				</td>
			</custom:trFormHeader>
			<custom:trHLine/>
			<tr>
				<custom:tdForm area="left" width="35%"><fmt:message key="user.form.nickname" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="nickname" maxlength="24" value="<%= appUserUpdate.getNickname()%>"/>
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.firstname" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="firstname" maxlength="24" value="<%= appUserUpdate.getFirstname()%>" />
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.lastname" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="lastname" maxlength="24" value="<%= appUserUpdate.getLastname()%>" />
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.email" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="email" maxlength="24" value="<%= appUserUpdate.getEmail()%>" />
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.title" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="title" maxlength="24" value="<%= appUserUpdate.getTitle()%>" />
				</custom:tdForm>
			</tr>
				<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.role" /></custom:tdForm>
				<custom:tdForm area="right">
				<html:select property="role" value="<%=appUserUpdate.getRole()%>" >
					<html:option value="<%=SQLCoachConf.ADMIN_ROLENAME%>"><%=SQLCoachConf.ADMIN_ROLENAME%></html:option>
					<html:option value="<%=SQLCoachConf.SUPERADMIN_ROLENAME%>"><%=SQLCoachConf.SUPERADMIN_ROLENAME%></html:option>
				</html:select>
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
<c:if test="${requestStatus == 'add'}">

	<%-- add new user --%>
	<html:form action="/updateuser" method="post">
			<html:hidden property="action" value="add"/>
		<custom:tableForm>			
			<custom:trFormHeader>
				<td colspan="2"><fmt:message key="user.form.add.headline"/>
				</td>
			</custom:trFormHeader>
			<custom:trHLine/>
			<tr>
				<custom:tdForm area="left" width="35%"><fmt:message key="user.form.nickname" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="nickname" maxlength="24" />
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.firstname" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="firstname" maxlength="24" />
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.lastname" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="lastname" maxlength="24"  />
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.email" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="email" maxlength="24"  />
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.title" /></custom:tdForm>
				<custom:tdForm area="right"><html:text property="title" maxlength="24"  />
				</custom:tdForm>
			</tr>
				<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.role" /></custom:tdForm>
				<custom:tdForm area="right">
				<html:select property="role" value="<%=SQLCoachConf.ADMIN_ROLENAME%>" >
					<html:option value="<%=SQLCoachConf.ADMIN_ROLENAME%>"><%=SQLCoachConf.ADMIN_ROLENAME%></html:option>
					<html:option value="<%=SQLCoachConf.SUPERADMIN_ROLENAME%>"><%=SQLCoachConf.SUPERADMIN_ROLENAME%></html:option>
				</html:select>
				</custom:tdForm>
			</tr>
			<tr>
				<custom:tdForm area="left"><fmt:message key="user.form.password" /></custom:tdForm>
				<custom:tdForm area="right"><html:password property="password" maxlength="24" value=""/>
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
<c:choose>
<c:when test="${requestStatus == 'delete'}">
<% AppUser appUserDelete = (AppUser) request.getAttribute("appUserDelete"); %>
		<html:form action="/updateuser" method="post">
			<html:hidden property="action" value="delete"/>
			<html:hidden property="id" value="${appUser.id}" /><!-- <%= user.getId() %> -->
			<html:hidden property="appUserId" value="${appUserDelete.id}" /><!-- <%= appUserDelete.getId()%> -->
			
			<custom:tableForm>			
				<custom:trFormHeader>
					<td colspan="2"><fmt:message key="user.form.delete.headline"/></td>
				</custom:trFormHeader>
				<custom:trHLine/>
				<tr>
					<td colspan="2" align="center"><bean:message key="user.form.delete.text" arg0="${appUserDelete.nickname}" /><!-- <%=appUserDelete.getNickname() %> -->
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
</c:when>
</c:choose>
<c:if test="${requestStatus == null}">
<div class="advice">
		<br><br>
		<fmt:message key="user.startAdvice" />
	</div>
</c:if>

</custom:twoBox>
<!-- standard GET -->
<custom:twoBox area="left">
	<div class="iconUser"></div>
	<div class="clear"></div>
	<div class="leftBoxStandard">
	<table class="plain leftBoxTable">
		<tr class="leftBoxText">
			<td>
			<% LinkParameter.createPasswordLink(pageContext, "linkParams"); %>
			 <html:link page="/userconfig" name="linkParams">
				<fmt:message key="user.newPassword" />
			</html:link></td>
			<td width="40"><html:link page="/userconfig" name="linkParams">
				<img src="./pics/button_password_left.png"
					title="<fmt:message key="button.new" />">
			</html:link></td>
		</tr>
<% if (LoginCheck.isSuperAdmin(session)) { %>
		
		<tr class="leftBoxText">
			<td>
			<% LinkParameter.createUserAddLink(pageContext, "linkParams"); %> 
			 <html:link page="/userconfig" name="linkParams">
				<fmt:message key="user.newUser" />
			</html:link></td>
			<td width="40"><html:link page="/userconfig" name="linkParams">
				<img src="./pics/button_new_left.png"
					title="<fmt:message key="button.new" />">
			</html:link></td>
		</tr>
<% } %>
	</table>
	</div>
<% if (LoginCheck.isSuperAdmin(session)) { %>
	<c:forEach var="itemUser" items="${appUserCol}">
		<div class="leftBoxStandard">
		<table class="plain leftBoxTable">
			<tr class="leftBoxText">
				<td>
					<c:out value="${itemUser.nickname}" />
					(<c:out value="${itemUser.role}" />)
				</td>
				<td width="114">
				<% LinkParameter.createUserLink(pageContext,"itemUser","update","linkParams"); %> 
				<html:link page="/userconfig" name="linkParams">
					<img src="./pics/button_edit_left<%=imgPostfix %>.png"
						title="<fmt:message key="button.edit" />">
				</html:link>
				 <% LinkParameter.createUserLink(pageContext,"itemUser","delete","linkParams"); %>
			 		<html:link page="/userconfig" name="linkParams">
					<img src="./pics/button_delete_left<%=imgPostfix %>.png"
						title="<fmt:message key="button.delete" />">
				</html:link>
				<% LinkParameter.createUserLink(pageContext,"itemUser","change","linkParams"); %> 
			 	<html:link page="/userconfig" name="linkParams">
					<img src="./pics/button_password_left<%=imgPostfix %>.png"
						title="<fmt:message key="button.changePassword" />">
				</html:link>
			 		</td>
			</tr>
		</table>
		</div>
	</c:forEach>
<% } %>
</custom:twoBox>

<%@ include file="include/footer.jsp"%>
