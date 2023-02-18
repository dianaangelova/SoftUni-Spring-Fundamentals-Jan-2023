package com.example.shoppinglist.web;

import com.example.shoppinglist.model.dto.ProductDTO;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import com.example.shoppinglist.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CurrentUser currentUser;
    private final UserService userService;

    @Autowired
    public HomeController(ProductService productService, CurrentUser currentUser, UserService userService) {
        this.productService = productService;
        this.currentUser = currentUser;
        this.userService = userService;
    }



    @GetMapping("/")
    public String loggedOutIndex() {

        if (this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        return "index";
    }

    @GetMapping({"/home"})
    public String loggedInIndex(Model model){
        if(!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        List<List<ProductDTO>> productsByCategory = this.productService.getAllProductsByCategory();

        BigDecimal totalPriceOfProducts = this.productService.getTotalPriceOfProducts();

        model.addAttribute("productsByCategory", productsByCategory);
        model.addAttribute("totalPriceOfProducts", totalPriceOfProducts);

        return "home";
    }




}
