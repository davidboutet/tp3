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
            throw new ContactInvalideException("Le nom et le prÃ©nom ne doivent pas Ãªtre null ou vide.");
        }
        this.nom = nom;
        this.prenom = prenom;
    }

    public Contact(String nom, String prenom, Telephone tel, Adresse adresse, String courriel) throws ContactInvalideException{
        this(nom, prenom);
        this.adresse = adresse;

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
            throw new ContactInvalideException("Le nom ne peut Ãªtre null ou vide.");
        }
        this.nom = nom;
    }
    public void setPrenom(String prenom)throws ContactInvalideException{
        if(checkNullorEmpty(prenom)){
            throw new ContactInvalideException("Le prÃ©nom ne peut Ãªtre null ou vide.");
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
            for (int i = 0; i < this.telephones.length; i++) {
                if(this.telephones[i]==null){
                    this.telephones[i] = tel;
                }
                //array full and last index not null(add 2 null to array)
                if ((i==this.telephones.length-1) && (this.telephones[i]!=null)){
                    this.telephones = doublerTableau(this.telephones);
                }
            }
            nbrTelephones++;
        }
    }
    public Telephone[] doublerTableau (Telephone[] tab) {
        Telephone [] tabCopy = new Telephone [tab.length+2];
        for (int i = 0 ; i < tab.length ; i++) {
            tabCopy[i] = tab[i];
        }
        return tabCopy;
    }

    public Telephone obtenirIemeTelephone(int ieme){
        Telephone t = null;
        ieme = ieme-1;
        if(this.telephones.length>0 && ieme>=0){
            int numOfElements = 0;
            for (int i=0; i<this.telephones.length; i++)
                if (this.telephones[i] != null)
                    numOfElements++;

            //new array of non null elements
            Telephone[] myNewArray = new Telephone[numOfElements];
            for (int i=0,j=0; i<this.telephones.length; i++)
                if (this.telephones[i] != null)
                    myNewArray[j++] = this.telephones[i];

            if(ieme<=myNewArray.length-1){
                t = myNewArray[ieme];
            }
        }
        return t;
    }
    
    /**
     * Cette méthode permet de supprimer le ième téléphone du tableau de 
     * téléphones de ce contact.
     * @param ieme le téléphone à supprimer du tableau de téléphones du contact
     * @return true si la suppression a eu lieu, false sinon. 
     */
    public boolean supprimerTelephone(int ieme){
        boolean s = false;
        Telephone TelSup = obtenirIemeTelephone(ieme);
        int taille = 0;
        
        if(TelSup == null){
            s = false;
        } else{
            for(int i = 0; i < this.telephones.length; i++){
                if(this.telephones[i] != TelSup){
                    taille++;
                }
            }
            
            //nouveau tableau sans le ieme telephone
            Telephone[] tabTelSup = new Telephone[taille];
            for(int j = 0; j < this.telephones.length; j++){
                if(this.telephones[j] != TelSup){
                    tabTelSup[j] = this.telephones[j]; 
                }
            }
            s = true;
        }
        return s;
    }
    
    public void modifierTelephone(int ieme, String type, String numero, 
            String poste){
        
        Telephone tel = obtenirIemeTelephone(ieme);
        if(tel == null){
            this.telephones = this.telephones;
        } else{
            for(int i = 0; i < this.telephones.length; i++){
                if(type == null || numero == null || poste == null){
                    this.telephones
                }
            }
        
        }
    }


//    private methode
    private static Boolean checkNullorEmpty(String s){
        return s.isEmpty();
    }
    private void println(Object m){
        System.out.println(m);
    }















}
