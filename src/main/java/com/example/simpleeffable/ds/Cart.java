package com.example.simpleeffable.ds;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@SessionScope
@Component
public class Cart {

    private Set<CartItem> cartItems = new HashSet<>();

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public int cartSize() {
        return cartItems.size();
    }

    public void addToCart(CartItem cartItem){
        cartItems.add(cartItem);
    }
}
