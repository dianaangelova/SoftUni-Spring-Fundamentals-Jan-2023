package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.ShipDTO;
import bg.softuni.battleships.model.dto.StartBattleDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.service.UserService;
import bg.softuni.battleships.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final ShipService shipService;

    private final CurrentUser currentUser;
    private final UserService userService;

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm() {
        return new StartBattleDTO();
    }


    @Autowired
    public HomeController(ShipService shipService, CurrentUser currentUser, UserService userService) {
        this.shipService = shipService;
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

        List<ShipDTO> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }
}
