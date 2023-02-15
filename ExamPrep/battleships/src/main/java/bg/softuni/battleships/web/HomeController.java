package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.ShipDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ShipService shipService;
    private final CurrentUser currentUser;
    @Autowired
    public HomeController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String loggedOutIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {
        long currentUserId = this.currentUser.getId();
        List<ShipDTO> ownShips = this.shipService.getShipsOwnedBy(currentUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(currentUserId);

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);

        return "home";
    }
}
