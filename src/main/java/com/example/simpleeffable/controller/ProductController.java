package com.example.simpleeffable.controller;

import com.example.simpleeffable.ds.CartItem;
import com.example.simpleeffable.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private boolean changeButton;
    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/products")
    public String listProductsByCategory(@RequestParam("cid") int cid, Model model) {
        model.addAttribute("show", true);
        model.addAttribute("products", productService.listProducts(cid));
        return "products";
    }

    @GetMapping("/cart/purchase")
    public String purchase(@RequestParam("id") int id, @RequestParam("cid") int cid, Model model) {
        productService.addToCart(id);
        return "redirect:/products?cid=" + cid;
    }

    @GetMapping("/cart/clear")
    public String clear() {
        productService.cartClear();
        return "redirect:/view-cart";
    }

    @ModelAttribute("cartSize")
    public int cartSize() {
        return productService.cartSize();
    }

    @GetMapping("/view-cart")
    public String checkOut(Model model) {
        model.addAttribute("show",true);
        model.addAttribute("cartItem", new CartItem());
        model.addAttribute("cartItems", productService.getCartItems());
        return "view";
    }
    @ModelAttribute("subTotal")
    public double subTotal(){
        return productService.getCartItems().stream().mapToDouble( i -> i.getPrice() * i.getQuantity()).sum();
    }
    @PostMapping("/cart/update")
    public String update(CartItem cartItem,Model model){
        model.addAttribute("cartItems", productService.getCartItems());
        int i= 0;
        for ( CartItem cartItem1 : productService.getCartItems()){
            cartItem1.setQuantity(cartItem.getQList().get(i));
            cartItem1.setRender(false);
            model.addAttribute("switch",true);
            i++;
        }
        return "redirect:/view-cart";
    }
    @GetMapping("/cart/update2")
    public String update2(Model model){
        Set<CartItem> cartItems = productService.getCartItems().stream()
                .map( i -> {
                    i.setRender(true);
                    return i;
                }).collect(Collectors.toSet());
    model.addAttribute("cartItem",new CartItem());
    model.addAttribute("cartItems",cartItems);
    model.addAttribute("changeButton",true);
    return "view";
    }
    @ModelAttribute("changeButton")
    public boolean isChangeButton(){
        return changeButton;
    }

}
