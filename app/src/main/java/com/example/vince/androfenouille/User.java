package com.example.vince.androfenouille;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Créé par Vincent le 07/03/2017.
 */

public class User {
    private static final int INT=0;
    private static final String STR="x";

    static ArrayList<User> userList = new ArrayList<User>();

    private static String vJeton;
    private int id;
    private String name;
    private String firstName;
    private String formation;
    private int year;

    public User(){
        this.id=INT;
        this.name=STR;
        this.firstName=STR;
        this.formation=STR;
        this.year=INT;
    }

    public static void addUser(String text){
        try {
            JSONArray jsonArray = new JSONArray(text);
            for (int i=0 ; i<jsonArray.length() ; i++){
                JSONObject j = jsonArray.getJSONObject(i);
                userList.add(new User(j.getInt("idEtu"), j.getString("nom"), j.getString("prenom"), j.getString("formation"), j.getInt("annee")));
            }
        } catch (JSONException e) {}
    }

    public User(int id, String name, String firstName, String formation, int year){
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.formation = formation;
        this.year = year;
    }

    //Lecture

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getFormation(){
        return formation;
    }
    public int getYear(){
        return year;
    }
    public ArrayList<User> getUserList(){
        return userList;
    }

    public String getLinkToAdd(){
        return "http://infort.gautero.fr/add.php?jeton=" + vJeton + "&nom=" + name + "&prenom=" + firstName + "&formation=" + formation + "&annee=" + Integer.toString(year);
    }

    public String toString(){
        return Integer.toString(id) + "-" + name + "-" + firstName + "-" + formation + "-" + Integer.toString(year);
    }

    public ArrayList<User> getUsersById(String id){
        ArrayList<User> resultList = new ArrayList<User>();
        for(int i = 0; i < userList.size(); i +=1){
            if(Integer.toString(userList.get(i).getId()) == id){
                resultList.add(userList.get(i));
            }
        }
        return resultList;
    }
    public ArrayList<User> getUsersByName(String name){
        ArrayList<User> resultList = new ArrayList<User>();
        for(int i = 0; i < userList.size(); i +=1){
            if(userList.get(i).getName() == name){
                resultList.add(userList.get(i));
            }
        }
        return resultList;
    }
    public ArrayList<User> getUsersByFirstName(String firstName){
        ArrayList<User> resultList = new ArrayList<User>();
        for(int i = 0; i < userList.size(); i +=1){
            if(userList.get(i).getFirstName() == firstName){
                resultList.add(userList.get(i));
            }
        }
        return resultList;
    }
    public ArrayList<User> getUsersByFormation(String formation){
        ArrayList<User> resultList = new ArrayList<User>();
        for(int i = 0; i < userList.size(); i +=1){
            if(userList.get(i).getFormation() == formation){
                resultList.add(userList.get(i));
            }
        }
        return resultList;
    }
    public ArrayList<User> getUsersByYear(String year){
        ArrayList<User> resultList = new ArrayList<User>();
        for(int i = 0; i < userList.size(); i +=1){
            if(Integer.toString(userList.get(i).getYear()) == year){
                resultList.add(userList.get(i));
            }
        }
        return resultList;
    }

    //Ecriture

    public void setId(int id){
        this.id = id;
    }
    public void setNom(String name){
        this.name = name;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setFormation(String formation){
        this.formation = formation;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setUserList(ArrayList<User> userList){
        this.userList = userList;
    }


}
