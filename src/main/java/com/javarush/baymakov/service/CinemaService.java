package com.javarush.baymakov.service;

import com.javarush.baymakov.config.HibernateUtil;
import com.javarush.baymakov.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Consumer;

public class CinemaService {

    private void executeInTransaction(Consumer<Session> action) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw new RuntimeException("Ошибка выполнения операции", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void createCustomer(String firstName, String lastName, String email,
                               Byte storeId, Short addressId) {
        executeInTransaction(session -> {
            Store store = session.get(Store.class, storeId);
            Address address = session.get(Address.class, addressId);
            if (store == null || address == null) {
                throw new RuntimeException("Магазин или адрес не найдены");
            }
            Customer customer = new Customer();
            customer.setStore(store);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setActive(true);
            customer.setCreateDate(LocalDateTime.now());
            customer.setLastUpdate(LocalDateTime.now());
            session.persist(customer);
        });
    }

    public void returnRental(Integer rentalId) {
        executeInTransaction(session -> {
            Rental rental = session.get(Rental.class, rentalId);
            if (rental == null || rental.getReturnDate() != null) {
                throw new RuntimeException("Аренда не найдена или уже возвращена");
            }
            rental.setReturnDate(LocalDateTime.now());
            rental.setLastUpdate(LocalDateTime.now());
            session.merge(rental);
        });
    }

    public void rentFilm(Integer inventoryId, Short customerId, Byte staffId, BigDecimal amount) {
        executeInTransaction(session -> {
            Inventory inventory = session.get(Inventory.class, inventoryId);
            if (inventory == null) {
                throw new RuntimeException("Инвентарь не найден");
            }

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
        });
    }

    public void addNewFilm(String title, String description, Byte languageId,
                           Set<Short> actorIds, Set<Byte> categoryIds, Byte storeId) {
        executeInTransaction(session -> {
            Language language = session.get(Language.class, languageId);
            if (language == null) throw new RuntimeException("Язык не найден");

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

            for (Short actorId : actorIds) {
                Actor actor = session.get(Actor.class, actorId);
                if (actor != null) film.getActors().add(actor);
            }
            for (Byte categoryId : categoryIds) {
                Category category = session.get(Category.class, categoryId);
                if (category != null) film.getCategories().add(category);
            }

            session.persist(film);

            Inventory inv = new Inventory();
            inv.setFilm(film);
            inv.setStore(session.get(Store.class, storeId));
            inv.setLastUpdate(LocalDateTime.now());
            session.persist(inv);
        });
    }
}