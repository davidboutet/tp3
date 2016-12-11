package com.tp_3;
import java.util.Arrays;

/**
 * Created by DavidBoutet(BOUD31109107) on 16-12-01.
 * Created by Adrien Lombard(LOMA28049707) on 16-12-01.
 */
public class Contact {
//    attribut de classe
    public static int nbrContactsFavoris = 0;

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
        if(isNullorEmpty(nom) || isNullorEmpty(prenom)){
            throw new ContactInvalideException("Le nom et le prénom ne doivent pas être null ou vide.");
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
        if(!isNullorEmpty(courriel)){
            this.courriel = courriel;
        }
    }

    @Override
    public String toString() {
        String strContact;
        strContact = nom.toUpperCase()+", "+ prenom.substring(0,1).toUpperCase() + prenom.substring(1);

        if(this.favori){
            strContact+= " [FAVORI]\n\n";
        }else{
            strContact +=  "\n\n";
        }

        Telephone[] tableauTelephone = obtenirTelephone();
        if(this.telephones.length>0){
            strContact += "TELEPHONE(S) : \n";
            for(int i = 0; i<tableauTelephone.length; i++){
                strContact+= (i+1)+ ". " + tableauTelephone[i]+"\n";
            }
            strContact+="\n";
        }else{
            strContact += "TELEPHONE(S) : Aucun.\n";
        }
        if(this.adresse==null){
            strContact += "ADRESSE : Aucune.\n";
        } else{
            strContact += "ADRESSE : \n" + this.adresse +"\n\n";
        }
        if(this.courriel==null){
            strContact += "COURRIEL : Aucun.\n";
        } else{
            strContact += "COURRIEL : " + this.courriel+"\n";
        }
        return strContact;
    }
//***********************************************************************//
//***********************************************************************//
//    TOSTRING2 POUR LES TEST SEULEMENT A ENLEVER AVANT LA REMISE        //
//***********************************************************************//
//***********************************************************************//
//    public String toString2 (String attribut) {
//        String s = "";
//        if (attribut == null) {
//            String contact = nom + " " + prenom + " " + favori + "\n";
//            if (telephones == null) {
//                contact = contact + "erreur";
//            } else {
//                for (Telephone tel : telephones) {
//                    contact = contact + tel + "\n";
//                }
//            }
//            contact = contact + adresse;
//            contact = contact + "\n" + courriel;
//
//            s = contact.trim();
//        } else if (attribut.equals("nom")) {
//            s = nom;
//        } else if (attribut.equals("prenom")) {
//            s = prenom;
//        } else if (attribut.equals("adresse")) {
//            s = "null";
//            if (adresse != null) {
//                s = adresse.toString();
//            }
//        } else if (attribut.equals("courriel")) {
//            s = "null";
//            if (courriel != null) {
//                s = courriel;
//            }
//        } else if (attribut.equals("favori")) {
//            s = favori + "";
//        } else if (attribut.equals("nbrTelephones")) {
//            s = nbrTelephones + "";
//        } else if (attribut.equals("telephones")) {
//            if (telephones == null) {
//                s = "erreur";
//            } else {
//                s = "";
//                for (Telephone tel : telephones) {
//                    s = s + tel + "\n";
//                }
//                s = s.trim();
//            }
//        }
//        return s;
//    }


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
        if(isNullorEmpty(nom)){
            throw new ContactInvalideException("Le nom ne peut être null ou vide.");
        }
        this.nom = nom;
    }
    public void setPrenom(String prenom)throws ContactInvalideException{
        if(isNullorEmpty(prenom)){
            throw new ContactInvalideException("Le prénom ne peut être null ou vide.");
        }
        this.prenom = prenom;
    }
    public void setAdresse(Adresse adresse){
        this.adresse = adresse;
    }
    public void setCourriel(String courriel){
        if(isNullorEmpty(courriel)){
            this.courriel = null;
        }
        this.courriel = courriel;
    }
    public void setFavori(Boolean favori){
        this.favori = favori;
        this.nbrContactsFavoris = this.nbrContactsFavoris+1;
    }

    /**
     * Cette méthode permet d’ajouter le tel donné en paramètre au tableau telephones de ce contact.
     * @param tel Le téléphone à ajouter au tableau de téléphones de ce contact.
     */
    public void ajouterTelephone(Telephone tel){
        Boolean hasBeenAdd = false;
        if(tel != null){
            if(isArrayFull(this.telephones)){
                this.telephones = ajouterLongueurTab(this.telephones, 2);
            }
            for (int i = 0; i < this.telephones.length; i++) {
                if(this.telephones[i]==null && !hasBeenAdd){
                    this.telephones[i] = tel;
                    hasBeenAdd = true;
                }
            }
            nbrTelephones++;
        }
    }

    /**
     * Cette méthode permet de doubler le tableau de téléphone si il est plein
     * @param tab le tableau de téléphone
     * @param aAjouter longueur a ajouter au tableau
     * @return le tableau + aJouter
     */
    public Telephone[] ajouterLongueurTab (Telephone[] tab, int aAjouter) {
        return Arrays.copyOf(tab, tab.length + aAjouter);
    }

    /**
     * Cette méthode permet d’obtenir le ième téléphone du tableau de téléphones de ce contact
     * @param ieme  spécifie le téléphone du tableau de téléphones de ce contact à retourner
     * @return le ième téléphone du tableau de téléphones de ce contact
     */
    public Telephone obtenirIemeTelephone(int ieme){
        Telephone t = null;
        ieme = ieme-1;
        if(this.telephones.length>0 && ieme>=0 && ieme <= this.telephones.length-1){
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
        Telephone telSup = obtenirIemeTelephone(ieme);
        int taille = 0;
        
        if(telSup != null){
            for(int i = 0; i < this.telephones.length; i++){
                if(this.telephones[i] != telSup){
                    taille++;
                }
            }
            //nouveau tableau sans le ieme telephone
            Telephone[] tabTelSup = new Telephone[taille];
            for(int j = 0; j < this.telephones.length; j++){
                if(this.telephones[j] != telSup){
                    tabTelSup[j] = this.telephones[j]; 
                }
            }
            s = true;
        }
        return s;
    }

    /**
     * Cette méthode permet de modifier le type, le numero, et le poste du ième téléphone de ce contact
     * @param ieme spécifie le téléphone à modifier dans le tableau de téléphones de ce contact
     * @param type la nouvelle valeur pour le type du téléphone à supprimer
     * @param numero la nouvelle valeur pour le numéro du téléphone à supprimer
     * @param poste la nouvelle valeur pour le poste du téléphone à supprimer
     * @throws ContactInvalideException si le paramètre numero n’est pas null, et n’est pas valide,
     */
    public void modifierTelephone(int ieme, String type, String numero, String poste) throws ContactInvalideException{
        Telephone tel = obtenirIemeTelephone(ieme);
        if (tel!=null){
            if(!isNullorEmpty(type)){
                tel.setType(type);
            }
            if(!isNullorEmpty(numero)){
                try{
                    tel.setNumero(numero);
                }catch (TelephoneInvalideException e){
                    throw new ContactInvalideException("Numero invalide.");
                }
            }
            if(!isNullorEmpty(poste)){
                tel.setPoste(poste);
            }
        }
    }

    /**
     * Cette méthode permet de modifier la valeur de l’attribut de classe nbrContactsFavoris par la valeur passée en paramètre
     * @param nbr la valeur de modification de l’attribut de classe nbrContactsFavoris
     */
    public static void modifierNbrContactsFavoris(int nbr){
        nbrContactsFavoris = nbr;
    }

    /**
     * Cette méthode ppermet d’obtenir la valeur de l’attribut de classe nbrContactsFavoris
     * @return le nombre de contact favoris
     */
    public static int obtenirNbrContactsFavoris(){
        return nbrContactsFavoris;
    }

//    private methode
    private Boolean isNullorEmpty(String s){
        Boolean b = true;
        if(s != null){
            b = s.isEmpty();
        }
        return b;
    }
    private void println(Object m){
        System.out.println(m);
    }
    private Telephone[] obtenirTelephone(){
        Telephone[] myNewArray = {null};
        if(this.telephones.length>0){
            int numOfElements = 0;
            for (int i=0; i<this.telephones.length; i++)
                if (this.telephones[i] != null)
                    numOfElements++;

            //new array of non null elements
            myNewArray = new Telephone[numOfElements];
            for (int i=0,j=0; i<this.telephones.length; i++)
                if (this.telephones[i] != null)
                    myNewArray[j++] = this.telephones[i];

        }
        return myNewArray;
    }

    private Boolean isArrayFull(Telephone[] tel){
        Boolean b = true;
        for (int i=0; i<=tel.length-1; i++) {
            if (tel[i] == null) {
                b = false;
            }
        }
        return b;
    }
}
