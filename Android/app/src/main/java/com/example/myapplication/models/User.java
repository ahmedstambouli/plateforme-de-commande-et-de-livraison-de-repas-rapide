package com.example.myapplication.models;

public class User {
    private String name;
    private String email;
    private String password;
    private String tel;
    private String address;
    private Long etat;
    private String role;
    private Boolean enabled;

    // Constructors
    public User() {
    }

    public User(String name, String email, String password, String tel, String address, Long etat, String role, Boolean enabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.address = address;
        this.etat = etat;
        this.role = role;
        this.enabled = enabled;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getEtat() {
        return etat;
    }

    public void setEtat(Long etat) {
        this.etat = etat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
