package com.example.Plateforme_livraison.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.Plateforme_livraison.Models.Partenaire;
import com.example.Plateforme_livraison.Models.Produit;
import com.example.Plateforme_livraison.repository.PartenaireRepository;
import com.example.Plateforme_livraison.repository.ProduitRepository;
import org.springframework.http.MediaType;

@Service
public class ProduitSrevice {

    private final ProduitRepository produitRepository;
    private final PartenaireRepository partenaireRepository;

    public ProduitSrevice(ProduitRepository produitRepository,PartenaireRepository partenaireRepository) {
        this.produitRepository = produitRepository;
        this.partenaireRepository = partenaireRepository;

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

    public String AddProducts(String path, MultipartFile file, String nom, String quantity, String categori,
            double price, Partenaire pa) throws IOException {
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
        uploadedFile.setCategori(categori);
        uploadedFile.setQuantity(quantity);
        uploadedFile.setPrice(price);
        uploadedFile.setPartenaire(pa);
        produitRepository.save(uploadedFile);
        return fileName;
    }

    public String updateImage(String path, MultipartFile newImage, String nom, String quantity, String categori,
            double price, int produitId) throws IOException {
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
            existingProduit.setCategori(categori);
            existingProduit.setQuantity(quantity);
            existingProduit.setPrice(price);
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

    public List<Produit> getProduitByIdPartenaire(Partenaire partenaire) {
        
             return produitRepository.findByPartenaire(partenaire);
        

    //     Partenaire partenaire = partenaireRepository.findById(id).orElse(null);
    //   List<Produit> produis=  partenaire.getProduits();
    //   System.out.println(produis);
    //            return new ResponseEntity<>(produis, HttpStatus.OK);

        
        //return null;
        // try {
        // Produit produit = produitRepository.findById(id).orElse(null);
        // long idpartenaire = produit.getPartenaire().getId();
        // List<Produit> produits = produitRepository.findByPartenaireId(idpartenaire);
        // System.out.println(produits);

        // return new ResponseEntity<>(produits, HttpStatus.OK);

        // if (produits == null) {
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        // return new ResponseEntity<>(produits, HttpStatus.OK);

        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // return new ResponseEntity<>(new Produit(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteProduit(int id) {
        produitRepository.deleteById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Partenaire with id : " + id + "delete");
        return ResponseEntity.noContent().headers(headers).build();

    }

    private String imageDirectoryPath = "images/";

    public ResponseEntity<?> getProductImageById(int id) throws IOException {
        Optional<Produit> produitOptional = produitRepository.findById(id);

        if (produitOptional.isPresent()) {
            Produit produit = produitOptional.get();
            String fileName = produit.getFileName();

            if (fileName != null) {
                Path filePath = Paths.get(imageDirectoryPath, fileName);
                File file = filePath.toFile();
                System.out.println(file);
                if (file.exists()) {
                    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + file.getName())
                            .contentType(MediaType.IMAGE_JPEG) // Adjust based on your image type
                            .body(resource);
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

}
