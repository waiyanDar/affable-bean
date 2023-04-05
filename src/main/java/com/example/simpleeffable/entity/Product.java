package com.example.simpleeffable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String name;
    private double price;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;
}
