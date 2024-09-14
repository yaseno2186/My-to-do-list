package dev.Yass.to_do_list.service;

import dev.Yass.to_do_list.model.User;
import dev.Yass.to_do_list.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Compare the provided password with the stored hashed password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return optionalUser; // Login successful
            }
        }

        return Optional.empty(); // Login failed
    }

    public User registerUser(String username, String password) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, hashedPassword);
        return userRepository.save(newUser);
    }

}