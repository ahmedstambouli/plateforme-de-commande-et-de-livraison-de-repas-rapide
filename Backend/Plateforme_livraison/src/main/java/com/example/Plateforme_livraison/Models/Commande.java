package com.example.Plateforme_livraison.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "is required")
    private String date;

    private boolean etat;  


    public void confirmerCommande() {
        this.etat = true; // Mettre à jour l'état pour indiquer que la commande est confirmée
    }
    public void annulerCommande() {
        this.etat = false; // Mettre à jour l'état pour indiquer que la commande est annulée
    }



    
}