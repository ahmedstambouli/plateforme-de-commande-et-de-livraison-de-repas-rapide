package com.example.Plateforme_livraison.Models;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Partenaires")
@Entity
public class Partenaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "name is required")
    private String name;


    @NotBlank(message = "email is required")
    private String email;


    @NotBlank(message = "password is required")
    @Size(min=8, message=" must be at least 8 characters long.")
    private String password;


    @NotBlank(message = "phone number is required")
    private String tel;


    @NotBlank(message = "Adresse is required")
    private String adresse;

    @NotBlank(message = "role is required")
    private String role;

    @NotBlank(message = "logo is required")
    private String logo;

    private Long etat;
    
    private String type;


    @OneToMany(targetEntity = Produit.class, cascade = CascadeType.ALL)
    private List<Produit> produits; 



}
