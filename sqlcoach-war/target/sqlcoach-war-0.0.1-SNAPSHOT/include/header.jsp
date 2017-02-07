<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/sqlcoach-custom.tld" prefix="custom"%>

<%@page import="de.sqlcoach.internationalization.Language"%>
<%@page import="de.sqlcoach.util.HeaderInfo"%>
<%@page import="de.sqlcoach.util.LoginCheck"%>

<html>
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <meta http-equiv="Content-Language" content="de">
  <title><fmt:message key="header.title" /><%= HeaderInfo.getSeparator()
  %><fmt:message key="<%= HeaderInfo.getTitleTag() %>" /></title>
  <%  HeaderInfo.resetTitleTag();  %>
  <link rel="stylesheet" type="text/css" href="./style/style.css">
  <link rel="shortcut icon" href="./pics/favicon.ico">
  
  <meta name="author"
    content="SQLcoach - Hochschule Kaiserslautern / Zweibr&uuml;cken - Florian Moritz, www.flomedia.de - Christoph Gerstle, www.christophgerstle.de - Bernhard Schiefer, www.hs-kl.de/~schiefer">
  <meta name="keywords"
    content="<fmt:message key="header.meta.keywords" />">
  <meta name="description"
    content="<fmt:message key="header.meta.description" />">
  <script type="text/javascript" src="script/google_analytics.js"></script>
  <script type="text/javascript">
    function validate(objForm){
      if(objForm.username.value.length==0){
        alert(<fmt:message key="login.js.username" />);
        objForm.username.focus();
        return false;
      }
      if(objForm.password.value.length==0){
        alert(<fmt:message key="login.js.password" />);
        objForm.password.focus();
        return false;
      }
      return true;
    }
  </script>
  <script src="./codepress/codepress.js" type="text/javascript"></script>
</head>

<body>

<div class="meta_level0_left"></div>
<div class="meta_level0_middle">

<table class="plain meta_level1" cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td class="plain meta_level1_1" width="20"></td>
    <td class="plain meta_level1_2">
    <div class="header_database"></div>
    <div class="header_sqlcoach"></div>
<% if(LoginCheck.loggedin(session)) { %>
    <div class="header_admin"></div>
<% } %>
    <div class="header_watermark"></div>
    <div class="headerNavi header_footer_text">
      <a href="index"><fmt:message key="header.link.home" /></a> 
<!--     | <a href="http://${pageContext.request.serverName}${pageContext.request.contextPath}/login"><fmt:message  key="header.link.admin" /></a>   --> 
      | <a href="login"><fmt:message  key="header.link.admin" /></a> 
      | <a href="imprint"><fmt:message key="header.link.imprint" /></a> 
<% if(LoginCheck.loggedin(session)) { %>
      | <a href="logout"><fmt:message key="header.link.logout" /></a>
<% } %>
    </div>
    <div class="languageNavi">
<% String lang = Language.readLanguage(session, request); %>
      <custom:lang current="<%=lang %>" />
    </div>
    </td>
    <td class="plain meta_level1_3"></td>
  </tr>
  <tr>
    <td class="plain meta_level1_4"></td>
    <td class="plain meta_level1_5">

    <div class="plain content">
      <div style="margin-top:15px; margin-bottom:10px; margin-left:20px; margin-right:20px">
<!-- content starts -->