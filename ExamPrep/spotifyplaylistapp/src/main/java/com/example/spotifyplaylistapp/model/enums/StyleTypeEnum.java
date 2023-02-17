package com.example.spotifyplaylistapp.model.enums;

public enum StyleTypeEnum {

    POP("Happy"),
    ROCK("Sad"),
    JAZZ("Inspired");

    public final String label;

    StyleTypeEnum(String label) {
        this.label = label;
    }
}
