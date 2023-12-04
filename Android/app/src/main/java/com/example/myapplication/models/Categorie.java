package com.example.myapplication.models;

public class Categorie {
    private String title;
    private String pict;

    public Categorie() {
    }

    public Categorie(String title, String pict) {
        this.title = title;
        this.pict = pict;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }
}

