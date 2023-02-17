package bg.softuni.likebook.web;

import bg.softuni.likebook.model.dto.CreatePostDTO;
import bg.softuni.likebook.service.PostService;
import bg.softuni.likebook.service.UserService;
import bg.softuni.likebook.user.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {


    private final PostService postService;

    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public PostController(PostService postService, UserService userService, CurrentUser currentUser) {
        this.postService = postService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("createPostDTO")
    public CreatePostDTO initCreatePost() {
        return new CreatePostDTO();
    }


    @GetMapping("/posts/add")
    public String posts() {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        return "post-add";
    }

    @PostMapping("/posts/add")
    public String posts(@Valid CreatePostDTO createPostDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.postService.create(createPostDTO)) {
            redirectAttributes.addFlashAttribute("createPostDTO", createPostDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createPostDTO", bindingResult);

            return "redirect:/posts/add";
        }

        return "redirect:/home";
    }

    @GetMapping("posts/like-post/{id}")
    String likePost(@PathVariable Long id) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }


        this.postService.likePostWithId(id, currentUser.getUsername());
        return "redirect:/home";

    }

    @GetMapping("posts/remove/{id}")
    String removePost(@PathVariable Long id) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.postService.removePostById(id);
        return "redirect:/home";

    }

}
