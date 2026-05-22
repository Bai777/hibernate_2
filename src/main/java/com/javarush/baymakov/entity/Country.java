package com.javarush.baymakov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short id;

    @Column(name = "country", columnDefinition = "CHAR(50)")
    private String country;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
