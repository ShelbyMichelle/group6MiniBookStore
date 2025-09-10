package Pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")

public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        int userid = Integer.parseInt(req.getParameter("userid"));

        if (role.equals("customer")) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userid", userid);
            resp.sendRedirect("listBooks");
        } else if (role.equals("admin") && password.equals("12345")) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userid", userid);
            resp.sendRedirect("AdminBooks");
        } else {
            resp.sendRedirect("login");
        }
    }
}
