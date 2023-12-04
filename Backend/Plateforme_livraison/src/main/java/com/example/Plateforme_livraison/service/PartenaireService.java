package com.example.Plateforme_livraison.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Plateforme_livraison.Models.Partenaire;
import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.repository.PartenaireRepository;

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
            List<Partenaire> lisetPartenaires = partenaireRepository.findAll().stream().toList();
            return ResponseEntity.ok(lisetPartenaires);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Partenaire> getAllPartenaireById(Long id) {
        try {
            Partenaire partenaire = partenaireRepository.findById(id).orElse(null);

            if (partenaire == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(partenaire, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new Partenaire(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updatePartnaire(Partenaire partenaire, Long id) {
        try {
            Optional<Partenaire> OptonalPartenair = partenaireRepository.findById(id);
            if (OptonalPartenair.isPresent()) {
                // return new ResponseEntity<>("Partenaire not found with Id "+id,
                // HttpStatus.INTERNAL_SERVER_ERROR);
                Partenaire partenaire2 = OptonalPartenair.get();
                partenaire2.setName(partenaire.getName());
                partenaire2.setEmail(partenaire.getEmail());
                partenaire2.setAdresse(partenaire.getAdresse());
                partenaire2.setPassword(passwordEncoder.encode(partenaire.getPassword()));
                partenaire2.setTel(partenaire.getTel());
                partenaire2.setLogo(partenaire.getLogo());
                partenaireRepository.save(partenaire2);
                return new ResponseEntity<String>("update successfuly ", HttpStatus.ACCEPTED);

            }

            return new ResponseEntity<String>("Partenaire not found with id : " + id, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    

    @Override
    public ResponseEntity<String> deletePartenaire(Long id) {
        partenaireRepository.deleteById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Partenaire with id : " + id + "delete");
        return ResponseEntity.noContent().headers(headers).build();

    }

    
    public Partenaire blockPartenaire(Long id) throws Exception {
        Optional<Partenaire> optional = partenaireRepository.findById(id);
    
        if (optional.isPresent()) {
            Partenaire part = optional.get();
            part.setEtat( 0L);
            partenaireRepository.save(part);
            return part;
        } else {
            throw new Exception("Partenaire not found with ID: " + id);
        }
    }
    

     public Partenaire deblockPartenaire(Long id) throws Exception {
        Optional<Partenaire> optional = partenaireRepository.findById(id);
    
        if (optional.isPresent()) {
            Partenaire part = optional.get();
            part.setEtat( 1L);
            partenaireRepository.save(part);
            return part;
        } else {
            throw new Exception("Partenaire not found with ID: " + id);
        }
    }


@Override
    public ResponseEntity<Partenaire> loginUser(String email, String password) {
        Partenaire p = partenaireRepository.findByEmail(email);
    
        if (p == null) {
                        System.out.println(p);

            return new ResponseEntity<Partenaire>(HttpStatus.UNAUTHORIZED);
        }
    
        String storedPassword = p.getPassword();
    
        if (!passwordEncoder.matches(password, storedPassword)) {
            System.out.println(password);
            System.out.println(storedPassword);

            return new ResponseEntity<Partenaire>(HttpStatus.UNAUTHORIZED);
        }
    
        return new ResponseEntity<Partenaire>(p, null, HttpStatus.OK);
    }

@Override
public ResponseEntity<String> updatePartnairesonpassword(Partenaire partenaire, Long id) {

      try {
            Optional<Partenaire> OptonalPartenair = partenaireRepository.findById(id);
            if (OptonalPartenair.isPresent()) {
                // return new ResponseEntity<>("Partenaire not found with Id "+id,
                // HttpStatus.INTERNAL_SERVER_ERROR);
                Partenaire partenaire2 = OptonalPartenair.get();
                partenaire2.setName(partenaire.getName());
                partenaire2.setEmail(partenaire.getEmail());
                partenaire2.setAdresse(partenaire.getAdresse());
                partenaire2.setTel(partenaire.getTel());
                partenaire2.setLogo(partenaire.getLogo());
                partenaireRepository.save(partenaire2);
                return new ResponseEntity<String>("update successfuly ", HttpStatus.ACCEPTED);

            }

            return new ResponseEntity<String>("Partenaire not found with id : " + id, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
}
    

}
