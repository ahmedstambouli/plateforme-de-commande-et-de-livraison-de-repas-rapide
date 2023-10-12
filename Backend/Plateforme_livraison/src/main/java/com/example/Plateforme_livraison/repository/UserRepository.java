package com.example.Plateforme_livraison.repository;

import com.example.Plateforme_livraison.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
