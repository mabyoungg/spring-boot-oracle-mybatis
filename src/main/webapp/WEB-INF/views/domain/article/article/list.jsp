<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
</head>

<body>
<h1>Article List</h1>

<ul>
    <c:forEach var="article" items="${articles}">
        <li>
            <a href="article?id=${article.id}">
                    ${article.id}.${article.title}.${article.content}
            </a>
        </li>
    </c:forEach>
</ul>
</body>

</html>