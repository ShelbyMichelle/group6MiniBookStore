<%--
  Created by IntelliJ IDEA.
  User: COMFORT
  Date: 9/10/2025
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Pages.CartItem" %>
<html>
<head>
    <title>Place Order</title>
    <link rel="stylesheet" href="css/app.css">
</head>
<body>

<div class="form-container">
    <h3>Enter Your Details</h3>
    <form method="post" action="orderConfirmation.jsp">
        <label for="name">Full Name</label>
        <input type="text" name="name" id="name" required>

        <label for="email">Email</label>
        <input type="email" name="email" id="email" required>

        <label for="address">Home Address</label>
        <textarea name="address" id="address" rows="3" required></textarea>

        <button type="submit">Place Order</button>
    </form>
</div>
</body>
</html>
