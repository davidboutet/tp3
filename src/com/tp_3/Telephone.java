package com.tp_3;

/**
 * Cette classe modelise un numero de telephone. 
 * Classe fournie dans le cadre du TP3 INF1120 A16.
 * @author melanie lord
 * @version novembre 2016.
 */
public class Telephone {
   
   //--------------
   //CONSTANTES
   //--------------
   
   //type de tel. par defaut
   public final static String TYPE_PAR_DEFAUT = "Domicile"; 
   
   //le numero de tel. doit etre de longueur 7 ou bien 10 (pour verification).
   public final static int LNG_NUM_1 = 7;   //lng valide pour le numero
   public final static int LNG_NUM_2 = 10;  //autre lng valide pour le numero
   
   //--------------------
   //ATTRIBUTS D'INSTANCE
   //--------------------
   
   private String type;    //type de telephone (portable, domicile, etc...)
   private String numero;  //le numero de telephone
   private String poste;   //le poste telephonique
   
   //--------------
   //CONSTRUCTEURS
   //--------------
   
   /**
    * Construit un objet Telephone en initialisant son type, son numero, et son
    * poste avec les valeurs passees en parametres. 
    * - Si le type donne en parametre est null ou vide, il sera remplace par 
    *   le type par defaut TYPE_PAR_DEFAUT. 
    * - Si le poste donne en parametre est vide, il sera remplace par null.
    * - Si le numero donne en parametre n'est pas valide, une exception
    *   TelephoneInvalideException est levee. Un numero valide doit etre 
    *   compose de 7 OU 10 caracteres numeriques. 
    * @param type le type de ce telephone (portable, domicile, bureau...).
    * @param numero le numero de ce telephone.
    * @param poste le poste telephonique de ce telephone.
    * @throws com.tp_3.TelephoneInvalideException si le numero donne n'est pas valide.
    */
   public Telephone (String type, String numero, String poste) 
                                             throws TelephoneInvalideException {
      if (!numeroTelValide(numero)) {
         throw new TelephoneInvalideException();
      }
      this.numero = numero;
      
      if (type == null || type.isEmpty()) 
         type = TYPE_PAR_DEFAUT;
      
      this.type = type;
      
      if (poste != null && poste.isEmpty()) {
         poste = null;
      }
      
      this.poste = poste;
   }
   
   /**
    * Construit un objet Telephone avec le type par defaut = TYPE_PAR_DEFAUT, 
    * un poste null, et le numero donne en parametre.
    * - Si le numero donne en parametre n'est pas valide, une exception
    *   TelephoneInvalideException est levee. Un numero valide doit etre 
    *   compose de 7 OU 10 caracteres numeriques.
    * @param numero le numero de ce telephone.
    * @throws com.tp_3.TelephoneInvalideException si le numero donne n'est pas valide.
    */
   public Telephone (String numero) throws TelephoneInvalideException {
      this(null, numero, null);
   }
   
   //-------------------
   //GETTERS ET SETTERS
   //-------------------
   
   /**
    * Permet d'obtenir la valeur du type de ce telephone.
    * @return le type de ce telephone.
    */
   public String getType() {
      return type;
   }

   /**
    * Modifie le type de ce telephone par celui passe en parametre.
    * - Si le type donne en parametre est null ou vide, il sera remplace par 
    *   le type par defaut = TYPE_PAR_DEFAUT. 
    * @param type le nouveau type de telephone.
    */
   public void setType(String type) {
      if (type == null || type.isEmpty())
         type = TYPE_PAR_DEFAUT;
      
      this.type = type;
   }

   /**
    * Permet d'obtenir le numero de ce telephone.
    * @return le numero de ce telephone.
    */
   public String getNumero() {
      return numero;
   }

   /**
    * Modifie le numero de ce telephone par celui passe en parametre.
    * - Si le numero donne en parametre n'est pas valide, aucune modification 
    *   n'est effectuee, et une exception TelephoneInvalideException est levee. 
    *   Un numero valide doit etre compose de 7 OU 10 caracteres numeriques.
    * @param numero le nouveau numero de telephone.
    * @throws com.tp_3.TelephoneInvalideException si le numero donne n'est pas valide.
    */
   public void setNumero(String numero) throws TelephoneInvalideException {
      if (!numeroTelValide(numero)) 
         throw new TelephoneInvalideException();
      
      this.numero = numero;
   }

   /**
    * Permet d'obtenir le poste de ce telephone.
    * @return le poste de ce telephone.
    */
   public String getPoste() {
      return poste;
   }

   /**
    * Modifie le poste de ce telephone par celui passe en parametre.
    * - Si le poste donne en parametre est vide, la valeur du poste de ce 
    *   telephone est initialise a null.
    * @param poste le nouveau poste de ce telephone.
    */
   public void setPoste(String poste) {
      if (poste != null && poste.isEmpty()) {
         poste = null;
      }
      this.poste = poste;
   }
   
   //------------------------------------
   //AUTRES METHODES D'INSTANCE PUBLIQUES
   //------------------------------------
   
   /**
    * Permet d'obtenir une representation sous forme de chaine de caracteres de 
    * ce telephone, sous le format suivant : .
    *    TEL. TYPE : formaterNumero(), poste xxxx  //lorsqu'il y a un poste
    *    TEL. TYPE : formaterNumero()              //lorsqu'il n'y a pas de poste
    * @return une representation de ce telephone sous forme d'une chaine de 
    *         caracteres.
    */
   public String toString() {
      String num;
      num = "TEL. " + type.toUpperCase() + " : " + formaterNumero();
      
      if (poste != null) {
         num = num + ", poste " + poste;
      }
      return num;
   }
   
   /**
    * Retourne le numero de ce telephone formate sous le format suivant.  
    * - Si numero est de longueur 10, par exemple 5142987656, la methode
    *   retournera la chaine (514) 298-7656
    * - Si le numero est de longueur 7, par exemple 2987656, la methode 
    *   retournera la chaine 298-7656.
    * @return le numero de ce telephone sous le format decrit ci-dessus.
    */
   public String formaterNumero() {
      String num = numero;
      String indReg = "";
      String part1;
      String part2;
      
      if (num.length() == LNG_NUM_2) {
         indReg = "(" + num.substring(0, 3) + ") ";
         num =  num.substring(3);
      }
      part1 = num.substring(0, 3);
      part2 = num.substring(3);
      num = indReg + part1 + "-" + part2;
      return num;
   }
   
   //--------------------------
   //METHODE DE CLASSE PUBLIQUE
   //--------------------------
   
   /**
    * Permet de verifier si le num passe en parametre est valide.
    * Un numero valide doit etre compose de 7 OU 10 caracteres numeriques.
    * @param num le numero a valider.
    * @return true si num est valide, false sinon.
    */
   public static boolean numeroTelValide (String num) {
      int i = 0;
      char car;
      boolean valide = num != null && 
              (num.length() == LNG_NUM_1 || num.length() == LNG_NUM_2);
      
      while (valide && i < num.length()) {
         car = num.charAt(i);
         valide = car >= '0' && car <= '9'; 
         i++;
      }
      return valide;
   }

} //fin classe Telephone


//--------------------------
//CLASSE EXCEPTION
//--------------------------

/**
 * Exception levee lorsqu'on tente de creer ou de modifier un objet Telephone 
 * avec un numero invalide.
 * @author melanie lord
 * @version novembre 2016
 */
class TelephoneInvalideException extends Exception {

}