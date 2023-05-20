package com.example.shoppingcartassgn.controller;

import com.example.shoppingcartassgn.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {

    @Autowired
    public ItemService itemService;

    @GetMapping("/home")
    public String getAllCourses(Model model){
        model.addAttribute("items",this.itemService.getItems());  //this name will be use in html page
        return "home";
    }
    @GetMapping("/cart")
    public String getAllCartItems(Model model){
        model.addAttribute("cartItems",this.itemService.getCartItems());
        return "cart";
    }


    @RequestMapping(value ="/add/{id}")
    public String createCartItems(@PathVariable int id){
        this.itemService.addCartItem(id);
        return "redirect:/home";
    }

    @RequestMapping("/remove/{id}")
    public String deleteCartItems(@PathVariable int id) {
        this.itemService.deleteCartItem(id);
        return "redirect:/cart";
    }


}
