package com.example.Plateforme_livraison.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.Plateforme_livraison.Models.Partenaire;
import com.example.Plateforme_livraison.Models.Produit;
import com.example.Plateforme_livraison.repository.ProduitRepository;



@Service
public class ProduitSrevice {


    private final ProduitRepository produitRepository;

    

    public ProduitSrevice(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    

    public ResponseEntity<List<Produit>> getAllProduits() {

        try {
            List<Produit> produit = produitRepository.findAll().stream().toList();
            return ResponseEntity.ok(produit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public String uploadImage(String path, MultipartFile file,String nom,String quantity,String catégori,Partenaire pa) throws IOException {
        String name = file.getOriginalFilename();

        // Générer un nom de fichier aléatoire
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        // Chemin complet
        String filePath = path + File.separator + fileName;

        // Créer le dossier s'il n'existe pas
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        // Copier le fichier
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Enregistrer le nom du fichier dans la base de données
        Produit uploadedFile = new Produit();
        uploadedFile.setFileName(fileName);
        uploadedFile.setName(nom);
        uploadedFile.setCatégori(catégori);
        uploadedFile.setQuantity(quantity);
        uploadedFile.setPartenaire(pa);
        produitRepository.save(uploadedFile);
        return fileName;
    }


    public String updateImage(String path, MultipartFile newImage, String nom, String quantity, String catégori, int produitId) throws IOException {
        Optional<Produit> existingProduitOptional = produitRepository.findById(produitId);
    
        if (existingProduitOptional.isPresent()) {
            Produit existingProduit = existingProduitOptional.get();
    
            // Delete the old image if needed
            if (existingProduit.getFileName() != null) {
                Files.deleteIfExists(Paths.get(path + File.separator + existingProduit.getFileName()));
            }
    
            String name = newImage.getOriginalFilename();
            String randomID = UUID.randomUUID().toString();
            String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));
            String filePath = path + File.separator + fileName;
    
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
    
            Files.copy(newImage.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
    
            existingProduit.setFileName(fileName);
            existingProduit.setName(nom);
            existingProduit.setCatégori(catégori);
            existingProduit.setQuantity(quantity);
            produitRepository.save(existingProduit);
            return fileName;
            } 

            return "erreur";
            
    }
    
    
    public ResponseEntity<Produit> getProduitById(int id) {
        try {
            Produit produit = produitRepository.findById(id).orElse(null);

            if (produit == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(produit, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new Produit(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    
      public ResponseEntity<?> getProduitByIdPartenaire(int id) {
          
        try {
        Produit produit = produitRepository.findById(id).orElse(null);
          long idpartenaire=produit.getPartenaire().getId();
          List<Produit> produits = produitRepository.findByPartenaireId(idpartenaire);

            if (produits == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(produits, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new Produit(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    

    public ResponseEntity<String> deleteProduit(int id) {
        produitRepository.deleteById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Partenaire with id : " + id + "delete");
        return ResponseEntity.noContent().headers(headers).build();

    }
    


    


    /* 
    public List<String> getAllImages(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        List<String> imageNames = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    imageNames.add(file.getName());
                }
            }
        }

        return imageNames;
    }
    */

}
