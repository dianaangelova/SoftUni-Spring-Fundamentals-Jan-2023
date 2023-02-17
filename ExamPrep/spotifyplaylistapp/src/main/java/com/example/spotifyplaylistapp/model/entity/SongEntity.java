package com.example.spotifyplaylistapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "songs")
public class SongEntity extends BaseEntity {

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private int duration;

    private LocalDate releaseDate;

    @ManyToOne
    private StyleEntity style;



}
