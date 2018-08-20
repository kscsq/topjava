<%--
  Created by IntelliJ IDEA.
  User: kscsq
  Date: 20.08.2018
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<fmt:setBundle basename="messages.app"/>--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <form action="users" method="post">
        <spring:message code="app.login"/>: <select name="userId" id="">
        <option value="100000" selected>User</option>
        <option value="100001">Admin</option>
    </select>
        <button type="submit"><spring:message code="common.select"/></button>
    </form>
    <ul>
        <li><a href="users"><spring:message code="user.title"/> </a></li>
        <li><a href="meals"><spring:message code="meal.title"/> </a></li>
    </ul>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
