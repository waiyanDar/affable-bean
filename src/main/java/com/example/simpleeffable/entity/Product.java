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

    @Column(columnDefinition = "decimal(5,2)")
    private double price;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id"/*, foreignKey = @ForeignKey(name = "fk_product_category")*/)
//    @ForeignKey(name = "fk_product_category")
    private Category category;

    public Product() {
    }

    public Product(String name, double price, String description, LocalDateTime lastUpdate, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.category = category;
    }
}
