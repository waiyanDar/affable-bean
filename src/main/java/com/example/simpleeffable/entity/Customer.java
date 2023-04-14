package com.example.simpleeffable.entity;

import com.example.simpleeffable.ds.CartItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name cannot be empty.")
    private String name;

    @Email(message = "Something's wrong.")
    private String email;

    @NotEmpty(message = "Phone can't be empty")
    private String phone;
    @NotEmpty(message = "Address can't be empty")
    private String address;
    @NotEmpty(message = "Card number can't be empty")
    @Column(name = "card_number")
    private String cardNumber;

    @Min(value = 100000000)
    @Max(value = 999999999)
    @Column(name = "voucher_no",unique = true)
    private int voucherNo;

    @Min(value = 1,message = "prague can't be less than 0")
    private String prague;

    @ManyToMany
    @JoinTable(name = "customer_product",joinColumns = @JoinColumn(name = "customer_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "FK_customer_product"))
    private List<CartItem> cartItems = new ArrayList<>();

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

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }
}
