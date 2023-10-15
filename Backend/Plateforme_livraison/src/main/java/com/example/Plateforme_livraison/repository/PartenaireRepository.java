package com.example.Plateforme_livraison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Plateforme_livraison.Models.Partenaire;


public interface PartenaireRepository extends  JpaRepository<Partenaire,Long>{
    Partenaire findByEmail(String email);
    Partenaire  findById(int id);
    Partenaire  deleteById(int id);
    
}
