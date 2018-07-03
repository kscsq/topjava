<%--
  Created by IntelliJ IDEA.
  User: kscsq
  Date: 03.07.2018
  Time: 8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>


<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="stylesheet.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<table>
    <tr>
        <th>Date anf Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

<c:forEach var="meal" items="${mealWithExceed}">
    <tr id="${meal.exceed}">
        <td><javatime:format value="${meal.dateTime}" style="MS" /></td>
        <%--<td><c:out value="parsedDate"/></td>--%>
        <%--<td><fmt:formatDate pattern="yyyy-MM-dd" value="${meal.dateTime}"/></td>--%>
        <td><c:out value="${meal.description}"/></td>
        <td><c:out value="${meal.calories}"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
