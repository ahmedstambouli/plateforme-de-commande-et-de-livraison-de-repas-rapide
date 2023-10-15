package com.example.Plateforme_livraison.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Plateforme_livraison.Models.Partenaire;

public interface PartenaireServiceInterface {



    ResponseEntity<List<Partenaire>> getAllPartenaire();

    ResponseEntity<Partenaire> getAllPartenaireById(Long id);

    
}
