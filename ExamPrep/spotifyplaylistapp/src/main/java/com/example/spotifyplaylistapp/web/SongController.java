package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.dto.CreateSongDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.user.CurrentUser;
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
public class SongController {



    private final SongService songService;
    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public SongController(SongService songService, UserService userService, CurrentUser currentUser) {
        this.songService = songService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("createSongDTO")
    public CreateSongDTO initCreateSong() {
        return new CreateSongDTO();
    }


    @GetMapping("/songs/add")
    public String songs() {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/songs/add")
    public String songs(@Valid CreateSongDTO createSongDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.songService.create(createSongDTO)) {
            redirectAttributes.addFlashAttribute("createSongDTO", createSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createSongDTO", bindingResult);

            return "redirect:/songs/add";
        }

        return "redirect:/home";
    }

    @GetMapping("songs/add-song/{id}")
    String addSong(@PathVariable Long id) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }


        this.songService.addSongWithID(id, currentUser.getUsername());
        return "redirect:/home";

    }

    @GetMapping("songs/remove")
    String removeSongs() {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.songService.removeAllSongsByUser(currentUser.getId());
        return "redirect:/home";

    }

}
