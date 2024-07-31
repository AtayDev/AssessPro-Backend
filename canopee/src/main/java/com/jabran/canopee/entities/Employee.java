package com.jabran.canopee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;

    private String country;

    private String favoritePLanguage;

    private List<String> favoriteOS;
    public Employee() {
    }
    public Employee(int id, String fname, String lname, String country, String favoritePLanguage, List<String> favoriteOS) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.favoritePLanguage = favoritePLanguage;
        this.favoriteOS = favoriteOS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavoritePLanguage() {
        return favoritePLanguage;
    }

    public void setFavoritePLanguage(String favoritePLanguage) {
        this.favoritePLanguage = favoritePLanguage;
    }

    public List<String> getFavoriteOS() {
        return favoriteOS;
    }

    public void setFavoriteOS(List<String> favoriteOS) {
        this.favoriteOS = favoriteOS;
    }
}
