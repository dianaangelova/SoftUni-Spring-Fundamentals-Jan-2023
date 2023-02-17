package bg.softuni.examtemplate.model.entity;

import bg.softuni.examtemplate.model.enums.MoodTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "moods")
@Getter
@Setter
@NoArgsConstructor
public class MoodEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private MoodTypeEnum name;

    private String description;

    public MoodEntity(MoodTypeEnum moodName) {
        this.name = moodName;
    }
}
