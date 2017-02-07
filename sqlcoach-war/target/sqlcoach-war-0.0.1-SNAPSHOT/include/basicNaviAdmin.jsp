<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="basicNavi">
  <div class="basicNaviIcon"><img src="./pics/header_icon_db.png" alt="header icon database"></div>
  <div class="basicNaviText">
    <html:link page="/exerciseconfig">
      <fmt:message key="header.link.exercise" />
    </html:link>
  </div>
  
  <div class="basicNaviIcon"><img src="./pics/header_icon_user.png" alt="heade icon user"></div>
  <div class="basicNaviText">
    <html:link page="/userconfig">
      <fmt:message key="header.link.user" />
    </html:link>
  </div>
</div>
