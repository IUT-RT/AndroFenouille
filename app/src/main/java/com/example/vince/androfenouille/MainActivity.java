package com.example.vince.androfenouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText log;
    private EditText pass;
    Download fille = new Download();
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = (EditText) findViewById(R.id.logEdit);
        pass = (EditText) findViewById(R.id.passEdit);
    }

    //Appelé par le button
    public void newActivity(View v) throws InterruptedException {
        //Test si EditText non null
        if (log.getText().toString().equals("admin") && pass.getText().toString().equals("secret")) {

            //Démarre un nouveau thread
          Thread t = new Thread(new Runnable() {
                public void run() {
                   json = fille.DownloadJson("http://infort.gautero.fr/connect.php?login=" + log.getText().toString() + "&mdp=" + pass.getText().toString());
                }
            });
            //Execute le run()
            t.start();
            //On attend la fin du thread
            t.join();

            //On supprime ce qui est ecrit dans les EditTexts
            log.setText("");
            pass.setText("");
            //Démarrage de la nouvelle activité
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        }
    }
}
