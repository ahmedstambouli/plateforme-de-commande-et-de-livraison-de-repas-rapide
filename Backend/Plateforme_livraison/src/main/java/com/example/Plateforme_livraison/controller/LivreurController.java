package com.example.Plateforme_livraison.controller;

import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.service.LivreurService;
import com.example.Plateforme_livraison.service.UserRegistrationException;
import com.example.Plateforme_livraison.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("public/livreurs")
public class LivreurController  {

    private final LivreurService livreurService;
    private final UserService userService; // Injectez le service UserService

    @Autowired
    public LivreurController(LivreurService livreurService, UserService userService) {
        this.livreurService = livreurService;
        this.userService = userService; // Initialisez le service UserService
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ErrorResponse> errors = new ArrayList<>();

            for (FieldError fieldError : fieldErrors) {
                errors.add(new ErrorResponse("Validation Error", fieldError.getField() + " " + fieldError.getDefaultMessage()));
            }

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (UserRegistrationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error", e.getMessage()));
        }
    }

    private static class ErrorResponse {
        private final String error;
        private final String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
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


    @PutMapping("/deblock/{livreurId}")
    public User deblockLivreur(@PathVariable Integer livreurId) throws Exception {
        return livreurService.deblockLivreur(livreurId);
    }


    @DeleteMapping("/delete/{livreurId}")
public ResponseEntity<String> deleteLivreur(@PathVariable Integer livreurId) {
    try {
        livreurService.deleteLivreur(livreurId);
        return new ResponseEntity<>("Livreur deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}




}
