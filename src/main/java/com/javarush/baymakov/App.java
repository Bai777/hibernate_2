package com.javarush.baymakov;

import com.javarush.baymakov.config.HibernateUtil;
import com.javarush.baymakov.service.CinemaService;

import java.math.BigDecimal;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        CinemaService service = new CinemaService();

        // 1. Создаём покупателя (убедитесь, что store_id=1 и address_id=5 существуют в вашей БД)
        service.createCustomer("Иван", "Петров", "ivan@mail.ru", (byte) 1, (short) 5);
        System.out.println("Покупатель создан");

        // 2. Возврат аренды (замените на реальный rental_id из БД)
        // service.returnRental(100);

        // 3. Аренда фильма (inventory_id, customer_id, staff_id, сумма)
        // service.rentFilm(10, (short) 1, (byte) 1, new BigDecimal("2.99"));

        // 4. Добавление нового фильма (language_id, список актёров, категорий, store_id)
        // service.addNewFilm("Новый фильм", "Описание", (byte) 1,
        //                    Set.of((short) 1, (short) 2), Set.of((byte) 1), (byte) 1);

        HibernateUtil.shutdown();
    }
}