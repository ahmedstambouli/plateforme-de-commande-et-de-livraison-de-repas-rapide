package com.example.myapplication.models;

import java.util.List;

public class RegisterResponse {

        private int id;
        private String name;
        private String email;
        private String password;
        private String address;
        private String tel;
        private Long etat;
        private Boolean enabled;
        private String role;
        private List<Authority> authorities;
        private String username;
        private Boolean accountNonExpired;
        private Boolean credentialsNonExpired;
        private Boolean accountNonLocked;

        // Add getters and setters

        public static class Authority {
            private String authority;

            // Add getters and setters
        }
    }


