package servlet;

import model.UserModel;
import org.example.group6minibookstore.entities.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserModel userModel;

    @Override
    public void init() throws javax.servlet.ServletException {
        userModel = new UserModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "get":
                long id = Long.parseLong(req.getParameter("id"));
                User user = userModel.getUserById(id);
                if (user != null) {
                    writer.println("User: " + user.getUsername());
                } else {
                    writer.println("User not found!");
                }
                break;

            case "list":
            default:
                List<User> users = userModel.getUsers();
                for (User u : users) {
                    writer.println("ID: " + u.getId() + " | Username: " + u.getUsername() + "<br>");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "register":
                String username = req.getParameter("username");
                String password = req.getParameter("password");String email = req.getParameter("email");

                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);

                userModel.register(newUser);
                resp.getWriter().println("User registered successfully!");
                break;

            case "login":
                String loginUser = req.getParameter("username");
                String loginPass = req.getParameter("password");

                User loggedInUser = userModel.login(loginUser, loginPass);
                if (loggedInUser != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", loggedInUser);
                    resp.getWriter().println("Login successful! Welcome, " + loggedInUser.getUsername());
                } else {
                    resp.getWriter().println("Invalid credentials!");
                }
                break;

            case "update":
                long id = Long.parseLong(req.getParameter("id"));
                String updatedUsername = req.getParameter("username");

                User userToUpdate = userModel.getUserById(id);
                if (userToUpdate != null) {
                    userToUpdate.setUsername(updatedUsername);
                    userModel.updateUser(userToUpdate);
                    resp.getWriter().println("User updated successfully!");
                } else {
                    resp.getWriter().println("User not found!");
                }
                break;

            default:
                resp.getWriter().println("Invalid action!");
                break;
        }
    }
}
