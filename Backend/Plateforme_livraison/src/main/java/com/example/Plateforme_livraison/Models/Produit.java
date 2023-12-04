package com.example.Plateforme_livraison.Models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fileName;

   @NotBlank(message = "is required")
    private String name;

    @NotBlank(message = "is required")  
    private String quantity;  

    @NotBlank(message = "is required")  
    private String  categori;

    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0.0")
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "id_partenaire")
    private Partenaire partenaire;



    
}
