package com.example.Plateforme_livraison.utilis;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class API {
    public API() {
    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage , HttpStatus httpStatus){
            return  new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}" ,httpStatus);
        }

    }
