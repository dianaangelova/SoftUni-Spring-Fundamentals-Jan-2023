package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.CreateShipDTO;
import bg.softuni.battleships.model.dto.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    @ModelAttribute("createShipDTO")
    public CreateShipDTO initCreateShip(){
        return new CreateShipDTO();
    }

    @GetMapping("/ships/add")
    public String ships() {
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDTO createShipDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        //|| !this.shipService.register(createShipDTO)
        if(bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO", bindingResult);

            return "redirect:/ships/add";
        }

        return "redirect:/home";
    }
}
