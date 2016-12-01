package com.tp_3;

/**
 * Cette classe modelise une adresse postale.
 * Classe fournie dans le cadre du TP3 INF1120 A16.
 * @author melanie lord
 * @version novembre 2016
 */
public class Adresse {
   
   //--------------------
   //ATTRIBUTS D'INSTANCE
   //--------------------
   
   private String noPorte = "1";                //le no de porte 
   private String nomRue = "rue";               //le nom de la rue
   private String apt = "apt";                  //le no d'apartement
   private String ville = "ville";              //la ville
   private String provinceEtat = "province";    //la province (ou l'etat, ou...)
   private String pays = "pays";                //le pays
   private String codePostal = "code postal";   //le code postal
   
   //--------------
   //CONSTRUCTEURS
   //--------------
   
   /**
    * Construit un objet Adresse en initialisant ses attributs avec les valeurs
    * passees en parametres. De plus :
    *    - si l'apt donne en parametre est vide, cet attribut sera initialise 
    *      a null.
    *    - si le code postal donne en parametre est vide, cet attribut sera 
    *      initialise a null.
    * @param noPorte le numero de porte de cette adresse.
    * @param nomRue le nom de rue de cette adresse.
    * @param apt l'appartement de cette adresse.
    * @param ville la ville de cette adresse.
    * @param provinceEtat la province (ou l'etat ou ...) de cette adresse.
    * @param pays le pays de cette adresse.
    * @param codePostal le code postal de cette adresse.
    * @throws com.tp_3.AdresseInvalideException si :
    *             - le noPorte donne est null ou vide
    *             - le nomRue donne est null ou vide
    *             - la ville donnee est null ou vide
    *             - la provinceEtat donnee est null ou vide
    *             - le pays donne est null ou vide
    */
   public Adresse (String noPorte, String nomRue, String apt, String ville, 
           String provinceEtat, String pays, String codePostal) 
                                                throws AdresseInvalideException {
      
      if (noPorte == null || noPorte.isEmpty() || nomRue == null || nomRue.isEmpty() 
              || ville == null || ville.isEmpty() || provinceEtat == null 
              || provinceEtat.isEmpty() || pays == null || pays.isEmpty()) 
         throw new AdresseInvalideException();
      
      this.noPorte = noPorte;
      this.nomRue = nomRue;
      if (apt != null && apt.isEmpty()) 
         apt = null;
      this.apt = apt;
      this.ville = ville;
      this.provinceEtat = provinceEtat;
      this.pays = pays;
      if (codePostal != null && codePostal.isEmpty())
         codePostal = null;
      this.codePostal = codePostal;
   }
   
   /**
    * Constructeur vide qui ne fait rien. L'objet cree aura les valeurs 
    * par defaut suivantes : 
    * - no porte         = "1"
    * - nom de la rue    = "rue"
    * - no apartement    = "apt"
    * - ville            = "ville"
    * - province ou etat = "province"
    * - pays             = "pays"
    * - codePostal       = "code postal"
    */
   public Adresse () {
   }

   //------------------
   //GETTERS ET SETTERS
   //------------------
   
   /**
    * Permet d'obtenir le pays de cette adresse.
    * @return le pays de cette adresse.
    */
   public String getPays() {
      return pays;
   }

   /**
    * Modifie le pays de cette adresse par le pays donne en parametre. 
    * Si le pays donne est null ou vide, aucune modification n'est effectuee, 
    * et la methode leve une exception AdresseInvalideException.
    * @param pays le nouveau pays de cette adresse.
    * @throws com.tp_3.AdresseInvalideException si pays est null ou vide.
    */
   public void setPays(String pays) throws AdresseInvalideException {
      if (pays == null || pays.isEmpty()) 
         throw new AdresseInvalideException();
      this.pays = pays;
   }
   
   /**
    * Permet d'obtenir le numero de porte de cette adresse.
    * @return le numero de porte de cette adresse.
    */
   public String getNoPorte() {
      return noPorte;
   }
   
   /**
    * Modifie le numero de porte de cette adresse par le noPorte donne en 
    * parametre. Si le noPorte donne est null ou vide, aucune modification 
    * n'est effectuee, et la methode leve une exception AdresseInvalideException.
    * @param noPorte le nouveau numero de porte.
    * @throws com.tp_3.AdresseInvalideException si le noPorte donne est null ou vide.
    */
   public void setNoPorte(String noPorte) throws AdresseInvalideException {
      if (noPorte == null || noPorte.isEmpty())
         throw new AdresseInvalideException();
      this.noPorte = noPorte;
   }

   /**
    * Permet d'obtenir le nom de rue de cette adresse.
    * @return le nom de rue de cette adresse.
    */
   public String getNomRue() {
      return nomRue;
   }

   /**
    * Modifie le nom de rue de cette adresse par le nomRue donne en parametre. 
    * Si le nomRue donne est null ou vide, aucune modification n'est effectuee, 
    * et la methode leve une exception AdresseInvalideException.
    * @param nomRue le nouveau nom de rue de cette adresse.
    * @throws com.tp_3.AdresseInvalideException si le nomRue donne est null ou vide.
    */
   public void setNomRue(String nomRue) throws AdresseInvalideException {
      if (nomRue == null || nomRue.isEmpty()) 
         throw new AdresseInvalideException();
      this.nomRue = nomRue;
   }

   /**
    * Permet d'obtenir l'appartement de cette adresse.
    * @return l'appartement de cette adresse.
    */
   public String getApt() {
      return apt;
   }

   /**
    * Modifie l'apartement de cette adresse par l'apt donne en parametre.
    * - si l'apt donne en parametre est vide, l'apartement de cette adresse 
    *   sera initialise a null.
    * @param apt le nouvel appartement de cette adresse.
    */
   public void setApt(String apt) {
      if (apt != null && apt.isEmpty()) 
         apt = null;
      this.apt = apt;
   }

   /**
    * Permet d'obtenir la ville de cette adresse.
    * @return la ville de cette adresse.
    */
   public String getVille() {
      return ville;
   }

   /**
    * Modifie la ville de cette adresse par la ville donnee en parametre. 
    * Si la ville donnee est null ou vide, aucune modification n'est effectuee, 
    * et la methode leve une exception AdresseInvalideException.
    * @param ville le nouvelle ville de cette adresse.
    * @throws com.tp_3.AdresseInvalideException si la ville donnee est null ou vide.
    */
   public void setVille(String ville) throws AdresseInvalideException {
      if (ville == null || ville.isEmpty()) 
         throw new AdresseInvalideException();
      this.ville = ville;
   }

   /**
    * Permet d'obtenir la province (ou l'etat ou...) de cette adresse.
    * @return la province (ou l'etat ou...) de cette adresse.
    */
   public String getProvinceEtat() {
      return provinceEtat;
   }

   /**
    * Modifie la province (ou etat ou ...) de cette adresse par la provinceEtat
    * donnee en parametre. Si la provinceEtat donnee est null ou vide, aucune 
    * modification n'est effectuee, et la methode leve une exception 
    * AdresseInvalideException.
    * @param provinceEtat la nouvelle province (ou etat ou...) de cette adresse.
    * @throws com.tp_3.AdresseInvalideException si la provinceEtat donnee est null ou vide.
    */
   public void setProvinceEtat(String provinceEtat) throws AdresseInvalideException {
      if (provinceEtat == null || provinceEtat.isEmpty()) 
         throw new AdresseInvalideException();
      this.provinceEtat = provinceEtat;
   }

   /**
    * Permet d'obtenir le code postal de cette adresse.
    * @return le code postal de cette adresse.
    */
   public String getCodePostal() {
      return codePostal;
   }

   /**
    * Modifie le code postal de cette adresse par le codePostal donne en parametre.
    * - si le codePostal donne en parametre est vide, le code postal de cette 
    *   adresse sera initialise a null.
    * @param codePostal le nouveau code postal de cette adresse.
    */
   public void setCodePostal(String codePostal) {
      if (codePostal != null && codePostal.isEmpty())
         codePostal = null;
      this.codePostal = codePostal;
   }
   
   //------------------------------------
   //AUTRES METHODES D'INSTANCE PUBLIQUES
   //------------------------------------
   
   /**
    * Construit une representation de cette adresse sous forme de chaine de 
    * caracteres. 
    * Format si l'appartement et le code postal sont null : 
    *                noPorte nomRue 
    *                ville, provinceEtat, PAYS 
    * Format si seulement le code postal est null : 
    *                noPorte nomRue, apt. apt
    *                ville, provinceEtat, PAYS 
    * Format si seulement l'appartement est null : 
    *                noPorte nomRue
    *                ville, provinceEtat, PAYS 
    *                CODEPOSTAL
    * * Format si l'appartement et le code postal ne sont pas null : 
    *                noPorte nomRue, apt. apt
    *                ville, provinceEtat, PAYS 
    *                CODEPOSTAL
    * @return 
    */
   public String toString () {
      String cp;
      String adr = noPorte + " " + nomRue;
      if (apt != null) {
         adr = adr + ", apt. " + apt; 
      }
      adr = adr + "\n" + ville + ", " + provinceEtat + ", " + pays.toUpperCase();
      if (codePostal != null) {
         cp = codePostal;
         if (cp.length() == 6) {
            cp = cp.substring(0, 3) + " " + cp.substring(3);
         }
         adr = adr + "\n" + cp.toUpperCase();
      }
      return adr;
   }

} //fin classe Adresse



//--------------------------
//CLASSE EXCEPTION
//--------------------------

/**
 * Exception levee lorsqu'on tente de creer ou de modifier un objet Adresse 
 * avec des informations invalides.
 * @author melanie lord
 * @version novembre 2016
 */
class AdresseInvalideException extends Exception {

}
