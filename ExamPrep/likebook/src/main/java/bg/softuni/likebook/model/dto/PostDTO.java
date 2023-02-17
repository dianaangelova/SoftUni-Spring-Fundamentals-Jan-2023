package bg.softuni.likebook.model.dto;

import bg.softuni.likebook.model.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String content;
    private int userLikes;
    private String mood;

    private String username;
    public PostDTO(PostEntity postEntity) {
        this.id= postEntity.getId();
        this.content= postEntity.getContent();

        String moodGet= postEntity.getMood().getName().toString();
        String moodNormalCase = moodGet.substring(0, 1).toUpperCase() + moodGet.toLowerCase().substring(1);

        this.mood = moodNormalCase;

        this.userLikes=postEntity.getUserLikes().size();

        this.username=postEntity.getCreator().getUsername();
    }

}
