package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.UserRegistrationDTO;
import bg.softuni.pathfinder.model.entity.UserEntity;
import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            throw new RuntimeException("password.match");
        }

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(userRegistrationDTO.getUsername());

        if (byUsername.isPresent()) {
            throw new RuntimeException("username.used");
        }

        UserEntity user = new UserEntity(
                userRegistrationDTO.getUsername(),
                userRegistrationDTO.getFullName(),
                userRegistrationDTO.getAge(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getEmail()
        );

        this.userRepository.save(user);
    }
}
