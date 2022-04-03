<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artemkropotov
  Date: 25.01.2022
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List:</h1>
<ul>
    <c:forEach var="product" items="${product}">
        <c:url var="viewUrl" value="/message/ + ${product.id}"/>
        <li>
            <a href="${viewUrl}">View</a>
            <br>
            Product ID: ${product.id}
            <br>
            Product TITLE: ${product.title}
            <br>
            Prodict COST: ${product.cost}
            <br>
        </li>
    </c:forEach>
</ul>
</body>
</html>
