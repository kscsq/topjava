<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: kscsq
  Date: 03.07.2018
  Time: 8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>


<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="stylesheet.css">
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h3>Meal List</h3>
<a href="meals?action=create">Add meal</a>
<hr>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date anf Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>

    <c:forEach var="meal" items="${mealList}">
        <tr id="${meal.exceed}">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
                <%--<td><javatime:format value="${meal.dateTime}" style="MS" /></td>--%>
                <%--<td><fmt:formatDate pattern="yyyy-MM-dd" value="${meal.dateTime}"/></td>--%>
            <td><%=TimeUtil.toString(meal.getDateTime())%>
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
