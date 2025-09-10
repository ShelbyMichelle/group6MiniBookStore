package Pages;

import Entities.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private Map<Integer, Book> bookDB = new HashMap<>();

    @Override
    public void init() {
        // Simulate book database
        bookDB.put(1, new Book(1, "Java Programming", "John Doe", 45.99));
        bookDB.put(2, new Book(2, "Web Development", "Jane Smith", 35.50));
        bookDB.put(3, new Book(3, "Data Structures", "Michael Lee", 50.00));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        if ("add".equals(action)) {
            Book book = bookDB.get(bookId);
            boolean found = false;

            for (CartItem item : cart) {
                if (item.getBook().getId() == bookId) {
                    item.setQuantity(item.getQuantity() + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartItem(book, 1));
            }
        } else if ("remove".equals(action)) {
            cart.removeIf(item -> item.getBook().getId() == bookId);
        }

        response.sendRedirect("cart.jsp");
    }
}
