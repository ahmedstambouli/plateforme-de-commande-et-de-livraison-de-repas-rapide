package com.example.Plateforme_livraison.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Plateforme_livraison.Models.Partenaire;
import com.example.Plateforme_livraison.Models.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Integer> {
       List<Produit>  findByPartenaireId(long id);

       Optional<Produit> findById(long id);

       List<Produit> findByPartenaire(Partenaire partenaire);

       




    

        

    
    
}
