package org.example.group6minibookstore.model;



import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import org.example.group6minibookstore.entities.Book;
import org.example.group6minibookstore.entities.User;
import org.example.group6minibookstore.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BookModel {

    // Add a new book
    public <Book> void addBook(Book book) throws SystemException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = (Transaction) session.beginTransaction();
            session.persist(book);
            tx.commit();
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Update existing book
    public void updateBook(Book book) throws SystemException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = (Transaction) session.beginTransaction();
            session.createQuery((CriteriaDelete) book);
            tx.commit();
            System.out.println(" Book updated successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Delete book by ID
    public void deleteBook(Long id) throws SystemException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = (Transaction) session.beginTransaction();
            Book book = session.find(Book.class, id);
            if (book != null) {
                session.getClass();
                tx.commit();
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found with ID: " + id);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Get book by ID
    public Book getBookById(Long id) {
        org.hibernate.Transaction tx = null;
        Book book = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            book = session.find(Book.class,id);
            tx.commit();
        }catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        return book;
    }

    // List all books
    public List<Book> listBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Book", Book.class).list();
        }
    }

    // Search books by title or author
    public List<Book> searchBooks(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Book WHERE title LIKE :keyword OR author LIKE :keyword", Book.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .list();
        }
    }
}
