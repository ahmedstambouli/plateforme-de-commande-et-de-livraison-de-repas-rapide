package com.example.Plateforme_livraison.controller;


import com.example.Plateforme_livraison.Models.Commande;
import com.example.Plateforme_livraison.repository.CommandeRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    @GetMapping("/allcommandes")
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @GetMapping("/commandes/{id}")
     public Optional<Commande> getCommandeById(@PathVariable Integer id) {
        return CommandeRepository.getCommandeById(id);
    }

    @PostMapping("/ajoutcommande")
    public Commande createCommande(@Valid @RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

    @PostMapping("/confirmer/{id}")
    public ResponseEntity<String> confirmerCommande(@PathVariable Long id) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);

        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            
            // Vérifier si la commande n'est pas déjà confirmée ou annulée
            if (!commande.isEtat()) {
                commande.confirmerCommande();
                commandeRepository.save(commande);
                return ResponseEntity.ok("Commande confirmée avec succès.");
            } else {
                return ResponseEntity.badRequest().body("Impossible de confirmer une commande déjà confirmée ou annulée.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/annuler/{id}")
    public ResponseEntity<String> annulerCommande(@PathVariable Long id) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);

        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            
            // Vérifier si la commande n'est pas déjà confirmée ou annulée
            if (!commande.isEtat()) {
                commande.annulerCommande();
                commandeRepository.save(commande);
                return ResponseEntity.ok("Commande annulée avec succès.");
            } else {
                return ResponseEntity.badRequest().body("Impossible d'annuler une commande déjà confirmée ou annulée.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}

    
