<%--
  Created by IntelliJ IDEA.
  User: COMFORT
  Date: 9/10/2025
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="css/app.css">
</head>
<body>
<h1>Welcome Back, Please enter your details to Login</h1>
<form action="login" method="post" id="loginform">
    <label for="username">username</label><br>
    <input type="text" name="username" id="username"><br>
    <label for="password">password</label><br>
    <input type="password" name="password" id="password"><br>
    <input type="submit" id="button">
    <p>Dont have an account? <a href="register.jsp">Register</a></p>
</form>
</body>
</html>
