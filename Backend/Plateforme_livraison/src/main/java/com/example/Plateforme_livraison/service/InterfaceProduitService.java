package com.example.Plateforme_livraison.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.Plateforme_livraison.Models.Produit;


public interface  InterfaceProduitService{
   /*  ResponseEntity<String> addProduit(Produit p);

    ResponseEntity<List<Produit>> getAllProduits();

    ResponseEntity<Produit> getAllProduitById(Long id);

    ResponseEntity<String> updateProduit(Produit produit, Long id);

    ResponseEntity<String> deleteProduit(Long id);*/

        String uploadImage(String path, MultipartFile file) throws IOException;

    
}
