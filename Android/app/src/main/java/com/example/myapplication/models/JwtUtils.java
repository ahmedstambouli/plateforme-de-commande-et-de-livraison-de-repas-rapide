package com.example.myapplication.models;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtils {

    public static Claims decodeJWT(String jwt) {
        // Parse the JWT token

        return Jwts.parserBuilder()
                .setSigningKey("404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970") // Set your secret key used for signing the token
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}