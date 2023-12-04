package com.example.Plateforme_livraison.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Plateforme_livraison.Models.Administrateur;
import com.example.Plateforme_livraison.Models.Partenaire;
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

    public ResponseEntity<List<Administrateur>>getAllInfo(){
        try {
            List<Administrateur> lisetAdmin = adminRepository.findAll().stream().toList();
            return ResponseEntity.ok(lisetAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public boolean updateAdminInfo( Administrateur newAdminInfo) {
        Optional<Administrateur> optionalAdmin = adminRepository.findById(1L);

        if (optionalAdmin!=null) {
            Administrateur existingAdmin = optionalAdmin.get();
            // Update the fields with new information
            existingAdmin.setNom(newAdminInfo.getNom());
            existingAdmin.setEmail(newAdminInfo.getEmail());
            existingAdmin.setAdresse(newAdminInfo.getAdresse());
            existingAdmin.setTel(newAdminInfo.getTel());
            existingAdmin.setRole(newAdminInfo.getRole());

            adminRepository.save(existingAdmin);

            return true; // Updated successfully
        } else {
            return false; // Admin with the given ID not found
        }
    }
}
