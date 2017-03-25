package com.example.vince.androfenouille;

import android.content.Intent;
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
        setContentView(R.layout.activity_main2);

        myList = (ListView) findViewById(R.id.listUserView);
        myArray = new ArrayList<String>();
        try {
            rechercheList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void rechercheList() throws InterruptedException {
        Toast.makeText(getBaseContext(),"List Actualise.",Toast.LENGTH_LONG).show();


        Thread t =new Thread(new Runnable() {
            public void run() {
               json = fille.DownloadJson("http://infort.gautero.fr/listEtu.php");
            }
        });
        t.start();
        t.join();

        //Test si la variable est null
        if ( json != null) {
            User.addUser(json);
            for (int cpt = 0; cpt < User.userList.size(); cpt++) {
                myArray.add(User.userList.get(cpt).toString());
            }
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray);
            myList.setAdapter(myAdapter);



            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                String[] user;

                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                   Object o = myList.getItemAtPosition(position);
                    String var = o.toString();
                    user = var.split("-");


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
                    /*Intent intent = new Intent(getBaseContext(), modifActivity.class);
                    intent.putExtra("param", var );
                    startActivity(intent);*/
                }
            });

        }
    }

    public void ajoutUser(View v){
        Intent intent = new Intent(this, ajoutActivity.class);
        startActivity(intent);
    }

}
