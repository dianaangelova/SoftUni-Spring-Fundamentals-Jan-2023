package bg.softuni.springmvc.web;

import bg.softuni.springmvc.model.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user") //1. Приема get и post заявки към end point-a, kойто е user
public class UserController {

    @GetMapping() //2. Изпълнява се get мапинга и той връща темплейт newuser, в който се съдържа html форма с две полета
    public String newUser() {

        return "newuser";
    }

    @PostMapping()
    public String createUser(UserDTO userDTO) { // Това dto има същите имена на полетата, каквито има формата в html
                                                // Spring разбира, че искаме да намапнем формата, което идва по http с това dto
        System.out.println("Creating new user " + userDTO);

        return "usercreated"; // Накрася връщаме темплейт и показваме крайния резултат на user-a
    }

}
