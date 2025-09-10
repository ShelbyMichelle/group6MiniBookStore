<%@ page import="Entities.Book" %><%--
  Created by IntelliJ IDEA.
  User: COMFORT
  Date: 9/10/2025
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
<h1>Hello Welcome Home <%= session.getAttribute("username") %> </h1>
<a href="logout">Logout</a>
<% Book book = (Book) request.getAttribute("Book"); %>
<h4>Book id <%= book.getId() %></h4>
<h6>Book name <%= book.getName() %></h6>
</body>
</html>
