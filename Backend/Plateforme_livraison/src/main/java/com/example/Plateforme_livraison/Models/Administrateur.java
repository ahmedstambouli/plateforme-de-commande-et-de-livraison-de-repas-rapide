package com.example.Plateforme_livraison.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
@Entity
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = " is required")
    private String nom;
    @Email (message = "invalid adress email")
    @NotBlank (message = " is required")
    private String email;

    @NotBlank (message = " is required")
    @Size(min=8, message=" must be at least 8 characters long.")
    private String password;

    @NotBlank (message = "  is required")
    private String adresse;

     @NotBlank (message = "  is required")
     private String tel;

     @NotBlank (message = "  is required")
     private String role;





    
}