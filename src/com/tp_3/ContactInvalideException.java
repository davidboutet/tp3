package com.tp_3;

/**
 * Exception levee lorsque le nom ou le prenom d'un contact est null
 * ou vide.
 * @author melanie lord
 * @version novembre 2016
 */
public class ContactInvalideException extends Exception {
   
   /**
    * Constructeur sans argument.
    */
   public ContactInvalideException () {
   
   }
   
   /**
    * Constructeur qui assigne un message a l'objet construit.
    * @param msg un message renseignant sur la cause et le contexte de 
    *            l'exception.
    */
   public ContactInvalideException (String msg) {
      super(msg);
   }
   
   
}
