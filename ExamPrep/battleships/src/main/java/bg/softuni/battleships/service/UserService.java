package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.UserLoginDTO;
import bg.softuni.battleships.model.dto.UserRegisterDTO;
import bg.softuni.battleships.model.entity.UserEntity;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    @Autowired
    public UserService(UserRepository userRepository,
                       CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        //Check for duplicate email

        Optional<UserEntity> userByEmail = userRepository.findAllByEmail(userRegisterDTO.getEmail());

        if (userByEmail.isPresent()){
            return false;
        }

        //Check for duplicate username

        Optional<UserEntity> userByUsername = userRepository.findAllByUsername(userRegisterDTO.getUsername());

        if (userByUsername.isPresent()){
            return false;
        }

        UserEntity user = new UserEntity();
        user.setUsername(userRegisterDTO.getUsername());
        user.setFullName(userRegisterDTO.getFullName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());

        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<UserEntity> userByUsernameAndPassword = userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        if (!userByUsernameAndPassword.isPresent()){
            return false;
        }
        return  true;
    }
}
