package com.example.vince.androfenouille;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class modifActivity extends AppCompatActivity {
    private TextView idText, nomText, prenomtext, formation, annee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        idText = (TextView) findViewById(R.id.idText);
        prenomtext = (TextView) findViewById(R.id.preText);
        nomText = (TextView) findViewById(R.id.nomText);
        formation = (TextView) findViewById(R.id.forText);
        annee = (TextView) findViewById(R.id.anText);

        Intent intent = getIntent();
        String value = intent.getStringExtra("param");
        String[] parts = value.split("-");

        idText.setText(parts[1]);
        nomText.setText(parts[2]);
        prenomtext.setText(parts[3]);
        formation.setText(parts[4]);
        annee.setText(parts[5]);
    }
}
