package com.example.Plateforme_livraison.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Plateforme_livraison.Models.Administrateur;
import com.example.Plateforme_livraison.repository.AdminRepository;

@Service
public class AdministrateurService {
    private final AdminRepository adminRepository ;
    public AdministrateurService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final BCryptPasswordEncoder passwordEncoder;

    public Administrateur registerAdministrateur(Administrateur administrateur) {
        // Check if the email is already in use
        if (adminRepository.findByEmail(administrateur.getEmail()) != null) {
            throw new UserRegistrationException("Email address is already in use. Please choose another email.");
        }

        // Validate administrateur data
        if (!isAdministrateurDataValid(administrateur)) {
            throw new UserRegistrationException("Invalid administrateur data. Please check the provided information.");
        }

        // Encrypt the administrateur's password
        administrateur.setPassword(passwordEncoder.encode(administrateur.getPassword()));

        // Save the administrateur to the repository
        return adminRepository.save(administrateur);
    }

    private boolean isAdministrateurDataValid(Administrateur administrateur) {
        // Perform data validation here
        // You can add more checks and validation logic
        return administrateur != null && administrateur.getNom() != null
                && administrateur.getPassword() != null && administrateur.getEmail() != null;
    }
}
