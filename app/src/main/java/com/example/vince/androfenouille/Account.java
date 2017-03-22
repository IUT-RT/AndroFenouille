package com.example.vince.androfenouille;

/**
 * Created by Clément on 11/03/2017.
 */

public class Account {
    static String user;
    static String passwd;
    static String jeton;

    public Account(String login, String pass, String vJeton){
        user=login;
        passwd=pass;
        jeton=vJeton;
    }
    //Lecture
    public String getUser(){
        return user;
    }
    public String getPasswd(){
        return passwd;
    }
    public String getJeton(){ return jeton; }

}
