package com.example.Plateforme_livraison.controller;

import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.service.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("/")
@RequestMapping("public/livreurs")
public class LivreurController {

    private final LivreurService livreurService;

    @Autowired
    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }
@PostMapping("/create")
public ResponseEntity<String> createLivreur(@RequestBody User livreur) {
    if (livreurService.isEmailAlreadyExists(livreur.getEmail())) {
        return new ResponseEntity<>("E-mail already exists", HttpStatus.CONFLICT);
    } else {
        User createdLivreur = livreurService.createLivreur(livreur);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }
}


    @GetMapping("/all")
    public List<User> getAllLivreurs() {
        return livreurService.getAllLivreurs();
    }

    @GetMapping("/{id}")
    public Optional<User> getLivreurById(@PathVariable Long id) {
        return livreurService.getLivreurById(id);
    }

    @PutMapping("/block/{livreurId}")
    public User blockLivreur(@PathVariable Long livreurId) throws Exception {
        return livreurService.blockLivreur(livreurId);
    }


    @PutMapping("/deblock/{livreurId}")
    public User deblockLivreur(@PathVariable Long livreurId) throws Exception {
        return livreurService.deblockLivreur(livreurId);
    }


    @DeleteMapping("/delete/{livreurId}")
public ResponseEntity<String> deleteLivreur(@PathVariable Long livreurId) {
    try {
        livreurService.deleteLivreur(livreurId);
        return new ResponseEntity<>("Livreur deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}




}
