package com.javarush.baymakov.config;

import com.javarush.baymakov.entity.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Actor.class);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(City.class);
            configuration.addAnnotatedClass(Country.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Film.class);
            configuration.addAnnotatedClass(FilmText.class);
            configuration.addAnnotatedClass(Inventory.class);
            configuration.addAnnotatedClass(Language.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Rental.class);
            configuration.addAnnotatedClass(Staff.class);
            configuration.addAnnotatedClass(Store.class);
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}