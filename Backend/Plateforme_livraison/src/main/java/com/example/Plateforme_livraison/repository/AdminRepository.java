package com.example.Plateforme_livraison.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Plateforme_livraison.Models.Administrateur;
import com.example.Plateforme_livraison.Models.Role;
import com.example.Plateforme_livraison.Models.User;


public interface AdminRepository  extends JpaRepository<Administrateur,Long>{
    Administrateur findByEmail(String mail);
        List<Administrateur> findAllByRole(Role role);

    
    
}