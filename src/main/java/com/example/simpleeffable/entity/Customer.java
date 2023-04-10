package com.example.simpleeffable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    @Column(name = "card_number")
    private String cardNumber;

    private String prague;

    public Customer() {
    }

    public Customer(String name, String email, String phone, String address, String cardNumber, String prague) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cardNumber = cardNumber;
        this.prague = prague;
    }
}
