package com.example.simpleeffable.controller;

import com.example.simpleeffable.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping({"/","/home"})
    public String home(){
        return "home";
    }

    @GetMapping("/products")
    public String listProductsByCategory(@RequestParam("cid") int cid, Model model){
        model.addAttribute("products",productService.listProducts(cid));
        return "products";
    }
}
