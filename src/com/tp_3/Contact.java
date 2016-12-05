package com.tp_3;

/**
 * Created by DavidBoutet on 16-12-01.
 */
public class Contact {
//    attribut de classe
    private static int nbrContactsFavoris = 0;

//    attribut d'instance
    private String nom;
    private String prenom;
    private Telephone telephones[] = {null, null};
    private int nbrTelephones;
    private Adresse adresse = null;
    private String courriel = null;
    private Boolean favori = false;

//    constructor
    public Contact(){

    }

    public Contact(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

    public Contact(String nom, String prenom, Telephone tel, Adresse adresse, String courriel){
        this(nom, prenom);

    }

    @Override
    public String toString() {
        return super.toString();
    }

//      getter
    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public Adresse getAdresse(){
        return this.adresse;
    }
    public String getCourriel(){
        return this.courriel;
    }
    public int getNbrTelephones(){
        return this.nbrTelephones;
    }
    public Boolean isFavori(){
        return this.favori;
    }

//    setter
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public void setAdresse(Adresse adresse){
        this.adresse = adresse;
    }
    public void setCourriel(String courriel){
        this.courriel = courriel;
    }
    public void setFavori(Boolean favori){
        this.favori = favori;
    }















}
