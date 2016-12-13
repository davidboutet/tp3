package com.tp_3;

import java.io.*;
import java.util.Arrays;

/**
 * Created by DavidBoutet(BOUD31109107) on 16-12-01.
 * Created by Adrien Lombard(LOMA28049707) on 16-12-01.
 */
public class CarnetContacts {
   
   //-----------
   //CONSTANTES
   //-----------
   
   //DIVERS
   
   public static final File FIC_CONTACTS = new File("contacts.txt");
   public static final int LNG_INIT_TAB_CONTACTS = 2;
   public static final String SEP = ":";
   
   //BORNES DE VALIDATION
   
   public final static String MENU_MIN = "1";
   public final static String MENU_MAX = "5";
  
   //MESSAGES DIVERS
  
   public static final String PRESENTATION = "Ce programme permet de gerer un "
           + "carnet de contacts.";
   public final static String MSG_PAUSE = "\nAppuyez sur \"ENTREE\" pour "
           + "continuer...\n";
   
   public static final String MENU = "\n-----\nMENU\n-----\n"
           + "1. AJOUTER UN CONTACT\n2. SUPPRIMER UN CONTACT"
           + "\n3. VIDER LE CARNET DE CONTACTS\n4. AFFICHER LES CONTACTS"
           + "\n5. QUITTER\n\n"
           + "Entrez votre choix au menu : ";
   
   public final static String MSG_ERR_MENU = "\nErreur, entrez une valeur entre "
           + MENU_MIN + " et " + MENU_MAX + "... Recommencez.\n";
   
   public static final String SOUS_MENU_AFF_CONTACTS = 
           "1. AFFICHER TOUS LES CONTACTS"
           + "\n2. AFFICHER LES FAVORIS\n\n"
           + "Entrez votre choix : ";
   
   public final static String MSG_FIN_PROG = "\n\nFIN NORMALE DU PROGRAMME.";
   
   //-------------------------------------------------------
   //METHODES D'ENREGISTREMENT DES CONTACTS DANS UN FICHIER 
   //-------------------------------------------------------
   
   /**
    * Lit la premiere ligne du fichier ficContacts qui est un nombre entier, 
    * et le retourne. Ce nombre correspond au nombre de contacts enregistres
    * dans le fichier. Si ficContacts n'existe pas, retourne 0.
    * @param ficContacts le fichier dont on veut lire la premiere ligne.
    * @return l'entier lu sur la premiere ligne du fichier ficContacts s'il
    *         existe, 0 sinon.
    */
   public static int lireFichierNbrContacts (File ficContacts) {
      int nbr = 0;
      BufferedReader in;
      if (ficContacts.exists()) {
         try {
            in = new BufferedReader(new FileReader(ficContacts));
            nbr = Integer.parseInt(in.readLine().trim());
            in.close();
         } catch (IOException e) {
            //ne se produira pas
         }
      }
      return nbr;
   }
   
   /**
    * Lit les contacts enregistres dans le fichier ficContacts et retourne
    * un tableau contenant ces contacts. Si le fichier n'existe pas, ou s'il ne
    * contient aucun contact, retourne un tableau de longueur LNG_INIT_TAB_CONTACTS 
    * dont toutes les cases sont initialisees a null.
    * @param ficContacts le fichier dans lequel lire les contacts.
    * @return un tableau contenant les contacts lus.
    */
   public static Contact [] lireFichierContacts (File ficContacts, 
                                                  int nbrContacts) {
      BufferedReader in;
      Contact [] contacts = new Contact[LNG_INIT_TAB_CONTACTS];
      Contact contact;
      String nom;
      String prenom;
      String tmp;
      int i = 0;
      
      if (ficContacts.exists() && nbrContacts != 0) {
         contacts = new Contact[nbrContacts + 2];
         try {
            in = new BufferedReader(new FileReader(ficContacts));
            in.readLine(); //sauter le nombre de contacts
            
            while (in.ready()) {
               nom = in.readLine();
               prenom = in.readLine();
               contact = new Contact(nom, prenom);
               lireTelephonesDansFic(in, contact);
               lireAdresseDansFic(in, contact);
               
               tmp = in.readLine();
               if (!tmp.equals("null"))
                  contact.setCourriel(tmp);
               
               tmp = in.readLine();
               if (tmp.equals("true"))
                  contact.setFavori(true);
               
               contacts[i] = contact; 
               i++;
            }
            in.close();
            
         } catch (IOException | ContactInvalideException 
                 | TelephoneInvalideException | AdresseInvalideException e) {
            //ne devrait pas se produire
         } 
      }
      return contacts;
   }
   
   /**
    * Lit l'adresse d'un contact dans le fichier associe au parametre in, puis
    * assigne l'adresse lue au contact donne.
    * @param in le flux associe au fichier a lire.
    * @param contact le contact auquel assigner l'adresse lue.
    * @throws AdresseInvalideException si l'adresse lue est invalide
    * @throws java.io.IOException s'il se produit une erreur d'entree/sortie.
    */
   private static void lireAdresseDansFic(BufferedReader in, Contact contact) 
                                  throws AdresseInvalideException, IOException {
      String tmp;
      String [] tokens;
      Adresse adresse = null;
      tmp = in.readLine();
      
      if (!tmp.equals("null")) {
         adresse = new Adresse();
         tokens = tmp.split(SEP);
         adresse.setNoPorte(tokens[0]);
         adresse.setNomRue(tokens[1]);
         tmp = tokens[2];
         if (tokens[2].equals("null"))
            tmp = null;
         adresse.setApt(tmp);
         adresse.setVille(tokens[3]);
         adresse.setProvinceEtat(tokens[4]);
         adresse.setPays(tokens[5]);
         tmp = tokens[6];
         if (tmp.equals("null"))
            tmp = null;
         adresse.setCodePostal(tmp);
      }
      contact.setAdresse(adresse);
      
   }
   
   /**
    * Lit les telephones d'un contact dans le fichier associe au parametre in, 
    * puis assigne les telephones lus au contact donne.
    * @param in le flux associe au fichier a lire.
    * @param contact le contact auquel assigner les telephones lus.
    * @throws TelephoneInvalideException si un telephone lu est invalide.
    * @throws java.io.IOException s'il se produit une erreur d'entree/sortie.
    */
   private static void lireTelephonesDansFic (BufferedReader in, Contact contact)
            throws TelephoneInvalideException, IOException {
      Telephone tel;
      String type;
      String no;
      String poste;
      String nbr = in.readLine().trim();
      int nbrTel = Integer.parseInt(nbr);
      for (int i = 0 ; i < nbrTel ; i++) {
         type = in.readLine();
         no = in.readLine();
         poste = in.readLine();
         if (poste.equals("null"))
            poste = null;
         tel = new Telephone(type, no, poste);
         contact.ajouterTelephone(tel);
      }
   }
   
   //-------------------------------------------------------
   //METHODES DE SAUVEGARDE DES CONTACTS DANS UN FICHIER 
   //-------------------------------------------------------
   
   /**
    * Ecrit tous les contacts non null presents dans contacts, dans le fichier
    * ficContacts. La methode ecrit d'abord le nombre de contacts a ecrire 
    * (nbrContacts) sur la premiere ligne du fichier, et ecrit ensuite les 
    * contacts non null, l'un a la suite de l'autre. Si le fichier ficContacts 
    * existe, il sera ecrase.
    * @param contacts les contacts a ecrire (sauvegarder).
    * @param ficContacts le fichier dans lequel ecrire les contacts.
    * @param nbrContacts le nombre de contacts non null dans contacts.
    */
   public static void sauvegarderContacts (Contact[] contacts, File ficContacts,
           int nbrContacts) {
      PrintWriter out;
      try {
         //si le fichier existe, il sera ecrase
         out = new PrintWriter(new FileWriter(ficContacts));
         out.println(nbrContacts);
         
         for (Contact c : contacts) {
            if (c != null) {
               out.println(c.getNom());
               out.println(c.getPrenom());
               out.println(c.getNbrTelephones());
               sauvegarderTelephonesDansFic(c, out);
               sauvegarderAdresseDansFic(c, out);
               out.println(c.getCourriel());
               out.println(c.isFavori());
            }
         }
         out.close();
         
      } catch (IOException e) {
         //ne se produira pas.
      }
   }
   
   /**
    * Ecrit l'adresse du contact donne dans le fichier associe au parametre 
    * out. Si l'adresse du contact est null, la methode ecrit "null".
    * @param contact le contact possedant l'adresse qu'on veut ecrire dans le 
    *                fichier.
    * @param out le flux associe au fichier dans lequel on veut ecrire l'adresse
    *            du contact.
    */
   private static void sauvegarderAdresseDansFic(Contact contact, PrintWriter out) {
      
      if (contact.getAdresse() != null) {
         out.print(contact.getAdresse().getNoPorte() + SEP 
                  + contact.getAdresse().getNomRue() + SEP
                  + contact.getAdresse().getApt() + SEP
                  + contact.getAdresse().getVille() + SEP
                  + contact.getAdresse().getProvinceEtat() + SEP
                  + contact.getAdresse().getPays() + SEP
                  + contact.getAdresse().getCodePostal() + "\n"
          );
      } else {
         out.println("null");
      }
   }

   /**
    * Ecrit les telephones du contact donne dans le fichier associe au parametre 
    * out. Si le contact n'a aucun telephone, la methode n'ecrit rien dans 
    * le fichier.
    * @param contact le contact possedant les telephones qu'on veut ecrire dans 
    *                le fichier.
    * @param out le flux associe au fichier dans lequel on veut ecrire les
    *            telephones de contact.
    */
   private static void sauvegarderTelephonesDansFic(Contact contact, PrintWriter out) {
      int i = 1;
      while (contact.obtenirIemeTelephone(i) != null) {
         out.println(contact.obtenirIemeTelephone(i).getType());
         out.println(contact.obtenirIemeTelephone(i).getNumero());
         out.println(contact.obtenirIemeTelephone(i).getPoste());
         i++;
      }
   }

   //-------------------------------------------------------
   //AUTRES METHODES UTILITAIRES
   //-------------------------------------------------------
   
   /**
    * Affiche une breve presentation de ce logiciel.
    */
   public static void presenterLogiciel() {
      System.out.println(PRESENTATION);
   }
   
   /**
    * Affiche le msg donne, puis demande a l'utilisateur d'appuyer sur ENTREE
    * pour continuer.
    * @param msg le message a afficher.
    */
   public static void pause (String msg) {
      System.out.print(MSG_PAUSE);
      Clavier.lireFinLigne();
   }
   
   /**
    * Construit une ligne formee avec le symbole donne, de la longueur donnee.
    * Exemple : 
    *    ligne('*', 10)  retourne la chaine "**********".
    * 
    * @param symbole le symbole a utiliser pour construire la ligne.
    * @param longueur le nombre de symboles dans la ligne construite.
    * @return une ligne formee avec le symbole donne, de la longueur donnee.
    */
   public static String ligne(char symbole, int longueur) {
      String ligne = "";
      for (int i = 0 ; i < longueur ; i++) {
          ligne = ligne + symbole;
      }
      return ligne;
   }
   
   /**
    * Encadre le titre donne dans une boite formee du symbole donne.
    * 
    * Exemple : boiteTitre('#', "TITRE") retourne la chaine qui lorsqu'affichee
    *           donnera ceci : 
    * 
    *          #########
    *          # TITRE #
    *          #########
    * 
    * @param symbole le symbole avec lequel former la boite (le cadre).
    * @param titre le titre a encadrer.
    * @return la chaine representant le titre encadre dans une boite formee
    *         avec le symbole donne.
    */
   public static String boiteTitre(char symbole, String titre) {
      String titreBoite = "\n";
      int longueur;
      if (titre != null && titre.length() > 0) {
         longueur = titre.length() + 4;
         titreBoite = titreBoite + ligne(symbole, longueur)
                 + "\n" + symbole + " " + titre.toUpperCase() 
                 + " " + symbole + "\n" + ligne(symbole, longueur);
      }
      return titreBoite + "\n";
   }
   
   /**
    * Affiche le msgSol donne (question) puis saisit, et valide la reponse 
    * (oui ou non) de l'utilisateur. Une reponse valide est 'o' ou 'O' pour OUI 
    * et 'n' ou 'N' pour non.
    * @param msgSol le message de sollicitation (une question) demandant une 
    *               reponse oui ou non.
    * @param msgErr le message d'erreur affiche lors d'un entree invalide.
    * @return true si l'utilisateur a repondu oui, false sinon.
    */
   public static boolean questionOuiNon(String msgSol, String msgErr) {
      String rep; //reponse de l'utilisateur

      System.out.print(msgSol);
      rep = Clavier.lireString();

      while (!rep.equalsIgnoreCase("O") && !rep.equalsIgnoreCase("N")) {
         System.out.println(msgErr);
         System.out.print(msgSol);
         rep = Clavier.lireString();
      }
      return rep.equalsIgnoreCase("O");
   }
   
   /**
    * Affiche le msgSol donne pour solliciter une chaine de caracteres, et 
    * retourne la chaine entree par l'utilisateur.
    * @param msgSol le message de sollicitation de la chaine de caracteres.
    * @return la valeur entree par l'utilisateur.
    */
   public static String lireChaine (String msgSol) {
      String chaine;
      System.out.print(msgSol);
      chaine = Clavier.lireString();
      return chaine;
   }
   
   /**
    * Sollicite, lit, et valide une chaine de caracteres qui doit etre de 
    * longueur comprise entre lngMin et lngMax inclusivement.
    * @param msgSol le message de sollicitation de la chaine de caracteres.
    * @param msgErr le message d'erreur affiche lorsque la chaine entree est 
    *               invalide.
    * @param lngMin longueur minimum valide de la chaine entree.
    * @param lngMax longueur maximum valide de la chaine entree.
    * @return la chaine entree, de longueur valide.
    */
   public static String validerChaine (String msgSol, String msgErr, 
           int lngMin, int lngMax) {
      String chaine;
      do {
         System.out.print(msgSol);
         chaine = Clavier.lireString();
         if (chaine.length() < lngMin || chaine.length() > lngMax) {
            System.out.println(msgErr);
         }
      } while (chaine.length() < lngMin || chaine.length() > lngMax);
      
      return chaine;
   }
   

   //-----------------------------
   //M�THODES � COMPL�TER
   //-----------------------------

    /** Cette méthode permet d'ajouter un contact avec les informations saisi par l'utilisateur
     * @param contacts le tableau de contacts représentant le carnet de contacts
     * @return le tableau de contacts dans lequel on a ajouté le nouveau contact
     */
    public static Contact[] ajouterContact (Contact [] contacts) {
        System.out.println(boiteTitre('*', "AJOUT D'UN CONTACT"));
        String nom = validerChaine("Nom du contact : ", "Erreur, le nom doit contenir entre 1 et 25 caracteres... Recommencez", 1, 25);
        String prenom = validerChaine("Prénom du contact : ", "Erreur, le prénom doit contenir entre 1 et 25 caracteres... Recommencez", 1, 25);
        try{
            Contact c = new Contact(nom, prenom);
            Boolean reponseTelephone = true;
            do{
                reponseTelephone = questionOuiNon("Voulez-vous entrez un telephone (o/n) : ", "Erreur, repondez par (o)ui ou (n)on !");
                if(reponseTelephone){
                    System.out.print("Type de telephone (Tapez ENTER pour \"Domicile\") : ");
                    String typeTelephone = Clavier.lireString();

                    Boolean b;
                    System.out.print("Numero de telephone : ");
                    String numeroTelephone = Clavier.lireString();
                    b = Telephone.numeroTelValide(numeroTelephone);
                    while (!b){
                        System.out.println("Erreur, le numero doit contenir exactement 7 ou 10 chiffres... Recommencez.");
                        System.out.print("Numero de telephone :");
                        numeroTelephone = Clavier.lireString();
                        b = Telephone.numeroTelValide(numeroTelephone);
                    }

                    System.out.print("Poste telephonique (Tapez ENTER si aucun poste) : ");
                    String poste = Clavier.lireString();
                    Telephone t = new Telephone(typeTelephone, numeroTelephone, poste);
                    c.ajouterTelephone(t);
                }
            }while(reponseTelephone);


            Boolean reponseAdresse = questionOuiNon("Voulez-vous entrez une adresse (o/n) : ", "Erreur, repondez par (o)ui ou (n)on !");
            if(reponseAdresse){
                String noPorte = validerChaine("No de porte : ", "Erreur, le numero de porte doit contenir entre 1 et 8 caracteres... Recommencez.", 1, 8);
                String nomRue = validerChaine("Rue : ", "Erreur, le nom de la rue doit contenir entre 1 et 50 caracteres... Recommencez.", 1, 50);
                System.out.print("Numero d'appartement (Taper ENTER si aucun apt.) : ");
                String numeroAppartement = Clavier.lireString();
                String nomVille = validerChaine("Ville : ", "Erreur, le nom de la ville doit contenir entre 1 et 50 caracteres... Recommencez.", 1, 50);
                String province = validerChaine("Province/état : ", "Erreur, le nom de la province/etat doit contenir entre 1 et 50 caracteres... Recommencez.", 1, 50);
                String pays = validerChaine("Pays : ", "Erreur, le nom du pays doit contenir entre 1 et 50 caracteres... Recommencez.", 1, 50);
                System.out.print("Code postal (Taper ENTER si aucun code postal) : ");
                String codePostal = Clavier.lireString();
                Adresse a = new Adresse(noPorte, nomRue, numeroAppartement, nomVille, province, pays, codePostal);
                c.setAdresse(a);
            }
            Boolean reponseCourriel = questionOuiNon("Voulez-vous entrez un courriel (o/n) : ", "Erreur, repondez par (o)ui ou (n)on !");
            if(reponseCourriel){
                String courriel = validerChaine("Courriel : ", "Erreur, le courriel doit contenir entre 5 et 100 caracteres... Recommencez.", 5, 100);
                c.setCourriel(courriel);
            }
            Boolean reponseFavori = questionOuiNon("Voulez-vous ajouter ce contact à vos favoris (o/n) : ", "Erreur, repondez par (o)ui ou (n)on !");
            if(reponseFavori){
                c.setFavori(true);
            }

            Boolean hasBeenAdd = false;
            if(isArrayFull(contacts)){
                contacts = ajouterLongueurTab(contacts, 2);
            }
            if(c != null){
                for (int i = 0; i < contacts.length; i++) {
                    if(contacts[i]==null && !hasBeenAdd){
                        contacts[i] = c;
                        hasBeenAdd=true;
                    }
                }
            }

        }catch (ContactInvalideException e){
            System.out.println(e.getMessage());
        }catch (TelephoneInvalideException e){
            System.out.println(e.getMessage());
        }catch (AdresseInvalideException e){
            System.out.println(e.getMessage());
        }
        return contacts; //pour compilation seulement... a enlever.
    }

    /**
     * Cette méthode permet de doubler le tableau de téléphone si il est plein
     * @param tab le tableau de téléphone
     * @param aAjouter longueur a ajouter au tableau
     * @return le tableau + aJouter
     */
    private static Contact[] ajouterLongueurTab (Contact[] tab, int aAjouter) {
        return Arrays.copyOf(tab, tab.length + aAjouter);
    }

    private static Boolean isArrayFull(Contact[] contacts){
        Boolean b = true;
        for (int i=0; i<=contacts.length-1; i++) {
            if (contacts[i] == null) {
                b = false;
            }
        }
        return b;
    }

    /**
     * Cette méthode permet de supprimer un contact saisi par l'utilisateur
     * @param contacts le tableau de contacts représentant le carnet de contacts
     * @return le nombre de contact(s) effectivement supprimés
     */
    public static int supprimerContact(Contact[] contacts) {
        Contact [] contactTrouver = {null};
        int tabIndex = 0;
        System.out.println(boiteTitre('*', "SUPPRIMER UN CONTACT"));
        String nomContact = validerChaine("Nom du contact : ", "Erreur, le nom doit contenir entre 1 et 25 caracteres... Recommencez", 1, 25);
        String prenomContact = validerChaine("Prénom du contact : ", "Erreur, le prénom doit contenir entre 1 et 25 caracteres... Recommencez", 1, 25);

        for(int i = 0; i<contacts.length; i++){
            if(contacts[i]!=null){
                if(contacts[i].getNom().equals(nomContact)&&contacts[i].getPrenom().equals(prenomContact)){
                    if(contactTrouver[tabIndex]==null){
                        contactTrouver[tabIndex] = contacts[i];
                        contactTrouver = ajouterLongueurTab(contactTrouver, 2);
                    }
                    tabIndex++;
                }
            }
        }
        if(tabIndex==0){
            System.out.print("\nLe carnet ne contient aucun contact portant le nom : " + prenomContact.toUpperCase() + " " + nomContact.toUpperCase() + "\n");
            pause(MSG_PAUSE);
        }else{
            for(int i = 0; i<contactTrouver.length; i++){
                if(contactTrouver[i]!=null){
                    System.out.println(contactTrouver[i]);
                    if(contactTrouver[i].isFavori()){
                        Contact.nbrContactsFavoris = Contact.nbrContactsFavoris--;
                    }
                    if(questionOuiNon("Voulez-vous vraiment supprimer ce contact (o/n) : ", "Erreur, repondez par (o)ui ou (n)on !")){
                        for(int z = 0; z<contacts.length; z++){
                            if(contacts[z]!=null){
                                if(contacts[z].getNom().equals(contactTrouver[i].getNom()) && contacts[z].getPrenom().equals(contactTrouver[i].getPrenom())){
                                    contacts[z] = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return tabIndex;
   }


    /**
     * Cette méthode permet de  vider le carnet de contacts
     * @param contacts le tableau de contacts représentant le carnet de contacts
     * @return true si l’utilisateur a confirmé la suppression de tous les contacts, false sinon
     */
    public static boolean viderCarnet(Contact[] contacts) {
        Boolean hasBeenClear = false;
        if(questionOuiNon("Voulez-vous vraiment effacer tous les contacts (o/n) :", "Erreur, repondez par (o)ui ou (n)on !" )){
            Contact.nbrContactsFavoris = 0;
            hasBeenClear = true;
        }
        return hasBeenClear;
   }

    /**
     * Cette méthode permet de choisir entre afficher tout les contacts ou afficher seulement les contacts favoris
     * @param contacts le tableau de contacts représentant le carnet de contacts
     * @param nbrContacts le nombre total de contacts présents dans le carnet
     */
    public static void afficherContacts (Contact [] contacts, int nbrContacts) {
        String choix;
        String nom;
        String prenom;
        int tailleNomPre = 0;
        boolean tabVide = true;
        System.out.println(boiteTitre('*', "AFFICHER LES CONTACTS"));

        do{
            System.out.print(SOUS_MENU_AFF_CONTACTS);
            choix = Clavier.lireString();

            switch (choix) {
                case "1" :
                    System.out.println("\nCARNET DE CONTACTS (" + nbrContacts + ")\n\n");

                    if(nbrContacts == 0) {
                        System.out.println("FIN DE LA LISTE DE CONTACTS.");
                        pause(MSG_PAUSE);
                        tabVide = false;
                    } else {
                        for (int i = 0; i < contacts.length; i++) {
                            if (contacts[i] != null) {
                                if (contacts[i].isFavori()) {
                                    tailleNomPre += 9;
                                }
                                tailleNomPre += contacts[i].getNom().length() + contacts[i].getPrenom().length() + 2;
                                nom = contacts[i].toString().substring(0, contacts[i].toString().indexOf(','));
                                prenom = contacts[i].toString().substring(contacts[i].toString().indexOf(',') + 1, contacts[i].toString().indexOf("\n"));
                                System.out.println(ligne('-', tailleNomPre));
                                System.out.println(nom + "," + prenom);
                                System.out.print(ligne('-', tailleNomPre));
                                System.out.println(contacts[i].toString().substring(contacts[i].toString().indexOf("\n")));
                                tailleNomPre = 0;
                                if(i < nbrContacts-1){
                                    pause(MSG_PAUSE);
                                }
                            }
                        }
                        tabVide = false;
                        System.out.println("FIN DE LA LISTE DE CONTACTS.");
                        pause(MSG_PAUSE);
                    }
                break;
                
                case "2" :
                    System.out.println("\nCARNET DE CONTACTS (" + Contact.nbrContactsFavoris + ")\n\n");
                    if(Contact.nbrContactsFavoris == 0) {
                        System.out.println("FIN DE LA LISTE DE CONTACTS.");
                        pause(MSG_PAUSE);
                        tabVide = false;
                    } else {
                        for(int i = 0; i < contacts.length; i++){
                            if(contacts[i] != null && contacts[i].isFavori()){
                                tailleNomPre += contacts[i].getNom().length() + contacts[i].getPrenom().length() + 11;
                                nom = contacts[i].toString().substring(0,contacts[i].toString().indexOf(','));
                                prenom = contacts[i].toString().substring(contacts[i].toString().indexOf(',')+1,contacts[i].toString().indexOf("\n"));
                                System.out.println(ligne('-',tailleNomPre));
                                System.out.println(nom + "," + prenom);
                                System.out.println(ligne('-',tailleNomPre));
                                System.out.println(contacts[i].toString().substring(contacts[i].toString().indexOf("\n")));
                                tailleNomPre = 0;
                                if(i < Contact.nbrContactsFavoris-1){
                                    pause(MSG_PAUSE);
                                }
                            }
                        }
                        tabVide = false;
                        System.out.println("FIN DE LA LISTE DE CONTACTS.");
                        pause(MSG_PAUSE);
                    }
                    break;

                default : 
                    System.out.println("\nErreur, entrez une valeur entre 1 et"
                            + "2... Recommencez.\n");
            }

        }while(tabVide);
    }
   /**
    * Point d'entree de l'application de gestion d'un carnet de contacts 
    * personnels.
    * @param args (aucun)
    */
   public static void main (String [] args) {
      //nombre de contacts dans le tableau de contacts
      int nbrContacts = lireFichierNbrContacts(FIC_CONTACTS);
      
      //tableau de contacts
      Contact [] contacts = lireFichierContacts(FIC_CONTACTS, nbrContacts);


      //Choix au menu principal
      String choix;

      presenterLogiciel();
      
      do {
         System.out.print(MENU);
         choix = Clavier.lireString();
         
         switch (choix) {
            case "1" : 
               contacts = ajouterContact(contacts);
               nbrContacts++;
               break;
               
            case "2" :
               nbrContacts = nbrContacts - supprimerContact(contacts);
               break;
               
            case "3" : 
               if (viderCarnet(contacts)) {
                  nbrContacts = 0;
                  contacts = new Contact[LNG_INIT_TAB_CONTACTS];
               }
               break;
               
            case "4" :
               afficherContacts(contacts, nbrContacts);
               break;ava
               
            case MENU_MAX :
               //Sauvegarde des contacts dans le fichier FIC_CONTACTS
               sauvegarderContacts (contacts, FIC_CONTACTS, nbrContacts);
               break;
            default : 
               System.out.println(MSG_ERR_MENU);
         }
         
      } while (!choix.equals(MENU_MAX));
      
      //afficher la fin du programme
      System.out.println(MSG_FIN_PROG);
   }
   
} //fin classe CarnetContacts
