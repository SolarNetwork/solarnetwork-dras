<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1 class="title"><fmt:message key="loggedout.title" /></h1>
<div class="clear"></div>

<p>
	<fmt:message key="loggedout.msg"/>
	<a href="<c:url value='/u/home.do'/>"><fmt:message key="loggedout.login.msg"/></a>
</p>
