package com.example.Plateforme_livraison.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Plateforme_livraison.Models.Partenaire;
import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.repository.PartenaireRepository;
import com.example.Plateforme_livraison.service.PartenaireServiceInterface;

@Service
public class PartenaireService implements PartenaireServiceInterface {


    private final PartenaireRepository partenaireRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public PartenaireService(PartenaireRepository partenaireRepository, BCryptPasswordEncoder passwordEncoder) {
        this.partenaireRepository = partenaireRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public Partenaire registerUser(Partenaire partenaire) {
        // Check if the email is already in use
        if (partenaireRepository.findByEmail(partenaire.getEmail()) != null) {
            throw new UserRegistrationException("Email address is already in use. Please choose another email.");
        }

        // Validate user data
        if (!isUserDataValid(partenaire)) {
            throw new UserRegistrationException("Invalid user data. Please check the provided information.");
        }

        // Encrypt the user's password
        partenaire.setPassword(passwordEncoder.encode(partenaire.getPassword()));

        // Save the user to the repository
        return partenaireRepository.save(partenaire);
    }


    private boolean isUserDataValid(Partenaire partenaire) {
        // Perform data validation here
        // You can add more checks and validation logic
        return partenaire != null && partenaire.getName() != null && partenaire.getPassword() != null;
    }



    @Override
    public ResponseEntity<List<Partenaire>> getAllPartenaire() {

        try {
            List<Partenaire> lisetPartenaires =partenaireRepository.findAll().stream().toList();
            return ResponseEntity.ok(lisetPartenaires);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<Partenaire> getAllPartenaireById(Long id) {
        try {
            Partenaire partenaire=partenaireRepository.findById(id).orElse(null);

            if(partenaire ==null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(partenaire,HttpStatus.OK);
            
        } catch (Exception e) {
                e.printStackTrace();
        }

        return new ResponseEntity<>(new Partenaire(),HttpStatus.INTERNAL_SERVER_ERROR);
    }



    




    



    

    

    


}
