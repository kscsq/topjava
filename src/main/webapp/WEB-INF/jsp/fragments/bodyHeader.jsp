<%--
  Created by IntelliJ IDEA.
  User: kscsq
  Date: 20.08.2018
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<fmt:setBundle basename="messages.app"/>--%>
<%--<header><a href="${pageContext.request.contextPath}/"><fmt:message key="app.home"/></a>&nbsp; | &nbsp;<a href="meals"><fmt:message key="app.title"/></a></header>--%>
<header><a href="${pageContext.request.contextPath}/"><spring:message code="app.home"/></a>&nbsp; | &nbsp;<a href="meals"><spring:message code="app.title"/></a></header>