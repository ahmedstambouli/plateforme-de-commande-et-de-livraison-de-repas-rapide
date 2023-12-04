package com.example.Plateforme_livraison.service;


import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Check if the email is already in use
        if (userRepository.findByemail(user.getEmail()) != null) {
            throw new UserRegistrationException("Email address is already in use. Please choose another email.");
        }

        // Validate user data
        if (!isUserDataValid(user)) {
            throw new UserRegistrationException("Invalid user data. Please check the provided information.");
        }

        // Encrypt the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the repository
        return userRepository.save(user);
    }

    private boolean isUserDataValid(User user) {
        // Perform data validation here
        // You can add more checks and validation logic
        return user != null && user.getName() != null && user.getPassword() != null;
    }


    public boolean isEmailAlreadyExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    
}
