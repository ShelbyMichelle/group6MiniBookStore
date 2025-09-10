<%--
  Created by IntelliJ IDEA.
  User: COMFORT
  Date: 9/10/2025
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*, Pages.CartItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="css/app.css">
</head>
<body>
<h2 style="text-align:center;">Shopping Cart</h2>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Price ($)</th>
        <th>Quantity</th>
        <th>Total</th>
        <th>Action</th>
    </tr>

    <%
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        double grandTotal = 0;
        if (cart != null && !cart.isEmpty()) {
            for (CartItem item : cart) {
                grandTotal += item.getTotalPrice();
    %>
    <tr>
        <td><%= item.getBook().getTitle() %></td>
        <td><%= item.getBook().getAuthor() %></td>
        <td><%= item.getBook().getPrice() %></td>
        <td><%= item.getQuantity() %></td>
        <td><%= item.getTotalPrice() %></td>
        <td>
            <form method="post" action="cart">
                <input type="hidden" name="bookId" value="<%= item.getBook().getId() %>"/>
                <input type="hidden" name="action" value="remove"/>
                <button type="submit" class="btn remove">Remove</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<h3 style="text-align:center;">Grand Total: $<%= grandTotal %></h3>
</body>
</html>
