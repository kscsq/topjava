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
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a href="meals" class="navbar-brand"><img src="resources/images/icon-meal.png" alt=""><spring:message code="app.title"/> </a>
        <form class="form-inline my-2"></form>
        <a href="users" class="btn btn-info mr-1"><spring:message code="user.title"/> </a>
        <a href="" class="btn btn-primary">
            <span class="fa fa-sign-in"></span>
        </a>
    </div>
</nav>