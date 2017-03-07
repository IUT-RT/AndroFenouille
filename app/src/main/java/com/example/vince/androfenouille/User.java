package com.example.vince.androfenouille;
import java.util.ArrayList;

/**
 * Créé par Vincent le 07/03/2017.
 */

public class User {
    private static ArrayList<User> userList = new ArrayList<User>();
    private static String vJeton;
    private int idEtu;
    private String nom;
    private String prenom;
    private String formation;
    private int annee;

    public User(int idEtu, String nom, String prenom, String formation, int annee){
        this.idEtu = idEtu;
        this.nom = nom;
        this.prenom = prenom;
        this.formation = formation;
        this.annee = annee;
        userList.add(this);
    }

    //Lecture

    public int getIdEtu(){
        return idEtu;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getFormation(){
        return formation;
    }
    public int getAnnee(){
        return annee;
    }
    public ArrayList<User> getUserList(){
        return userList;
    }

    public String getLinkToAdd(){
        return "http://infort.gautero.fr/add.php?jeton=" + vJeton + "&nom=" + nom + "&prenom=" + prenom + "&formation=" + formation + "&annee=" + Integer.toString(annee);
    }

    public String toString(){
        return Integer.toString(idEtu) + "|" + nom + "|" + prenom + "|" + formation + "|" + Integer.toString(annee);
    }

    //Ecriture

    public void setIdEtu(int idEtu){
        this.idEtu = idEtu;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public void setFormation(String formation){
        this.formation = formation;
    }
    public void setAnnee(int annee){
        this.annee = annee;
    }
    public void setUserList(ArrayList<User> userList){
        this.userList = userList;
    }
}
