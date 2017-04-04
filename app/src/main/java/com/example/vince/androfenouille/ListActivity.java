package com.example.vince.androfenouille;

import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<String> myArray;
    private ListView myList;
    private ArrayAdapter<String> myAdapter;
    Download fille = new Download();
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myList = (ListView) findViewById(R.id.listUserView);
        myArray = new ArrayList<String>();
        try {
            //Cree la liste et l'affiche
            rechercheList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    /**************
     *  onResume
     ***************/

   @Override
    protected void onResume(){
        super.onResume();
        for(int i=0 ; i<User.userList.size()-1 ; i++){
            User.userList.remove(i);
        }
        User.userList.clear();
        myArray.clear();
        myAdapter.notifyDataSetChanged();
        try {
            rechercheList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**************
    *    ajoutUser
     ***************/

    public void ajoutUser(View v){
        Intent intent = new Intent(this, ajoutActivity.class);
        startActivity(intent);
    }






    /**************
     *  onActualise
     ***************/

    public void onActualise(View v){
        for(int i=0 ; i<User.userList.size()-1 ; i++){
            User.userList.remove(i);
        }
        User.userList.clear();
        myArray.clear();
        myAdapter.notifyDataSetChanged();
        try {
            rechercheList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**************
     *  rechercheList
     ***************/

    public void rechercheList() throws InterruptedException {
        //Affichage d'un msg
        Toast.makeText(getBaseContext(),"List Actualise.",Toast.LENGTH_LONG).show();

        //Crée un Thread qui permet de téléchager la liste JSon
        Thread t =new Thread(new Runnable() {
            public void run() {
               json = fille.DownloadJson("http://infort.gautero.fr/listEtu.php");
            }
        });
        //Démarre le thread
        t.start();
        //On attend que le thread termine ça tache
        t.join();

        //Test si le téléchargement contient quelque chose
        if ( json != null) {
            //Appelle de la methode Qui va ajouter à l'ArrayList<User> des objet de type User
            User.addUser(json);
            for (int cpt = 0; cpt < User.userList.size(); cpt++) {
                myArray.add(User.userList.get(cpt).toString());
            }

            //Crée la Liste
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray);
            myList.setAdapter(myAdapter);
            final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            //Ajoute des méthode quand on click sur un item de la liste
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                String[] user;

                //Quand on click sur un item(utilisateur) on le suprime de la liste et de la liste json sur internet
                @Override
                public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {

                    //Récupère l'objet à la position (Variable position)
                    Object o = myList.getItemAtPosition(position);
                    //Récupère son contenu
                    String var = o.toString();
                    //On le split car la forme (id-nom-prenom-formation-annee)
                    user = var.split("-");

                    /**************
                     *  Boite de dialogue
                     *
                     *  Ouvre un boite de dialogue avec trois choix supprimer en local, en ligne, ou pas de suppréssion
                     ***************/

                    dlgAlert.setMessage(var);
                    dlgAlert.setTitle("Voulez-vous supprimé");
                    dlgAlert.setPositiveButton("En ligne", null);
                    dlgAlert.setNeutralButton("Nop", null);
                    dlgAlert.setNegativeButton("En local", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.setPositiveButton("En ligne", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Crée un Thread qui permet de supprimer l'utilisateur
                            Thread t =new Thread(new Runnable() {
                                public void run() {
                                    json = fille.DownloadJson("http://infort.gautero.fr/supp.php?jeton=71a4a17a658b90a7f847585721b5a217&id="+user[0]);
                                }
                            });
                            t.start();
                            try {
                                t.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //Supprime item à la position donné
                            myArray.remove(position);
                            //On dit à l'adapteur que l'on a modifier
                            myAdapter.notifyDataSetChanged();
                        }
                    });
                    dlgAlert.setNegativeButton("En local", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Supprime item à la position donné
                            myArray.remove(position);
                            //On dit à l'adapteur que l'on a modifier
                            myAdapter.notifyDataSetChanged();
                        }
                    });
                    dlgAlert.setNeutralButton("Nop", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    dlgAlert.create().show();
                }
            });

        }
    }





}
