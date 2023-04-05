package com.example.simpleeffable.service;

import com.example.simpleeffable.dao.CategoryDao;
import com.example.simpleeffable.dao.ProductDao;
import com.example.simpleeffable.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;

    public List<Product> listProducts (int categoryId){
        return productDao.findProductByCategoryId(categoryId);
    }
}
