package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class SongDTO {

    private Long id;

    private String performer;

    private String title;

    private int duration;

    private String style;

    //конструктор, който прави SongDTO от SongEntity
    public SongDTO(SongEntity song) {
        this.id = song.getId();
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = song.getDuration();
        this.style = song.getStyle().getStyleName().name();
    }


    @Override
    public String toString() {
        return performer + " - " + title + " (" + duration + " min)";
    }

}
