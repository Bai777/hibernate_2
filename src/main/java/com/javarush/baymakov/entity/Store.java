package com.javarush.baymakov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "store")
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte id;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff managerStaff;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
