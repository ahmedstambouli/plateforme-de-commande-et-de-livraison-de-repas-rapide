package com.example.Plateforme_livraison.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Plateforme_livraison.Models.Partenaire;
import com.example.Plateforme_livraison.Models.Produit;
import com.example.Plateforme_livraison.playload.FileResponse;
import com.example.Plateforme_livraison.service.ProduitSrevice;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("public/Prouit")

public class ProduitController {
    @Value("${project.image}")
    private String path;
    @Autowired
    private final ProduitSrevice produitSrevice;

    public ProduitController(ProduitSrevice produitSrevice) {
        this.produitSrevice = produitSrevice;
    }

    @RequestMapping(value = "/AddProduit", method = RequestMethod.POST, headers = "accept=Application/json")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image") MultipartFile image, @RequestParam("name") String name,
            @RequestParam("quantity") String quantity, @RequestParam("categori") String categori,
            @RequestParam("idp") Partenaire pa) {

        String fileName = null;
        try {
            fileName = this.produitSrevice.uploadImage(path, image, name, quantity, categori, pa);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "ERREURR fichier non envoyer"), HttpStatus.OK);

        }

        return new ResponseEntity<>(new FileResponse(fileName, "fichier non envoyer"), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/UpdateProduit", method = RequestMethod.PUT, headers = "accept=Application/json")
    public ResponseEntity<?> UpdateProduit(
            @RequestParam("image") MultipartFile image, @RequestParam("name") String name,
            @RequestParam("quantity") String quantity, @RequestParam("categori") String categori,
            @PathVariable int id) {

        String fileName = null;
        try {
            fileName = this.produitSrevice.updateImage(path, image, name, quantity, categori, id);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "ERREURR fichier non envoyer"), HttpStatus.OK);

        }

        return new ResponseEntity<>(new FileResponse(fileName, "fichier non envoyer"), HttpStatus.OK);
    }

    @GetMapping("/AllProduit")
    public ResponseEntity<List<Produit>> getAllpartenaire() {
        try {
            return produitSrevice.getAllProduits();

        } catch (Exception EX) {
            EX.fillInStackTrace();

        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // cette methode get un Produit avec id
    @GetMapping("/Produitid/{id}")
    public ResponseEntity<Produit> getPartenaireById(@PathVariable int id) {

        try {
            return produitSrevice.getProduitById(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Produit(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // cette methode get un Produit avec id de partenaire
    @GetMapping("/byPartenaire/{Idp}")
    public ResponseEntity<?> getProduitsByPartenaireId(@PathVariable int Idp) {
        try {
            return produitSrevice.getProduitByIdPartenaire(Idp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Produit(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/deleteProduit/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable int id) {
        return produitSrevice.deleteProduit(id);

    }


    @GetMapping("/image/{productId}")
    public ResponseEntity<?> getProductImage(@PathVariable int productId) throws IOException {
        return produitSrevice.getProductImageById(productId);
    }


    

}
/*
 * 
 * @PostMapping("/AddProduit")
 * public ResponseEntity<?> addProduit( @RequestBody Produit produit) {
 * 
 * try {
 * return produitSrevice.addProduit(produit);
 * 
 * 
 * } catch (Exception EX) {
 * EX.fillInStackTrace();
 * 
 * }
 * 
 * return new ResponseEntity<>(new
 * ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
 * }
 * 
 * 
 */