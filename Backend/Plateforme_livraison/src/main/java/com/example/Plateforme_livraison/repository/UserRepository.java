package com.example.Plateforme_livraison.repository;

import com.example.Plateforme_livraison.Models.Role;
import com.example.Plateforme_livraison.Models.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
    User findByemail(String email);
    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);
    
    boolean existsByEmail(String email);
    List<User> findAllByRole(Role role);
    
  List<User> findAllByRoleAndEnabled(Role role, boolean enabled);
}
