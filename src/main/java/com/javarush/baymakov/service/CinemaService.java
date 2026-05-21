package com.javarush.baymakov.service;

import com.javarush.baymakov.config.HibernateUtil;
import com.javarush.baymakov.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class CinemaService {
    public void createCustomer(String firstName, String lastName, String email,
                               Address address, Store store) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setStore(store);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            ;
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setActive(true);
            customer.setCreateDate(LocalDateTime.now());
            customer.setLastUpdate(LocalDateTime.now());

            session.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка создания покупателя", e);
        }
    }

    public void returnRental(Short rentalId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Rental rental = session.get(Rental.class, rentalId);
            if (rental == null || rental.getReturnDate() != null) {
                throw new RuntimeException("Аренда не найдена или уже возвращена");
            }
            rental.setReturnDate(LocalDateTime.now());
            rental.setLastUpdate(LocalDateTime.now());
            session.merge(rental);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка возврата фильма", e);
        }
    }

    public void rentFilm(Short inventoryId, Short customerId, Short staffId, BigDecimal amount) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Inventory inventory = session.get(Inventory.class, inventoryId);
            if (inventory == null) throw new RuntimeException("Инвентарь не найден");

            String hql = "FROM Rental r WHERE r.inventory.id = :invId ORDER BY r.rentalDate DESC";
            Rental lastRental = session.createQuery(hql, Rental.class)
                    .setParameter("invId", inventoryId)
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastRental != null && lastRental.getReturnDate() == null) {
                throw new RuntimeException("Фильм уже арендован и не возвращён");
            }

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setInventory(inventory);
            rental.setCustomer(session.get(Customer.class, customerId));
            rental.setStaff(session.get(Staff.class, staffId));
            rental.setReturnDate(null);
            rental.setLastUpdate(LocalDateTime.now());
            session.persist(rental);

            Payment payment = new Payment();
            payment.setCustomer(session.get(Customer.class, customerId));
            payment.setStaff(session.get(Staff.class, staffId));
            payment.setRental(rental);
            payment.setAmount(amount);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setLastUpdate(LocalDateTime.now());
            session.persist(payment);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка аренды фильма", e);
        }
    }

    public void addNewFilm(String title, String description, Language language,
                           Set<Actor> actors, Set<Category> categories,
                           Short storeId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Film film = new Film();
            film.setTitle(title);
            film.setDescription(description);
            film.setLanguage(language);
            film.setRentalDuration((byte) 3);
            film.setRentalRate(new BigDecimal("4.99"));
            film.setLength((short) 120);
            film.setReplacementCost(new BigDecimal("19.99"));
            film.setRating("PG-13");
            film.setLastUpdate(LocalDateTime.now());

            film.setActors(actors);
            film.setCategories(categories);
            session.persist(film);

            Inventory inv = new Inventory();
            inv.setFilm(film);
            inv.setStore(session.get(Store.class, storeId));
            inv.setLastUpdate(LocalDateTime.now());
            session.persist(inv);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка добавления фильма", e);
        }
    }
}
