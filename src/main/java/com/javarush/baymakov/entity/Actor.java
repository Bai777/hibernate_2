package com.javarush.baymakov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor {
    @Id
    private Long id;

    @Column
    private String first_name;
}
