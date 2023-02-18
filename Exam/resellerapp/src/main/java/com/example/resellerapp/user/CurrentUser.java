package com.example.resellerapp.user;

import com.example.resellerapp.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope
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
