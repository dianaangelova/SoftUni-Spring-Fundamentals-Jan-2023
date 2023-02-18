package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.CreateSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;

    public SongService(SongRepository songRepository, CurrentUser currentUser, UserRepository userRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
    }

    public List<SongDTO> getSongsOwnedBy(long loggedUserId) {
        return null;
    }

    public List<SongDTO> getSongsNotOwnedBy(long loggedUserId) {
        return null;
    }

    public boolean create(CreateSongDTO createSongDTO) {

        StyleTypeEnum styleName = StyleTypeEnum.valueOf(createSongDTO.getStyle());
        StyleEntity style = this.styleRepository.findByStyleName(styleName);

        SongEntity song = new SongEntity();

        song.setPerformer(createSongDTO.getPerformer());
        song.setTitle(createSongDTO.getTitle());
        song.setDuration(createSongDTO.getDuration());
        song.setReleaseDate(createSongDTO.getReleaseDate());
        song.setStyle(style);
        this.songRepository.save(song);
        return true;
    }

    public List<SongDTO> getSongsPop() {

        StyleTypeEnum styleName = StyleTypeEnum.valueOf("POP");

        StyleEntity style = this.styleRepository.findByStyleName(styleName);

        return this.songRepository.findByStyle(style)
                .stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getSongsRock() {

        StyleTypeEnum styleName = StyleTypeEnum.valueOf("ROCK");

        StyleEntity style = this.styleRepository.findByStyleName(styleName);

        return this.songRepository.findByStyle(style)
                .stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getSongsJazz() {

        StyleTypeEnum styleName = StyleTypeEnum.valueOf("JAZZ");

        StyleEntity style = this.styleRepository.findByStyleName(styleName);

        return this.songRepository.findByStyle(style)
                .stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    public void addSongWithID(Long id, String username) {

        UserEntity user = this.userRepository.findAllByUsername(username).orElse(null);

        SongEntity song = this.songRepository.findById(id).orElse(null);

        user.getPlaylist().add(song);

        this.userRepository.save(user);

    }

    public List<SongDTO> getTotalSongsPerUser(Long loggedUserId) {

        UserEntity user = this.userRepository.findById(loggedUserId).orElse(null);


        return user.getPlaylist().stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    public int getTotalSongsDuration(long loggedUserId) {


        UserEntity user = this.userRepository.findById(loggedUserId).orElse(null);

        Set<SongEntity> listSongs = user.getPlaylist();

        int duration = 0;

        for (SongEntity song : listSongs) {
            duration = duration + song.getDuration();
        }

        return duration;
    }

    public void removeAllSongsByUser(long loggedUserId) {

        UserEntity user = this.userRepository.findById(loggedUserId).orElse(null);

        Set<SongEntity> listSongs = user.getPlaylist();

        user.emptyPlaylist();

        this.userRepository.save(user);

    }
}
