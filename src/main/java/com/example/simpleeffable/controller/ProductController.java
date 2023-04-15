package com.example.simpleeffable.controller;

import com.example.simpleeffable.ds.CartItem;
import com.example.simpleeffable.entity.Customer;
import com.example.simpleeffable.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private boolean changeButton;
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("show",false);
        return "home";
    }

    @GetMapping("/products")
    public String listProductsByCategory(@RequestParam("cid") int cid, Model model) {
//        model.addAttribute("show", true);
        model.addAttribute("products", productService.listProducts(cid));
        return "products";
    }

    @GetMapping("/cart/purchase")
    public String purchase(@RequestParam("id") int id, @RequestParam("cid") int cid, Model model) {
        productService.addToCart(id);
        return "redirect:/products?cid=" + cid;
    }

    @GetMapping("/cart/clear")
    public String clear(RedirectAttributes attributes) {
        productService.cartClear();
        attributes.addFlashAttribute("empty",true);
        return "redirect:/view-cart";
    }

    @ModelAttribute("cartSize")
    public int cartSize() {
        return productService.getCartItems().stream().mapToInt( i -> i.getQuantity()).sum();
    }

    @GetMapping("/view-cart")
    public String checkOut(Model model) {
        model.addAttribute("well",model.containsAttribute("empty"));
        model.addAttribute("cartItem", new CartItem());
        model.addAttribute("cartItems", productService.getCartItems());
        return "view";
    }
    double price;
    double deliCharge;

    @ModelAttribute("show")
    public boolean show(){
        return true;
    }
    @ModelAttribute("subTotal")
    public double subTotal(){
        price =  productService.getCartItems().stream().mapToDouble( i -> i.getPrice() * i.getQuantity()).sum();
        return price;
    }
    @ModelAttribute("deliCharge")
    public double deliCharge(){
        deliCharge = price * 0.05;
        return deliCharge;
    }
    @ModelAttribute("total")
    public double total(){
        return price + deliCharge;
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

    @GetMapping("/add-info")
    public String addInfo( RedirectAttributes attributes,Model model){
        if (cartSize()==0){
            attributes.addFlashAttribute("empty",true);
            return "redirect:/view-cart";
        }

        model.addAttribute("user",new Customer());
        return "checkout";
    }
    @PostMapping("/add-info")
    public String saveInfo( Customer customer, BindingResult result, Model model){
        if (result.hasErrors()){
            return "checkout";
        }

        for (CartItem cartItem : productService.getCartItems()){
            cartItem.getCustomers().add(customer);
            customer.getCartItems().add(cartItem);
            productService.saveCartItem(cartItem);
        }
        productService.saveInfo(customer);

        productService.cartClear();
        return "redirect:/show-info?id=" + customer.getId();
    }
    @ModelAttribute("date")
    public String dateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        return LocalDateTime.now().format(formatter);
    }
    @GetMapping("/show-info")
    public String showInfo(@RequestParam("id")int id, Model model){

//        model.addAttribute("show",true);
        model.addAttribute("user",productService.listUser());
        model.addAttribute("bItem",productService.findProduct(id));
        model.addAttribute("cus",productService.searchById(id));

        var p1 = productService.findProduct(id);
        double subTotal= p1.stream().mapToDouble( p -> p.getPrice()* p.getQuantity()).sum();
        double deli = subTotal * 0.05;
        double total = deli + subTotal;
        model.addAttribute("deli",deli);
        model.addAttribute("total2",total);
        return "voucher";
    }

}
