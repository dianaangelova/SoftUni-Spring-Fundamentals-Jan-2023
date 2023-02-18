package com.example.resellerapp.web;

import com.example.resellerapp.model.dto.OfferDTO;
import com.example.resellerapp.service.OfferService;
import com.example.resellerapp.service.UserService;
import com.example.resellerapp.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final CurrentUser currentUser;
    private final UserService userService;

    @Autowired
    public HomeController(OfferService offerService, CurrentUser currentUser, UserService userService) {
        this.offerService = offerService;
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

        List<OfferDTO> offersAllOwner = this.offerService.getAllOffersOwner(currentUser.getId());
        List<OfferDTO> offersAllNotOwner = this.offerService.getAllOffersNotOwner(currentUser.getId());
        List<OfferDTO> offersAllCreated = this.offerService.getAllOffersCreated(currentUser.getId());

        model.addAttribute("offersAllOwner", offersAllOwner);
        model.addAttribute("offersAllNotOwner", offersAllNotOwner);
        model.addAttribute("offersAllCreated", offersAllCreated);

        return "home";
    }
}
