package com.example.android_game_projet_tir_au_but;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android_game_projet_tir_au_but.Tools.ScoreComparator;
import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.model.AdapterScores;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TableauScore extends AppCompatActivity {


    private ListView listScores;
    private AdapterScores adapterScores;

    private Button retour;
    private Button supprimer;

    private String file_name = "Activity";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_score);


        ListScores.getScores().sort(new ScoreComparator());


        listScores = findViewById(R.id.id_activity_tableau_score_list_view);

        adapterScores = new AdapterScores(this.getApplicationContext(), ListScores.getScores(), this);

        listScores.setAdapter(adapterScores);


        this.retour = findViewById(R.id.id_activity_tableau_score_bouton_retour);

        this.retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        this.supprimer = findViewById(R.id.id_activity_tableau_score_supprimer);

        this.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirmationDelete();
            }
        });
    }

    private void dialogConfirmationDelete() {
        // Dialog box


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Voulez vous vraiment supprimer tout vos scores ?");
        alertDialogBuilder.setPositiveButton("Oui",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        ListScores.getScores().clear();
                        Serializer.serialize(file_name, ListScores.getScores(), getApplicationContext());

                        Intent intent = new Intent(getApplicationContext(), TableauScore.class);
                        startActivity(intent);
                        finish();

                    }
                });

        alertDialogBuilder.setNegativeButton("Non",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}