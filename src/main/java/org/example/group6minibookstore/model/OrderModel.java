package org.example.group6minibookstore.model;



import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import org.example.group6minibookstore.entities.Order;
import org.example.group6minibookstore.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderModel {

    // Create a new order
    public void createOrder(Order order) throws SystemException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = (Transaction) session.beginTransaction();
            session.persist(order);
            tx.commit();
            System.out.println("Order created successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Get order by ID
    public Order getOrderById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Order.class, id);
        }
    }

    // Get all orders (for Admin)
    public List<Order> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        }
    }

    // Get orders by User (for Customer)
    public List<Order> getOrdersByUser(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Order o WHERE o.user.id = :userId", Order.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }
}
