package com.example.vince.androfenouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ajoutActivity extends AppCompatActivity {
    Download fille = new Download();
    private EditText id, nom, prenom, formation, annee;
    String[] perso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        formation = (EditText) findViewById(R.id.formation);
        annee = (EditText) findViewById(R.id.annee);
    }

    public void ajoutUser(View v) throws InterruptedException {
        //Test si aucun champ n'est vide
        if( nom.length() != 0 && prenom.length() != 0 && formation.length() != 0 && annee.length() != 0 ) {
            //Affichage d'un msg
            Toast.makeText(getBaseContext(),"Ajout de l'utilisateur",Toast.LENGTH_LONG).show();
            //Nouveau Thread
            Thread t = new Thread(new Runnable() {
                public void run() {
                    fille.DownloadJson("http://infort.gautero.fr/add.php?jeton=71a4a17a658b90a7f847585721b5a217&nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&formation=" + formation.getText().toString() + "&annee=" + annee.getText().toString());
                }
            });
            //On start le thread
            t.start();
            //On attend que l'execution dans le nouveau soit fini
            t.join();

            nom.setText("");
            prenom.setText("");
            formation.setText("");
            annee.setText("");
            /*
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);*/


        }else {
            Toast.makeText(getBaseContext(),"Erreur veulliez remplir toute les informations",Toast.LENGTH_LONG).show();
        }
        //http://stackoverflow.com/questions/2558591/remove-listview-items-in-android
    }
}
