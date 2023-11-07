package com.example.Plateforme_livraison.controller;


import com.example.Plateforme_livraison.Models.User;
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

@RestController
@CrossOrigin(origins = "/")
@RequestMapping("public/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
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
}
