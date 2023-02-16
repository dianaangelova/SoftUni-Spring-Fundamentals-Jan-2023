package bg.softuni.likebook.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope // този bean e различен за всяка една юзерска сесия
//CurrentUser Не е добра практика да се прави, този клас ще бъде подобрен, когато вземем Spring Security
public class CurrentUser {

    private String name;
    private boolean isLoggedIn;

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public void clear() {
        isLoggedIn = false;
        name = null;
    }

    public boolean isAnonymous(){
        return !isLoggedIn();
    }
}
