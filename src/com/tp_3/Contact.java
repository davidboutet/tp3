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
            this.nbrTelephones++;
        }
        if(!isNullorEmpty(courriel)){
            this.courriel = courriel;
        }
    }

    @Override
    public String toString() {
        String strContact;
        strContact = nom.toUpperCase()+", "+ prenom;

        if(this.favori){
            strContact+= " [FAVORI]\n\n";
        }else{
            strContact +=  "\n\n";
        }

        Telephone[] tableauTelephone = obtenirTelephone();
        if(tableauTelephone.length == 0){
            strContact += "TELEPHONE(S) : Aucun.\n\n";
        }else{
            strContact += "TELEPHONE(S) : \n";
            for(int i = 0; i<tableauTelephone.length; i++){
                strContact+= (i+1)+ ". " + tableauTelephone[i]+"\n";
            }
            strContact+="\n";
        }
        if(this.adresse==null){
            strContact += "ADRESSE : Aucune.\n\n";
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

//  getter
    /**
     * Cette méthode permet de retourner le nom du contact(String)
     */
    public String getNom(){
        return this.nom;
    }
    /**
     * Cette méthode permet de retourner le prenom du contact(String)
     */
    public String getPrenom(){
        return this.prenom;
    }
    /**
     * Cette méthode permet de retourner l'adresse du contact(Adresse)
     */
    public Adresse getAdresse(){
        return this.adresse;
    }
    /**
     * Cette méthode permet de retourner le courriel du contact(String)
     */
    public String getCourriel(){
        return this.courriel;
    }
    /**
     * Cette méthode permet de retourner le nombre de telephone du contact(int)
     */
    public int getNbrTelephones(){
        return this.nbrTelephones;
    }
    /**
     * Cette méthode permet de savoir si le contact est favori(Boolean)
     */
    public Boolean isFavori(){
        return this.favori;
    }

//  setter
    /**
     * Cette méthode permet de setter le nom du contact
     * Ne peut pas etre null ou vide
     * @param nom
     * @throws com.tp_3.ContactInvalideException
     */
    public void setNom(String nom) throws ContactInvalideException{
        if(isNullorEmpty(nom)){
            throw new ContactInvalideException("Le nom ne peut être null ou vide.");
        }
        this.nom = nom;
    }
    /**
     * Cette méthode permet de setter le prenom du contact
     * Ne peut pas etre null ou vide
     * @param prenom
     * @throws com.tp_3.ContactInvalideException
     */
    public void setPrenom(String prenom)throws ContactInvalideException{
        if(isNullorEmpty(prenom)){
            throw new ContactInvalideException("Le prénom ne peut être null ou vide.");
        }
        this.prenom = prenom;
    }

    /**
     * Cette méthode permet de setter l'adresse du contact
     * @param adresse de type Adresse
     */
    public void setAdresse(Adresse adresse){
        this.adresse = adresse;
    }
    /**
     * Cette méthode permet de setter le courriel du contact
     * @param courriel de type String
     */
    public void setCourriel(String courriel){
        if(isNullorEmpty(courriel)){
            this.courriel = null;
        }
        this.courriel = courriel;
    }
    /**
     * Cette méthode permet de setter si le contact est favori ou non
     * @param favori de type Boolean
     */
    public void setFavori(Boolean favori){
        this.favori = favori;
        if(favori){
            this.nbrContactsFavoris++;
        }else{
            this.nbrContactsFavoris--;
        }
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
                    this.nbrTelephones++;
                    hasBeenAdd = true;
                }
            }
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
            for (int i=0,j=0; i<this.telephones.length; i++) {
                if (this.telephones[i] != null) {
                    myNewArray[j++] = this.telephones[i];
                }
            }

            if(ieme<myNewArray.length){
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

        if(telSup != null){
            Telephone[] tabTelSup = new Telephone[this.telephones.length];
            for(int i=0,j=0; j < this.telephones.length; j++){
                if(this.telephones[j] != telSup){
                    tabTelSup[i++] = this.telephones[j];
                }
            }
            this.telephones = tabTelSup;
            this.nbrTelephones--;
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
     * Cette méthode permet d’obtenir la valeur de l’attribut de classe nbrContactsFavoris
     * @return le nombre de contact favoris
     */
    public static int obtenirNbrContactsFavoris(){
        return nbrContactsFavoris;
    }

//    private methode
    /**
     * Cette methode permet de verifier si un string est null ou vide
     * @param s type String valeur a verifier
     * @return Boolean
     */
    private Boolean isNullorEmpty(String s){
        Boolean b = true;
        if(s != null){
            b = s.isEmpty();
        }
        return b;
    }
    /**
     * Cette methode est un raccourci a System.out.println();
     * @param m type Object a afficher dans un System.out.println();
     */
    private void println(Object m){
        System.out.println(m);
    }

    /**
     * Cette methode permet d'obtenir la liste des telephones et retire les valeurs null du tableau
     * @return un nouveau tableau de telephone excluant les valeurs null
     */
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

    /**
     * Cette methode permet de savoir si un tableau de type Telephone est plein
     * @param tel
     * @return retourne true si aucune case du tableau n'est egale a null
     */
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
