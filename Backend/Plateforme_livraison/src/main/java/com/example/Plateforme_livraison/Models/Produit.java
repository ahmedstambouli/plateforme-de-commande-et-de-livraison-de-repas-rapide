package com.example.Plateforme_livraison.Models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "Produits")
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "is required")
    private String name;

    @NotBlank(message = "is required")  
    private int quantity;  

    @NotBlank(message = "is required")  
    private String  catégori;
    
    @NotBlank(message = "logo is required")
    private String logo;

    @ManyToOne
    @JoinColumn(name = "id_partenaire", nullable = false)
    private Partenaire partenaire;



    
}
