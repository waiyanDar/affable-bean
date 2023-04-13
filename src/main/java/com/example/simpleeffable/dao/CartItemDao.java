package com.example.simpleeffable.dao;

import com.example.simpleeffable.ds.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemDao extends JpaRepository<CartItem,Integer> {
    @Query("""
    select b from Customer a join a.cartItems b where a.id = :id
    """)
    public List<CartItem> findCartItemsByCustomerId(int id);
}
