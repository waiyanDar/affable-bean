package com.example.simpleeffable.dao;

import com.example.simpleeffable.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query("""
            select p from Product p where p.category.id =?1
            """)
    List<Product> findProductByCategoryId(int category_id);
}
