package com.example.Plateforme_livraison.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Plateforme_livraison.Models.Administrateur;
import com.example.Plateforme_livraison.service.AdministrateurService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("public/Admin")
public class AdministrateurController {
  
     private final AdministrateurService administrateurService;

    @Autowired
    public AdministrateurController(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    @GetMapping("/allInfo")
    public ResponseEntity<List<Administrateur>> getAllInfo() {
        return administrateurService.getAllInfo();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdminInfo( @RequestBody Administrateur newAdminInfo) {
        boolean updated = administrateurService.updateAdminInfo( newAdminInfo);

        if (updated) {
            return ResponseEntity.ok("Administrator information updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrator not found.");
        }
    }

}