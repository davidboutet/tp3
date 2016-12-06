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

    public Contact(String nom, String prenom) throws ContactInvalideException{
        if(checkNullorEmpty(nom) && checkNullorEmpty(prenom)){
            throw new ContactInvalideException("Le nom et le prénom ne doivent pas être null ou vide.");
        }
        this.nom = nom;
        this.prenom = prenom;
    }

    public Contact(String nom, String prenom, Telephone tel, Adresse adresse, String courriel) throws ContactInvalideException{
        this(nom, prenom);
        if(tel != null){
            this.telephones[0] = tel;
            this.nbrTelephones = this.nbrTelephones+1;
        }
        if(!checkNullorEmpty(courriel)){
            this.courriel = courriel;
        }


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
    public void setNom(String nom) throws ContactInvalideException{
        if(checkNullorEmpty(nom)){
            throw new ContactInvalideException("Le nom ne peut être null ou vide.");
        }
        this.nom = nom;
    }
    public void setPrenom(String prenom)throws ContactInvalideException{
        if(checkNullorEmpty(prenom)){
            throw new ContactInvalideException("Le prénom ne peut être null ou vide.");
        }
        this.prenom = prenom;
    }
    public void setAdresse(Adresse adresse){
        this.adresse = adresse;
    }
    public void setCourriel(String courriel){
        if(checkNullorEmpty(courriel)){
            this.courriel = null;
        }
        this.courriel = courriel;
    }
    public void setFavori(Boolean favori){
        this.favori = favori;
        this.nbrContactsFavoris = this.nbrContactsFavoris+1;
    }


    public void ajouterTelephone(Telephone tel){
        if(tel != null){
            for (int i = 0 ; i < this.telephones.length ; i++) {
                if(this.telephones[i]==null){
                    this.telephones[i] = tel;
                }
                //array full and last index not null(add 2 null to array)
                if ((i==this.telephones.length-1) && (this.telephones[i]!=null)){
                    this.telephones = doublerTableau(this.telephones);
                }
            }
        }
    }


    public static Telephone[] doublerTableau (Telephone[] tab) {
        Telephone [] tabCopy = new Telephone [tab.length+2];
        for (int i = 0 ; i < tab.length ; i++) {
            tabCopy[i] = tab[i];
        }
        return tabCopy;
    }




//    private methode
    private static Boolean checkNullorEmpty(String s){
        return s.isEmpty();
    }
    private void println(Object m){
        System.out.println(m);
    }

















}
