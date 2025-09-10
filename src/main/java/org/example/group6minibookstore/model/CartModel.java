package org.example.group6minibookstore.model;



import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import org.example.group6minibookstore.entities.Book;
import org.example.group6minibookstore.entities.Order;
import org.example.group6minibookstore.entities.User;
import org.example.group6minibookstore.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CartModel {

    private BookModel bookModel = new BookModel();
    private OrderModel orderDAO = new OrderModel();

    // Checkout: Convert session cart to Order
    public void checkout(User user, List<Book> cart) throws SystemException {
        if (cart == null || cart.isEmpty()) return;

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = (Transaction) session.beginTransaction();

            double totalAmount = cart.stream().mapToDouble(Book::getPrice).sum();
            Order order = new Order(user, cart, totalAmount);

            // Save the order
            session.persist(order);

            for (Book book : cart) {
                Book dbBook = session.find(Book.class, book.getId());
                if (dbBook != null) {
                    dbBook.setStock(dbBook.getStock() - 1);
                }
            }


            tx.commit();
            System.out.println("Checkout completed, order saved!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
