package com.example.simpleeffable.ds;

import com.example.simpleeffable.entity.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class CartItem {
    @Id
    private int id;
    private String name;
    private double price;
    private int quantity;
    private boolean render;

    private LinkedList<Integer> qList = new LinkedList<>();

    @ManyToMany(mappedBy = "cartItems")
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public CartItem() {
    }

    public CartItem(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && Objects.equals(name, cartItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
