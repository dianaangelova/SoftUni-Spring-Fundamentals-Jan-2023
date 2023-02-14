package bg.softuni.battleships.user;

import bg.softuni.battleships.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope // този bean e различен за всяка една юзерска сесия
//CurrentUser Не е добра практика да се прави, този клас ще бъде подобрен, когато вземем Spring Security
@Getter
@Setter
public class CurrentUser {
    private long id;
    private String fullName;
    public void login(UserEntity user){
        this.id= user.getId();
        this.fullName= user.getFullName();
    }
    public void logout(UserEntity user){
        this.id= 0;
        this.fullName= null;
    }
}
