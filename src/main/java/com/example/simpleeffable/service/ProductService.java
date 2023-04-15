package com.example.simpleeffable.service;

import com.example.simpleeffable.dao.*;
import com.example.simpleeffable.ds.Cart;
import com.example.simpleeffable.ds.CartItem;
import com.example.simpleeffable.entity.CartItemQuantity;
import com.example.simpleeffable.entity.Category;
import com.example.simpleeffable.entity.Product;
import com.example.simpleeffable.entity.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CartItemQuantityDao cartItemQuantityDao;

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CartItemDao cartItemDao;

    private Customer customer;
    private CartItem cartItem;
    @Autowired
    private Cart cart;

    public void saveCategory(Category category){
        categoryDao.save(category);
    }
    public void saveProduct(Product product){
        productDao.save(product);
    }

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
    public void saveInfo(Customer customer){
        Random random = new Random();
        int min = 100000000;
        int max = 999999999;
        int voucherNo = random.nextInt(max - min + 1) + min;
        customer.setVoucherNo(voucherNo);

       /* CartItemQuantity quantity = new CartItemQuantity();
        quantity.setCustomer(customer);
        quantity.setCartItem(getCartItems().stream().collect(Collectors.toList()));
        quantity.setQuantity(((CartItem) customer.getCartItems()).getQuantity());
        cartItemQuantityDao.save(quantity);
        customer.getCartItemQuantities().add(quantity);*/
        customerDao.save(customer);
    }
    public List<Customer> listUser (){
        return customerDao.findAll();
    }
    public Customer searchById(int id){
        return customerDao.findById(id).get();
    }
    public void saveCartItem(CartItem cartItem){
        cartItemDao.save(cartItem);
    }

    public List<CartItem> findProduct(int id){
        return cartItemDao.findCartItemsByCustomerId(id);
    }
}
