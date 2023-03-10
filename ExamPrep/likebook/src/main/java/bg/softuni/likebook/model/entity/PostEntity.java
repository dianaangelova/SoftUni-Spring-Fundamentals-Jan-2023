package bg.softuni.likebook.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="posts")
@Getter
@Setter
public class PostEntity extends  BaseEntity{

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private UserEntity creator;

    @ManyToMany
    private Set<UserEntity> userLikes;

    @ManyToOne
    private MoodEntity mood;

    public PostEntity(){
        this.userLikes = new HashSet<>();
    }

}
