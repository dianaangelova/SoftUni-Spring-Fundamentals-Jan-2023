package bg.softuni.examtemplate.service;

import bg.softuni.examtemplate.repository.UserRepository;
import bg.softuni.examtemplate.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
