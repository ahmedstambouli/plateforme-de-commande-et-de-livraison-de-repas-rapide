package com.example.Plateforme_livraison.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.Plateforme_livraison.Models.Partenaire;


public interface PartenaireRepository extends  JpaRepository<Partenaire,Long>{
   
    Partenaire findByEmail(String email);
    List<Partenaire> findByPassword(String password);

    Partenaire  findById(long id);
    Partenaire  deleteById(int id);

    
    
}
