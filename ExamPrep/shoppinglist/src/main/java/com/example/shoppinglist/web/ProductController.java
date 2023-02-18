package com.example.shoppinglist.web;

import com.example.shoppinglist.model.dto.CreateProductDTO;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import com.example.shoppinglist.user.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;

    private final UserService userService;
    private final CurrentUser currentUser;

    public ProductController(ProductService productService, UserService userService, CurrentUser currentUser) {
        this.productService = productService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("createProductDTO")
    public CreateProductDTO initCreateProduct(){
        return new CreateProductDTO();
    }


    @GetMapping("/products/add")
    public String products() {

        if(!this.userService.isLoggedIn()){
            return "redirect:/";
        }

        return "product-add";
    }

    @PostMapping("/products/add")
    public String products(@Valid CreateProductDTO createProductDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if(!this.userService.isLoggedIn()){
            return "redirect:/";
        }


        if(bindingResult.hasErrors() || !this.productService.create(createProductDTO)) {
            redirectAttributes.addFlashAttribute("createProductDTO", createProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createProductDTO", bindingResult);

            return "redirect:/products/add";
        }

        return "redirect:/home";
    }

    @GetMapping("products/buy-product/{id}")
    String buyProduct(@PathVariable Long id) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.productService.buyProductWithID(id);
        return "redirect:/home";

    }

    @GetMapping("products/remove-all")
    String removeProducts() {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.productService.removeAllProducts();
        return "redirect:/home";

    }
}
