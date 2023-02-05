package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.entity.RouteEntity;
import bg.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final RouteService routeService;

    @Autowired
    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    public String Home(Model model) {


        List<RouteEntity> routes = routeService.getMostCommented();

        model.addAttribute("mostCommentedRoute", routes.get(0));

        return "index";
    }
}

