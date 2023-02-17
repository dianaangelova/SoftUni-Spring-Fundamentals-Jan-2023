package bg.softuni.likebook.user;

import bg.softuni.likebook.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope // този bean e различен за всяка една юзерска сесия
//CurrentUser Не е добра практика да се прави, този клас ще бъде подобрен, когато вземем Spring Security
public class CurrentUser {
    private long id;
    private String username;
    public void login(UserEntity user){
        this.id= user.getId();
        this.username= user.getUsername();
    }
    public void logout(){
        this.id= 0;
        this.username= null;
    }
}
