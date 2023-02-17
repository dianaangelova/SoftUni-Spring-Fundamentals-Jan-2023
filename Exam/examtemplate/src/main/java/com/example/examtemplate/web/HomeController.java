package com.example.examtemplate.web;

import bg.softuni.likebook.model.dto.PostDTO;
import bg.softuni.likebook.service.PostService;
import bg.softuni.likebook.service.UserService;
import bg.softuni.likebook.user.CurrentUser;
import com.example.examtemplate.service.UserService;
import com.example.examtemplate.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final PostService postService;
    private final CurrentUser currentUser;
    private final UserService userService;

    @Autowired
    public HomeController(PostService postService, CurrentUser currentUser, UserService userService) {
        this.postService = postService;
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

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        long loggedUserId = this.userService.getLoggedUserId();

        List<PostDTO> ownPosts = this.postService.getPostsOwnedBy(loggedUserId);
        List<PostDTO> othersPosts = this.postService.getPostsNotOwnedBy(loggedUserId);

        model.addAttribute("ownPosts", ownPosts);
        model.addAttribute("othersPosts", othersPosts);

        return "home";
    }




}
