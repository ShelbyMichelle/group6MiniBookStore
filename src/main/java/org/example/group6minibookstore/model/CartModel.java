package org.example.group6minibookstore.model;



import jakarta.transaction.Transaction;
import org.example.group6minibookstore.entities.Book;
import org.example.group6minibookstore.entities.User;
import org.hibernate.Session;

import java.util.List;

public class CartDAO {

    private BookModel bookModel = new BookModel();
    private OrderModel orderDAO = new OrderModel();

    // Checkout: Convert session cart to Order
    public void checkout(User user, List<Book> cart) {
        if (cart == null || cart.isEmpty()) return;

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            double totalAmount = cart.stream().mapToDouble(Book::getPrice).sum();
            Order order = new Order(user, cart, totalAmount);

            // Save the order
            session.persist(order);

            // Update stock for each book
            for (Book book : cart) {
                Book dbBook = session.get(Book.class, book.getId());
                dbBook.setStock(dbBook.getStock() - 1);
                session.update(dbBook);
            }

            tx.commit();
            System.out.println("✅ Checkout completed, order saved!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
