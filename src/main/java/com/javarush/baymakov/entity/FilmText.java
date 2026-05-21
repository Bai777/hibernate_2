package com.javarush.baymakov.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "film_text")
@Getter
@Setter
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Short id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;

    private String title;
    private String description;
}
