ALTER TABLE film_text
    ADD CONSTRAINT FK_FILM_TEXT_ON_FILM FOREIGN KEY (film_id) REFERENCES film (film_id);

ALTER TABLE film_actor
DROP
COLUMN last_update;

ALTER TABLE film_category
DROP
COLUMN last_update;

ALTER TABLE staff
DROP
COLUMN picture;

ALTER TABLE customer
    MODIFY active TINYINT NULL;

ALTER TABLE staff
    MODIFY active BIT (1) NULL;

ALTER TABLE address
    MODIFY address VARCHAR (255);

ALTER TABLE address
    MODIFY address VARCHAR (255) NULL;

ALTER TABLE address
    MODIFY address2 VARCHAR (255);

ALTER TABLE customer
    MODIFY address_id SMALLINT NULL;

ALTER TABLE staff
    MODIFY address_id SMALLINT NULL;

ALTER TABLE store
    MODIFY address_id SMALLINT NULL;

ALTER TABLE payment
    MODIFY amount DECIMAL;

ALTER TABLE payment
    MODIFY amount DECIMAL NULL;

ALTER TABLE city
    MODIFY city VARCHAR (255);

ALTER TABLE city
    MODIFY city VARCHAR (255) NULL;

ALTER TABLE address
    MODIFY city_id SMALLINT NULL;

ALTER TABLE country
    MODIFY country VARCHAR (255);

ALTER TABLE country
    MODIFY country VARCHAR (255) NULL;

ALTER TABLE city
    MODIFY country_id SMALLINT NULL;

ALTER TABLE payment
    MODIFY customer_id SMALLINT NULL;

ALTER TABLE rental
    MODIFY customer_id SMALLINT NULL;

ALTER TABLE film
    MODIFY `description` VARCHAR (255);

ALTER TABLE film_text
    MODIFY `description` VARCHAR (255);

ALTER TABLE address
    MODIFY district VARCHAR (255);

ALTER TABLE address
    MODIFY district VARCHAR (255) NULL;

ALTER TABLE customer
    MODIFY email VARCHAR (255);

ALTER TABLE staff
    MODIFY email VARCHAR (255);

ALTER TABLE inventory
    MODIFY film_id SMALLINT NULL;

ALTER TABLE customer
    MODIFY first_name VARCHAR (255);

ALTER TABLE staff
    MODIFY first_name VARCHAR (255);

ALTER TABLE staff
    MODIFY first_name VARCHAR (255) NULL;

ALTER TABLE rental
    MODIFY inventory_id INT NULL;

ALTER TABLE film
    MODIFY language_id TINYINT NULL;

ALTER TABLE customer
    MODIFY last_name VARCHAR (255);

ALTER TABLE staff
    MODIFY last_name VARCHAR (255);

ALTER TABLE staff
    MODIFY last_name VARCHAR (255) NULL;

ALTER TABLE address
    MODIFY last_update datetime NULL;

ALTER TABLE category
    MODIFY last_update datetime NULL;

ALTER TABLE city
    MODIFY last_update datetime NULL;

ALTER TABLE country
    MODIFY last_update datetime NULL;

ALTER TABLE film
    MODIFY last_update datetime NULL;

ALTER TABLE inventory
    MODIFY last_update datetime NULL;

ALTER TABLE language
    MODIFY last_update datetime NULL;

ALTER TABLE rental
    MODIFY last_update datetime NULL;

ALTER TABLE staff
    MODIFY last_update datetime NULL;

ALTER TABLE store
    MODIFY last_update datetime NULL;

ALTER TABLE store
    MODIFY manager_staff_id TINYINT NULL;

ALTER TABLE category
    MODIFY name VARCHAR (255);

ALTER TABLE category
    MODIFY name VARCHAR (255) NULL;

ALTER TABLE language
    MODIFY name VARCHAR (255);

ALTER TABLE language
    MODIFY name VARCHAR (255) NULL;

ALTER TABLE staff
    MODIFY password VARCHAR (255);

ALTER TABLE payment
    MODIFY payment_date datetime NULL;

ALTER TABLE address
    MODIFY phone VARCHAR (255);

ALTER TABLE address
    MODIFY phone VARCHAR (255) NULL;

ALTER TABLE address
    MODIFY postal_code VARCHAR (255);

ALTER TABLE film
DROP
COLUMN release_year;

ALTER TABLE film
DROP
COLUMN special_features;

ALTER TABLE film
    ADD release_year INT NULL;

ALTER TABLE film
    MODIFY rental_duration TINYINT NULL;

ALTER TABLE film
    MODIFY rental_rate DECIMAL;

ALTER TABLE film
    MODIFY rental_rate DECIMAL NULL;

ALTER TABLE film
    MODIFY replacement_cost DECIMAL;

ALTER TABLE film
    MODIFY replacement_cost DECIMAL NULL;

ALTER TABLE film
    ADD special_features VARCHAR(255) NULL;

ALTER TABLE payment
    MODIFY staff_id TINYINT NULL;

ALTER TABLE rental
    MODIFY staff_id TINYINT NULL;

ALTER TABLE customer
    MODIFY store_id TINYINT NULL;

ALTER TABLE inventory
    MODIFY store_id TINYINT NULL;

ALTER TABLE staff
    MODIFY store_id TINYINT NULL;

ALTER TABLE film
    MODIFY title VARCHAR (255);

ALTER TABLE film
    MODIFY title VARCHAR (255) NULL;

ALTER TABLE film_text
    MODIFY title VARCHAR (255) NULL;

ALTER TABLE staff
    MODIFY username VARCHAR (255);

ALTER TABLE staff
    MODIFY username VARCHAR (255) NULL;