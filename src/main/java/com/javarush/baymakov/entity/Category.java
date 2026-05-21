package com.javarush.baymakov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany(mappedBy = "categories")
    private Set<Film> films = new HashSet<>();
}
