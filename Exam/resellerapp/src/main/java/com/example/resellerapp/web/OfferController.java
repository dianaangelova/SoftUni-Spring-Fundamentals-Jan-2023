package com.example.resellerapp.web;

import com.example.resellerapp.model.dto.CreateOfferDTO;
import com.example.resellerapp.service.OfferService;
import com.example.resellerapp.service.UserService;
import com.example.resellerapp.user.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public OfferController(OfferService offerService, UserService userService, CurrentUser currentUser) {
        this.offerService = offerService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("createOfferDTO")
    public CreateOfferDTO initCreateOffer(){
        return new CreateOfferDTO();
    }
    
    @GetMapping("/offers/add")
    public String offers() {

        if(!this.userService.isLoggedIn()){
            return "redirect:/";
        }

        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String offers(@Valid CreateOfferDTO createOfferDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if(!this.userService.isLoggedIn()){
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.offerService.create(createOfferDTO)) {
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);

            return "redirect:/offers/add";
        }

        return "redirect:/home";
    }

    @GetMapping("offers/remove/{id}")
    String removeOffer(@PathVariable Long id) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.offerService.removeOffer(id);
        return "redirect:/home";

    }

    @GetMapping("offers/buy/{id}")
    String buyOffer(@PathVariable Long id) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.offerService.buyOffer(id);
        return "redirect:/home";
    }
}
