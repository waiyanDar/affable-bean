package com.example.simpleeffable.dao;

import com.example.simpleeffable.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
}
