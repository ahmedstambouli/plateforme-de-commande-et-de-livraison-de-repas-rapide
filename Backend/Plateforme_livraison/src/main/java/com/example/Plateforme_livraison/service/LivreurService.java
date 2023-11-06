package com.example.Plateforme_livraison.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.repository.UserRepository;

@Service
public class LivreurService {

    private final UserRepository userRepository;

    @Autowired
    public LivreurService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createLivreur(User livreur) {
        return userRepository.save(livreur);
    }

    public List<User> getAllLivreurs() {
        return userRepository.findByRole("livreur");
    }

    public Optional<User> getLivreurById(Integer id) {
        return userRepository.findById(id);
    }


    public User blockLivreur(Integer livreurId) throws Exception {
        Optional<User> optionalLivreur = userRepository.findById(livreurId);

        if (optionalLivreur.isPresent()) {
            User livreur = optionalLivreur.get();
            livreur.setEtat(0L);
            userRepository.save(livreur); 
            return livreur;
        } else {
            throw new Exception("Livreur not found with ID: " + livreurId);
        }
    }

    
}
