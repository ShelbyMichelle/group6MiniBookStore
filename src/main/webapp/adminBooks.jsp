<%--
  Created by IntelliJ IDEA.
  User: COMFORT
  Date: 9/10/2025
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*, Entities.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin - Manage Books</title>
    <link rel="stylesheet" href="css/app.css">
</head>
<body>
<h2 style="text-align:center;">Manage Books</h2>

<div class="form-container">
    <h3>Add New Book</h3>
    <form method="post" action="books">
        <input type="hidden" name="action" value="add">
        <input type="text" name="title" placeholder="Title" required>
        <input type="text" name="author" placeholder="Author" required>
        <input type="text" name="price" placeholder="Price" required>
        <button type="submit">Add</button>
    </form>
</div>

<table>
    <tr>
        <th>ID</th><th>Title</th><th>Author</th><th>Price ($)</th><th>Actions</th>
    </tr>
    <%
        List<Book> books = (List<Book>) application.getAttribute("books");
        if (books != null) {
            for (Book b : books) {
    %>
    <tr>
        <form method="post" action="books">
            <td><%= b.getId() %></td>
            <td><input type="text" name="title" value="<%= b.getTitle() %>"></td>
            <td><input type="text" name="author" value="<%= b.getAuthor() %>"></td>
            <td><input type="text" name="price" value="<%= b.getPrice() %>"></td>
            <td>
                <input type="hidden" name="id" value="<%= b.getId() %>">
                <button type="submit" name="action" value="update">Update</button>
                <button type="submit" name="action" value="delete">Delete</button>
            </td>
        </form>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
