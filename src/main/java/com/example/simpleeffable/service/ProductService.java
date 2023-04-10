package com.example.simpleeffable.service;

import com.example.simpleeffable.dao.CategoryDao;
import com.example.simpleeffable.dao.ProductDao;
import com.example.simpleeffable.dao.CustomerDao;
import com.example.simpleeffable.ds.Cart;
import com.example.simpleeffable.ds.CartItem;
import com.example.simpleeffable.entity.Category;
import com.example.simpleeffable.entity.Product;
import com.example.simpleeffable.entity.Customer;
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
    private CustomerDao customerDao;

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
        customerDao.save(customer);
    }
    public List<Customer> listUser (){
        return customerDao.findAll();
    }
    public Customer searchById(int id){
        return customerDao.findById(id).get();
    }
}
