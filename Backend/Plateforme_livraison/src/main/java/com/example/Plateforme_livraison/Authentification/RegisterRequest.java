package com.example.Plateforme_livraison.Authentification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class RegisterRequest {
  private String name;
  private String email;
  private String password;
  private String tel;
  private String address;
  private Long etat;
  private String role ;
  private Boolean enabled;

}
