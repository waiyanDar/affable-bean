package com.example.simpleeffable.dao;

import com.example.simpleeffable.ds.CartItem;
import com.example.simpleeffable.entity.CartItemQuantity;
import com.example.simpleeffable.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemQuantityDao extends JpaRepository<CartItemQuantity,Integer> {
    Optional<CartItemQuantity> findCartItemQuantityByCustomerAndCartItem(Customer customer, CartItem cartItem);
}
