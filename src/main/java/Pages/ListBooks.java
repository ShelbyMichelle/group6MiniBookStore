package Pages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/listBooks")
public class ListBooks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
//        String username = (String) session.getAttribute("username");
        Integer userid = (Integer) session.getAttribute("userid");
        if (userid == null) {
            resp.sendRedirect("login");
        }
        else {
            resp.sendRedirect("listBooks.jsp");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/listBooks.jsp");
            dispatcher.include(req, resp);
        }
    }

}
