package com.tp_3;

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
        if(checkNullorEmpty(nom) && checkNullorEmpty(prenom)){
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
        if(!checkNullorEmpty(courriel)){
            this.courriel = courriel;
        }
    }

    @Override
    public String toString() {
        String strContact;
        strContact = nom.toUpperCase( )+", "+ prenom;

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
            strContact += "TELEPHONE(S) : Aucun.\n\n";
        }
        if(this.adresse==null){
            strContact += "ADRESSE : Aucune.\n";
        } else{
            strContact += "ADRESSE : \n" + this.adresse+"\n\n";
        }
        if(this.courriel==null){
            strContact += "COURRIEL : Aucun.\n";
        } else{
            strContact += "COURRIEL : " + this.courriel+"\n";
        }
        return strContact;
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
        Boolean b = false;
        if(tel != null){
            for (int i = 0; i < this.telephones.length; i++) {
                if((this.telephones[i]==null || i==this.telephones.length-1) && !b){
                    this.telephones[i] = tel;
                    b=true;
                }
            }
            nbrTelephones++;
        }
        if(b){
            this.telephones = doublerTableau(this.telephones);
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
     * Cette m�thode permet de supprimer le i�me t�l�phone du tableau de 
     * t�l�phones de ce contact.
     * @param ieme le t�l�phone � supprimer du tableau de t�l�phones du contact
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
    
    public void modifierTelephone(int ieme, String type, String numero, String poste) throws ContactInvalideException{
        Telephone tel = obtenirIemeTelephone(ieme);
        if (tel!=null){
            if(!type.isEmpty()){
                tel.setType(type);
            }
            if(!numero.isEmpty()){
                try{
                    tel.setNumero(numero);
                }catch (TelephoneInvalideException e){
                    throw new ContactInvalideException("Numero invalide.");
                }
            }
            if(!poste.isEmpty()){
                tel.setPoste(poste);
            }
        }
    }

    public void modifierNbrContactsFavoris(int nbr){
        this.nbrContactsFavoris = nbr;
    }
    public int obtenirNbrContactsFavoris(){
        return this.obtenirNbrContactsFavoris();
    }

//    private methode
    private static Boolean checkNullorEmpty(String s){
        return s.isEmpty();
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
}
