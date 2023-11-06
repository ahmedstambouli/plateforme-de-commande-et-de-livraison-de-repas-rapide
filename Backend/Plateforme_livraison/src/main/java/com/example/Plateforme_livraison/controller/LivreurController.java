package com.example.Plateforme_livraison.controller;

import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.service.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("public/livreurs")
public class LivreurController {

    private final LivreurService livreurService;

    @Autowired
    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @PostMapping("/create")
    public User createLivreur(@RequestBody User livreur) {
        return livreurService.createLivreur(livreur);
    }

    @GetMapping("/all")
    public List<User> getAllLivreurs() {
        return livreurService.getAllLivreurs();
    }

    @GetMapping("/{id}")
    public Optional<User> getLivreurById(@PathVariable Integer id) {
        return livreurService.getLivreurById(id);
    }

    @PutMapping("/block/{livreurId}")
    public User blockLivreur(@PathVariable Integer livreurId) throws Exception {
        return livreurService.blockLivreur(livreurId);
    }
}
