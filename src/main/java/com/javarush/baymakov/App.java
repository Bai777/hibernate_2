package com.javarush.baymakov;

import com.javarush.baymakov.config.HibernateUtil;
import com.javarush.baymakov.service.CinemaService;

import java.math.BigDecimal;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        CinemaService service = new CinemaService();

        service.createCustomer("Иван", "Петров", "ivan@mail.ru", (byte) 1, (short) 5);
        System.out.println("Покупатель создан");

         service.returnRental(11739);

         service.rentFilm(10, (short) 1, (byte) 1, new BigDecimal("2.99"));

         service.addNewFilm("Новый фильм", "Описание", (byte) 1,
                            Set.of((short) 1, (short) 2), Set.of((byte) 1), (byte) 1);

        HibernateUtil.shutdown();
    }
}