package com.example.simpleeffable.service;

import com.example.simpleeffable.dao.CategoryDao;
import com.example.simpleeffable.dao.ProductDao;
import com.example.simpleeffable.ds.Cart;
import com.example.simpleeffable.ds.CartItem;
import com.example.simpleeffable.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private Cart cart;

    public List<Product> listProducts(int categoryId) {
        return productDao.findProductByCategoryId(categoryId);
    }

    public Product findProductById(int id){
        return productDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public void addToCart(int id){
        Product product = findProductById(id);
        cart.addToCart(new CartItem(
                product.getId(),
                product.getName(),
                product.getPrice(),
                1
        ));
    }
    public int cartSize(){
        return cart.cartSize();
    }
    public void cartClear(){
        cart.clearCart();
    }
    public Set<CartItem> getCartItems (){
        return cart.getCartItems();
    }
}
