package com.example.simpleeffable;

import com.example.simpleeffable.entity.Category;
import com.example.simpleeffable.entity.Product;
import com.example.simpleeffable.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootApplication
public class SimpleEffableApplication {

    @Autowired
    private ProductService productService;

    @Transactional
    @Bean @Profile("test")
    public void saveCate(){
        Category c1 = new Category("dairy");
        Category c2 = new Category("meats");
        Category c3 = new Category("bakery");
        Category c4 = new Category("fruit & veg");

        productService.saveCategory(c1);
        productService.saveCategory(c2);
        productService.saveCategory(c3);
        productService.saveCategory(c4);

        Product p1 = new Product("milk",1.70,"semi skimmed (1L)", LocalDateTime.now(),c1);
        Product p2 = new Product("cheese",2.39,"mild cheddar(330g)", LocalDateTime.now(),c1);
        Product p3 = new Product("butter",1.09,"unsalted (250g)", LocalDateTime.now(),c1);
        Product p4 = new Product("free range eggs",1.76,"medium-sized (6 eggs)", LocalDateTime.now(),c1);
        Product p5 = new Product("organic meat patties",2.29,"rolled in fresh herbs<br> 2 patties (250g)", LocalDateTime.now(),c2);
        Product p6 = new Product("parma ham",3.49,"semi skimmed (1L)", LocalDateTime.now(),c2);
        Product p7 = new Product("chicken leg",2.59,"free range (250g)", LocalDateTime.now(),c2);
        Product p8 = new Product("sausages",3.55,"reduced fat, pork<br>3 sausages (350g)", LocalDateTime.now(),c2);
        Product p9 = new Product("sunflower seed loaf",1.89,"600g", LocalDateTime.now(),c3);
        Product p10 = new Product("sesame seed bagel",1.19,"4 bagels", LocalDateTime.now(),c3);
        Product p11 = new Product("pumpkin seed bun",1.15,"4 buns", LocalDateTime.now(),c3);
        Product p12 = new Product("chocolate cookies",2.39,"contain peanuts<br>(3cookies)", LocalDateTime.now(),c3);
        Product p13 = new Product("corn on the cob",1.59,"2 pieces", LocalDateTime.now(),c4);
        Product p14 = new Product("red currants",2.49,"150g", LocalDateTime.now(),c4);
        Product p15 = new Product("broccoli",1.29,"500g", LocalDateTime.now(),c4);
        Product p16 = new Product("seedless watermelon",1.49,"250g", LocalDateTime.now(),c4);

        productService.saveProduct(p1);
        productService.saveProduct(p2);
        productService.saveProduct(p3);
        productService.saveProduct(p4);
        productService.saveProduct(p5);
        productService.saveProduct(p6);
        productService.saveProduct(p7);
        productService.saveProduct(p8);
        productService.saveProduct(p9);
        productService.saveProduct(p10);
        productService.saveProduct(p11);
        productService.saveProduct(p12);
        productService.saveProduct(p13);
        productService.saveProduct(p14);
        productService.saveProduct(p15);
        productService.saveProduct(p16);
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleEffableApplication.class, args);
    }

}
