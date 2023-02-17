package com.example.examtemplate.service;

import com.example.examtemplate.model.dto.UserLoginDTO;
import com.example.examtemplate.model.dto.UserRegisterDTO;
import com.example.examtemplate.model.entity.UserEntity;
import com.example.examtemplate.repository.UserRepository;
import com.example.examtemplate.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,
                       CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

//    public boolean register(UserRegisterDTO userRegisterDTO) {
//        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
//            return false;
//        }
//
//        //Check for duplicate email
//
//        Optional<UserEntity> userByEmail = userRepository.findAllByEmail(userRegisterDTO.getEmail());
//
//        if (userByEmail.isPresent()) {
//            return false;
//        }
//
//        //Check for duplicate username
//
//        Optional<UserEntity> userByUsername = userRepository.findAllByUsername(userRegisterDTO.getUsername());
//
//        if (userByUsername.isPresent()) {
//            return false;
//        }
//
//        UserEntity user = new UserEntity();
//        user.setUsername(userRegisterDTO.getUsername());
//        user.setEmail(userRegisterDTO.getEmail());
//
//        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
//
//        this.userRepository.save(user);
//        return true;
//    }

//    public boolean login(UserLoginDTO userLoginDTO) {
//
//        Optional<UserEntity> userByUsername = this.userRepository.findAllByUsername(userLoginDTO.getUsername());
//
//        if (userByUsername.isPresent()) {
//
//            String encodedPassword = userByUsername.get().getPassword();
//            String rawPassword = userLoginDTO.getPassword();
//
//            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
//                this.currentUser.login(userByUsername.get());
//                return true;
//            }
//
//            return false;
//        }
//
//        return false;
//    }

    public void logout() {

        this.currentUser.logout();
    }

    public boolean isLoggedIn() {
        return this.currentUser.getId() > 0;
    }

    public long getLoggedUserId() {
        return this.currentUser.getId();
    }

}
