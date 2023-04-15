package com.example.simpleeffable.entity;

import com.example.simpleeffable.ds.CartItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CartItemQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private CartItem cartItem;

    @ManyToOne
    private Customer customer;

    private int quantity;

    // ... constructors, getters, and setters ...

    public CartItemQuantity() {
    }

    public CartItemQuantity(CartItem cartItem, Customer customer, int quantity) {
        this.cartItem = cartItem;
        this.customer = customer;
        this.quantity = quantity;
    }
}
