package org.example.group6minibookstore.servelts;


import org.example.group6minibookstore.entities.User;
import org.example.group6minibookstore.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserModel userModel = new UserModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userModel.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect("books?action=list"); // admin page
            } else {
                response.sendRedirect("books"); // customer page
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }
}

