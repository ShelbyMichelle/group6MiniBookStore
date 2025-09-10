<%--
  Created by IntelliJ IDEA.
  User: COMFORT
  Date: 9/10/2025
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <link rel="stylesheet" href="css/app.css">
</head>
<body>
<h1>Welcome, Please enter your details to register</h1>
<form action="register" method="post" id="registerform">
    <label for="username">username</label><br>
    <input type="text" name="username" id="username"><br>
    <label for="password">password</label><br>
    <input type="password" name="password" id="password"><br>
    <label>Role</label><br />
    <input type="radio" name="role" id="Admin" value="Admin">
    <label for="Admin">Admin</label>
    <input type="radio" name="role" id="Customer" value="Customer">
    <label for="Customer">Customer</label> <br />
    <input type="submit" id="button">
    <p>Already have an account? <a href="login.jsp">Login</a></p>
</form>
</body>
</html>
