package com.javarush.baymakov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Short id;

    private String address;
    private String address2;
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String postalCode;
    private String phone;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
