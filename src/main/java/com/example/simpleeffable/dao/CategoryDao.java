package com.example.simpleeffable.dao;

import com.example.simpleeffable.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}
