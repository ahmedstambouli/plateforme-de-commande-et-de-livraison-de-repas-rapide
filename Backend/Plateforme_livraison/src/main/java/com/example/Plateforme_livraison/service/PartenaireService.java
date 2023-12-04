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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.Plateforme_livraison.Models.Partenaire;
import com.example.Plateforme_livraison.repository.PartenaireRepository;

@Service
public class PartenaireService implements PartenaireServiceInterface {

    private final PartenaireRepository partenaireRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public PartenaireService(PartenaireRepository partenaireRepository, BCryptPasswordEncoder passwordEncoder) {
        this.partenaireRepository = partenaireRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Partenaire registerUser(Partenaire partenaire) {
        // Check if the email is already in use
        if (partenaireRepository.findByEmail(partenaire.getEmail()) != null) {
            throw new UserRegistrationException("Email address is already in use. Please choose another email.");
        }

        // Validate user data
        if (!isUserDataValid(partenaire)) {
            throw new UserRegistrationException("Invalid user data. Please check the provided information.");
        }

        // Encrypt the user's password
        partenaire.setPassword(passwordEncoder.encode(partenaire.getPassword()));

        // Save the user to the repository
        return partenaireRepository.save(partenaire);
    }

    private boolean isUserDataValid(Partenaire partenaire) {
        // Perform data validation here
        // You can add more checks and validation logic
        return partenaire != null && partenaire.getName() != null && partenaire.getPassword() != null;
    }

    @Override
    public ResponseEntity<List<Partenaire>> getAllPartenaire() {

        try {
            List<Partenaire> lisetPartenaires = partenaireRepository.findAll().stream().toList();
            return ResponseEntity.ok(lisetPartenaires);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Partenaire> getAllPartenaireById(Long id) {
        try {
            Partenaire partenaire = partenaireRepository.findById(id).orElse(null);

            if (partenaire == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(partenaire, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new Partenaire(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

   

    @Override
    public ResponseEntity<String> deletePartenaire(Long id) {
        partenaireRepository.deleteById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Partenaire with id : " + id + "delete");
        return ResponseEntity.noContent().headers(headers).build();

    }

    public Partenaire blockPartenaire(Long id) throws Exception {
        Optional<Partenaire> optional = partenaireRepository.findById(id);

        if (optional.isPresent()) {
            Partenaire part = optional.get();
            part.setEtat(0L);
            partenaireRepository.save(part);
            return part;
        } else {
            throw new Exception("Partenaire not found with ID: " + id);
        }
    }

    public Partenaire deblockPartenaire(Long id) throws Exception {
        Optional<Partenaire> optional = partenaireRepository.findById(id);

        if (optional.isPresent()) {
            Partenaire part = optional.get();
            part.setEtat(1L);
            partenaireRepository.save(part);
            return part;
        } else {
            throw new Exception("Partenaire not found with ID: " + id);
        }
    }

    @Override
    public ResponseEntity<Partenaire> loginUser(String email, String password) {
        Partenaire p = partenaireRepository.findByEmail(email);

        if (p == null) {
            System.out.println(p);

            return new ResponseEntity<Partenaire>(HttpStatus.UNAUTHORIZED);
        }

        String storedPassword = p.getPassword();

        if (!passwordEncoder.matches(password, storedPassword)) {
            System.out.println(password);
            System.out.println(storedPassword);

            return new ResponseEntity<Partenaire>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Partenaire>(p, null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updatePartnairesonpassword(Partenaire partenaire, Long id) {

        try {
            Optional<Partenaire> OptonalPartenair = partenaireRepository.findById(id);
            if (OptonalPartenair.isPresent()) {
                // return new ResponseEntity<>("Partenaire not found with Id "+id,
                // HttpStatus.INTERNAL_SERVER_ERROR);
                Partenaire partenaire2 = OptonalPartenair.get();
                partenaire2.setName(partenaire.getName());
                partenaire2.setEmail(partenaire.getEmail());
                partenaire2.setAdresse(partenaire.getAdresse());
                partenaire2.setTel(partenaire.getTel());
                partenaire2.setLogo(partenaire.getLogo());
                partenaireRepository.save(partenaire2);
                return new ResponseEntity<String>("update successfuly ", HttpStatus.ACCEPTED);

            }

            return new ResponseEntity<String>("Partenaire not found with id : " + id, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    public String AddPartenaire(
        String path, MultipartFile logo, String name, String email, String password, String adresse, String tel,
        String role, Long etat, String type) throws IOException {
        String namel = logo.getOriginalFilename();

        // Générer un nom de fichier aléatoire
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(namel.substring(namel.lastIndexOf(".")));

        // Chemin complet
        String filePath = path + File.separator + fileName;

        // Créer le dossier s'il n'existe pas
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        // Copier le fichier
        Files.copy(logo.getInputStream(), Paths.get(filePath));
        if (partenaireRepository.findByEmail(email) != null) {
            throw new UserRegistrationException("Email address is already in use. Please choose another email.");
        }
        // Enregistrer le nom du fichier dans la base de données
        Partenaire addPartenaire = new Partenaire();
        addPartenaire.setName(name);
        addPartenaire.setEmail(email);
        addPartenaire.setAdresse(adresse);
        addPartenaire.setLogo(filePath);
        addPartenaire.setPassword(passwordEncoder.encode(password));
        addPartenaire.setTel(tel);
        addPartenaire.setRole(role);
        addPartenaire.setEtat(etat);
        addPartenaire.setType(type);
        partenaireRepository.save(addPartenaire);
        return fileName;
    }


    //methode update partenaire
    public ResponseEntity<String> updatePartenaire(
            String path, MultipartFile newlogo, String name, String email, String password, String adresse, String tel,
            String role, Long etat, String type, Long idp) throws IOException {
        try {
            Optional<Partenaire> existingPartenaireOptional = partenaireRepository.findById(idp);

            // Delete the old image if needed
            if (existingPartenaireOptional.isPresent()) {
                Partenaire existingPartenaire = existingPartenaireOptional.get();
                if (existingPartenaire.getLogo() != null) {
                    Files.deleteIfExists(Paths.get(path + File.separator + existingPartenaire.getLogo()));
                }
                String namef = newlogo.getOriginalFilename();
                String randomID = UUID.randomUUID().toString();
                String fileName = randomID.concat(namef.substring(namef.lastIndexOf(".")));
                String filePath = path + File.separator + fileName;
                System.out.println(namef+"|"+randomID+"|"+fileName+"|"+filePath);

                File f = new File(path);
                if (!f.exists()) {
                    f.mkdir();
                }
                Files.copy(newlogo.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                existingPartenaire.setName(name);
                existingPartenaire.setEmail(email);
                existingPartenaire.setAdresse(adresse);
                existingPartenaire.setLogo(filePath);
                existingPartenaire.setPassword(passwordEncoder.encode(password));
                existingPartenaire.setTel(tel);
                existingPartenaire.setRole(role);
                existingPartenaire.setEtat(etat);
                existingPartenaire.setType(type);
                System.out.println(existingPartenaire);
                partenaireRepository.save(existingPartenaire);
                return new ResponseEntity<String>("update successfuly ",HttpStatus.ACCEPTED);

            } else {
                return new ResponseEntity<String>("Partenaire not found with id : " + idp, HttpStatus.NOT_FOUND);

            }

            // if (existingPartenaireOptional.isPresent()) {
            // Partenaire existingPartenaire = existingPartenaireOptional.get();

            // // Delete the old image if needed
            // if (existingPartenaire.getLogo() != null) {
            // Files.deleteIfExists(Paths.get(path + File.separator +
            // existingPartenaire.getLogo()));
            // }

            // String namef = newlogo.getOriginalFilename();
            // String randomID = UUID.randomUUID().toString();
            // String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));
            // String filePath = path + File.separator + fileName;

            // File f = new File(path);
            // if (!f.exists()) {
            // f.mkdir();
            // }
            // Files.copy(newlogo.getInputStream(), Paths.get(filePath),
            // StandardCopyOption.REPLACE_EXISTING);
            // existingPartenaire.setName(name);
            // existingPartenaire.setEmail(email);
            // existingPartenaire.setAdresse(adresse);
            // existingPartenaire.setLogo(filePath);
            // existingPartenaire.setPassword(passwordEncoder.encode(password));
            // existingPartenaire.setTel(tel);
            // existingPartenaire.setRole(role);
            // existingPartenaire.setEtat(etat);
            // existingPartenaire.setType(type);
            // System.out.println(existingPartenaire);
            // partenaireRepository.save(existingPartenaire);
            // return new ResponseEntity<String>("update successfuly ",
            // HttpStatus.ACCEPTED);

            // }

            // return new ResponseEntity<String>("Partenaire not found with id : " + idp,
            // HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private String imageDirectoryPath="";

    public ResponseEntity<?> getPartenaireImageById(long id) throws IOException {
        Optional<Partenaire> partenaireOptional = partenaireRepository.findById(id);
         if (partenaireOptional.isPresent()) {
             Partenaire partenaire = partenaireOptional.get();
           String fileName = partenaire.getLogo();

             if (fileName != null) {
                 Path filePath = Paths.get(imageDirectoryPath, fileName);
                 File file = filePath.toFile();

                 if (file.exists()) {
                    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                                            System.out.println(resource);


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
