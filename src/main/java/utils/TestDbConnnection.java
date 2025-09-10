package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestDbConnnection {
    public static void main(String[] args) {
        try {
            // Get SessionFactory from HibernateUtil
            SessionFactory factory = HibernateUtil.getSessionFactory();

            // Open a session
            Session session = factory.openSession();
            System.out.println("Database connection successful using Hibernate!");

            // Close session
            session.close();
            factory.close();
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
