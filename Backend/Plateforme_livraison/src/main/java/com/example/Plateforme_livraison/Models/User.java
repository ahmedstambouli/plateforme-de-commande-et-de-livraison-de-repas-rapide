package com.example.Plateforme_livraison.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = " is required")
    private String name;
    @Email(message = "Invalid email address")
    @NotBlank(message = " is required")
    private String email;

    @NotBlank(message = " is required")
    private String password;

    @NotBlank(message = " is required")
    private String role;

    @NotBlank(message = " is required")
    private String address;

    @NotBlank(message = " is required")
    private String tel;






}
