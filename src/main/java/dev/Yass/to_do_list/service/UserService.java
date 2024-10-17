package dev.Yass.to_do_list.service;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.Matcher;
import dev.Yass.to_do_list.model.User;
import dev.Yass.to_do_list.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private Matcher passwordEncoder;

    public Optional<User> loginUser(String username, String password) throws EvaluationException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Compare the provided password with the stored hashed password
            if (passwordEncoder.matches(password)) {
                return optionalUser; // Login successful
            }
        }

        return Optional.empty(); // Login failed
    }

    public User registerUser(String username, String password, String email) {
        // Hash the password before saving
        String hashedPassword = ((PasswordEncoder) passwordEncoder).encode(password);
        User newUser = new User(username, hashedPassword);
        return userRepository.save(newUser);
    }

}