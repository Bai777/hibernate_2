package com.javarush.baymakov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private Boolean active;
    private String username;
    private String password;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
