<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: artemkropotov
  Date: 27.03.2022
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<%--@elvariable id="product" type=""--%>
<form:form action="create" modelAttribute="product">
    From: <form:input path="id" />
    <br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
