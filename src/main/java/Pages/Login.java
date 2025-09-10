package Pages;

import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")

public class Login extends HttpServlet {
    private String username = "comfort";
    private String password = "12345";
    private String role = "customer";
    private int userid = 1;
    UserModel model = new UserModel();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.username = model.getUsers();
//        this.password = password;
        this.role = role;
        this.userid = userid;

        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String role = req.getParameter("role");
//        int userid = Integer.parseInt(req.getParameter("userid"));
        System.out.println(model.login(username, password));

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
