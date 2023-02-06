package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.model.dto.UserRegisterDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @GetMapping("/users/logout")
    public String logout() {
        userService.logout();
        System.out.println("User is logged out");

        return "redirect:/";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);
        System.out.println("User is logged: " + userService.login(userLoginDTO));
        return "redirect:/";
    }

    @GetMapping("/users/register")
    public String register(){
        return ("/auth-register");
    }

    @PostMapping ("/users/register")
    public String register(UserRegisterDTO userRegisterDTO){

        userService.registerAndLogin(userRegisterDTO);

        return "redirect:/";
    }

}
