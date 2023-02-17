package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final SongService songService;
    private final CurrentUser currentUser;
    private final UserService userService;

    @Autowired
    public HomeController(SongService songService, CurrentUser currentUser, UserService userService) {
        this.songService = songService;
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

        List<SongDTO> popSongs = this.songService.getSongsPop();
        List<SongDTO> rockSongs = this.songService.getSongsRock();
        List<SongDTO> jazzSongs = this.songService.getSongsJazz();
        List<SongDTO> totalSongs = this.songService.getTotalSongsPerUser(loggedUserId);
        int totalSongsDuration = this.songService.getTotalSongsDuration(loggedUserId);


        model.addAttribute("popSongs", popSongs);
        model.addAttribute("rockSongs", rockSongs);
        model.addAttribute("jazzSongs", jazzSongs);
        model.addAttribute("totalSongs", totalSongs);
        model.addAttribute("totalSongsDuration", totalSongsDuration);

        return "home";
    }




}
