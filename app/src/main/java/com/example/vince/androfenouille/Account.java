package com.example.vince.androfenouille;

/**
 * Created by Clément on 11/03/2017.
 */

public class Account {
    private String user;
    private String passwd;
    private String jeton;

    public Account(String login, String pass, String vJeton){
        this.user=login;
        this.passwd=pass;
        this.jeton=vJeton;
    }

    //Lecture
    public String getUser(){
        return user;
    }
    public String getPasswd(){
        return passwd;
    }
    public String getJeton(){
        return jeton;
    }

}
