package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.UserLoginDTO;
import bg.softuni.battleships.model.dto.UserRegisterDTO;
import bg.softuni.battleships.model.entity.UserEntity;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.user.CurrentUser;
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

    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        //Check for duplicate email

        Optional<UserEntity> userByEmail = userRepository.findAllByEmail(userRegisterDTO.getEmail());

        if (userByEmail.isPresent()) {
            return false;
        }

        //Check for duplicate username

        Optional<UserEntity> userByUsername = userRepository.findAllByUsername(userRegisterDTO.getUsername());

        if (userByUsername.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();
        user.setUsername(userRegisterDTO.getUsername());
        user.setFullName(userRegisterDTO.getFullName());
        user.setEmail(userRegisterDTO.getEmail());

        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<UserEntity> userByUsernameAndPassword = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if(userByUsernameAndPassword.isPresent()) {

            String encodedPassword = userByUsernameAndPassword.get().getPassword();
            String rawPassword = userLoginDTO.getPassword();

            if(passwordEncoder.matches(rawPassword, encodedPassword)) {
                this.currentUser.login(userByUsernameAndPassword.get());

                return true;
            }

            return false;
        }

        return false;
    }
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
