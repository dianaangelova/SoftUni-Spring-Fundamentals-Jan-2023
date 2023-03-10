package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.UserLoginDTO;
import bg.softuni.battleships.model.dto.UserRegisterDTO;
import bg.softuni.battleships.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initRegistrationDTO(){
        return new UserRegisterDTO();
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initLoginDTO(){
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register() {

        if(this.userService.isLoggedIn()){
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes
                           ){
        if(this.userService.isLoggedIn()){
            return "redirect:/home";
        }

        if(bindingResult.hasErrors() || !this.userService.register(userRegisterDTO)){
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {

        if(this.userService.isLoggedIn()){
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes
    ){
        if(this.userService.isLoggedIn()){
            return "redirect:/home";
        }

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.userService.login(userLoginDTO)){
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }
        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logout(){
        this.userService.logout();
        return "redirect:/";
    }
}
