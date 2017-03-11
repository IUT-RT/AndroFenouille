package com.example.vince.androfenouille;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private String string;
    private ArrayList<String> myArray;
    private ListView myList;
    private ArrayAdapter<String> myAdapter;
    private ArrayList<User> userList;
    Download fille = new Download();
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myList = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();
        myArray = new ArrayList<String>();
    }

    public void addList(View v){
        string = "[{\"idEtu\":1,\"nom\":\"ABDESSADKI\",\"prenom\":\"AMIN\",\"formation\":\"DUT1\",\"annee\":2016},{\"idEtu\":2,\"nom\":\"ABOUKORA\",\"prenom\":\"AHMED\",\"formation\":\"DUT1\",\"annee\":2016},{\"idEtu\":3,\"nom\":\"BAILEY\",\"prenom\":\"ALEC\",\"formation\":\"DUT1\",\"annee\":2016},{\"idEtu\":4,\"nom\":\"BARNINI\",\"prenom\":\"PAUL\",\"formation\":\"DUT1\",\"annee\":2016},{\"idEtu\":5,\"nom\":\"BIANCHI\",\"prenom\":\"MAXIME\",\"formation\":\"DUT1\",\"annee\":2016},{\"idEtu\":6,\"nom\":\"BONINO\",\"prenom\":\"MANON\",\"formation\":\"DUT1\",\"annee\":2016}]";
        string = "nedifhg 'gfg'w";
        userList = newList(string);
        for (int cpt = 0; cpt < userList.size(); cpt++) {
            myArray.add(userList.get(cpt).toString());
        }



        myAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray);
        myList.setAdapter(myAdapter);
    }

    public void onClick(View v) {
        new Thread(new Runnable() {

            public void run() {
               json = fille.DownloadJson("http://infort.gautero.fr/listEtu.php");
            }
        }).start();
        if ( json != null) {
            userList = newList(json);
            myArray.add("first test");
            for (int cpt = 0; cpt < userList.size(); cpt++) {
                myArray.add(userList.get(cpt).toString());
            }
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray);
            myList.setAdapter(myAdapter);
        }
    }

    /*Prend en parametre une liste String Json
     *initialise un tableau json
     *parcours le tableau
     *Ajoute à une liste un objet de type User
     *retourne la liste<User>
     */
    public ArrayList<User> newList(String jsonText){
        try {
            ArrayList<User> List = new ArrayList<User>();
            JSONArray jsonArray = new JSONArray(jsonText);
            for (int i=0 ; i<jsonArray.length() ; i++){
                JSONObject j = jsonArray.getJSONObject(i);
                List.add(new User(j.getInt("idEtu"), j.getString("nom"), j.getString("prenom"), j.getString("formation"), j.getInt("annee")));
            }
            return  List;
        } catch (JSONException e) {
            return null;
        }
    }
}
