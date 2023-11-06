package com.example.Plateforme_livraison.Authentification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Plateforme_livraison.Authentification.Token.Token;
import com.example.Plateforme_livraison.Authentification.Token.TokenRepository;
import com.example.Plateforme_livraison.Authentification.Token.TokenType;
import com.example.Plateforme_livraison.Authentification.jwt.JwtService;
import com.example.Plateforme_livraison.Models.Role;
import com.example.Plateforme_livraison.Models.User;
import com.example.Plateforme_livraison.repository.UserRepository;
import com.example.Plateforme_livraison.utilis.API;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
  private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(RegisterRequest request) {
        if (!repository.existsByEmail(request.getEmail())) {
            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword())) 
                    .tel(request.getTel())
                    .address(request.getAddress())
                    .enabled(request.getEnabled())
                    .build();
            switch (request.getRole()) {
                case "admin":
                    user.setRole(Role.ADMIN);
                    break;
                case "user":
                    user.setRole(Role.USER);
                    break;
                case "livreur":
                    user.setRole(Role.LIVREUR);
                    break;
                
               
            }
     
        var savedUser = repository.save(user);
        var jwtClaims = new HashMap<String, Object>();
        jwtClaims.put("id", savedUser.getId());
        var jwtToken = jwtService.generateToken(jwtClaims, savedUser);
        
        saveUserToken(savedUser, jwtToken);
        
        return ResponseEntity.ok().body(AuthenticationResponse.builder()
            .token(jwtToken)
            .user(savedUser)
            .build());
    } else {
        return API.getResponseEntity("email already exists", HttpStatus.BAD_REQUEST);
    }
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
      }


      public ResponseEntity<?> authenticate(AuthenticationRequest request) {
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
    if (user.isEnabled()) {
        if (user.isAccountNonExpired()) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var jwtToken = jwtService.generateToken(user, user.getId());
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build());
        } else {
            return ResponseEntity.badRequest().body("Account is expired");
        }
    } else {
        return ResponseEntity.badRequest().body("Account is not active yet");
    }
}
private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
    }
    

