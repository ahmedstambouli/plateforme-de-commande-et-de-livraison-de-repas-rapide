package com.example.Plateforme_livraison.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Plateforme_livraison.Models.Commande;

public interface CommandeRepository extends JpaRepository<Commande,Long> {

    static Optional<Commande> getCommandeById(Integer id) {
        return null;
    }

    Optional<Commande> findById(Integer id);
    
    
    
}