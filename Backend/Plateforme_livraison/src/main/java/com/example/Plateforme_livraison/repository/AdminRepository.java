package com.example.Plateforme_livraison.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Plateforme_livraison.Models.Administrateur;


public interface AdminRepository  extends JpaRepository<Administrateur,Long>{
    Administrateur findByEmail(String mail);
    
    
}