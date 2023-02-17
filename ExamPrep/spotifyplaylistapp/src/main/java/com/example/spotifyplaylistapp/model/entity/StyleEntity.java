package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "styles")
@Getter
@Setter
@NoArgsConstructor
public class StyleEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private StyleTypeEnum styleName;

    private String description;

    public StyleEntity(StyleTypeEnum styleName) {
        this.styleName = styleName;
    }
}
