package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());

        if(byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        User user = new User(
                registerDTO.getUsername(),
                registerDTO.getPassword(),
                registerDTO.getEmail(),
                registerDTO.getFullName(),
                registerDTO.getAge()
        );

        this.userRepository.save(user);
     }

}
