package com.javarush.baymakov;

import com.javarush.baymakov.config.HibernateUtil;
import com.javarush.baymakov.entity.Address;
import com.javarush.baymakov.entity.Store;
import com.javarush.baymakov.service.CinemaService;
import org.hibernate.Session;

public class App
{
    public static void main( String[] args )
    {
//        CinemaService service = new CinemaService();
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Store store = session.get(Store.class, (short) 1);
//            Address address = session.get(Address.class, (short) 5); // существующий адрес
//            service.createCustomer("Иван", "Петров", "ivan@mail.ru", address, store);
//            System.out.println("Покупатель создан");
//        }


        // service.returnRental((short) 100);

        // service.rentFilm((short) 10, (short) 1, (short) 1, new BigDecimal("2.99"));

        // Language lang = session.get(Language.class, (byte) 1);
        // Set<Actor> actors = Set.of(session.get(Actor.class, (short) 1));
        // Set<Category> cats = Set.of(session.get(Category.class, (byte) 1));
        // service.addNewFilm("Новый фильм", "Описание", lang, actors, cats, (short) 1);

//        HibernateUtil.shutdown();
    }
}
