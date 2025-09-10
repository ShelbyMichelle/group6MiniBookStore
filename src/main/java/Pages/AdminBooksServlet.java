package Pages;

import Entities.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adminBooks")
public class AdminBooksServlet extends HttpServlet {

    private List<Book> books;

    @Override
    public void init() throws ServletException {
        books = new ArrayList<>();
        books.add(new Book(1, "Java Programming", "John Doe", 45.99));
        books.add(new Book(2, "Web Development", "Jane Smith", 35.50));
        books.add(new Book(3, "Data Structures", "Michael Lee", 50.00));

        getServletContext().setAttribute("books", books);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Just forward to JSP
        request.getRequestDispatcher("/adminBooks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        books = (List<Book>) getServletContext().getAttribute("books");

        if ("add".equals(action)) {
            int id = books.size() + 1;
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            double price = Double.parseDouble(request.getParameter("price"));

            books.add(new Book(id, title, author, price));

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (Book book : books) {
                if (book.getId() == id) {
                    book.setTitle(request.getParameter("title"));
                    book.setAuthor(request.getParameter("author"));
                    book.setPrice(Double.parseDouble(request.getParameter("price")));
                    break;
                }
            }

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            books.removeIf(book -> book.getId() == id);
        }
    }
}
