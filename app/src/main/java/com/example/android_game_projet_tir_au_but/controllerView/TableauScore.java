package com.example.android_game_projet_tir_au_but.controllerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_game_projet_tir_au_but.R;
import com.example.android_game_projet_tir_au_but.Tools.ScoreComparator;
import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.model.AdapterScores;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

public class TableauScore extends AppCompatActivity {


    private final String file_name = "Activity";

    private Button supprimer;
    private Button retour;
    private Button garderCinqPremiers;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_score);


        ListScores.getScores().sort(new ScoreComparator());


        ListView listScores = findViewById(R.id.id_activity_tableau_score_list_view);

        AdapterScores adapterScores = new AdapterScores(this.getApplicationContext(), ListScores.getScores(), this);

        listScores.setAdapter(adapterScores);


        Score.acctualiserMeilleurScore();
        TextView meilleurScore = findViewById(R.id.id_activity_tableau_score_meilleur_score);
        meilleurScore.setText(String.valueOf(Score.getMeilleurScore()));


        retour = findViewById(R.id.id_activity_tableau_score_bouton_retour);

        retour.setOnClickListener(view -> {

            retourActivity();
        });


        supprimer = findViewById(R.id.id_activity_tableau_score_supprimer);

        supprimer.setOnClickListener(view -> dialogConfirmationDelete());

        this.garderCinqPremiers = findViewById(R.id.id_activity_tableau_score_garder5premiers);

        this.garderCinqPremiers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListScores.garderCinqPremiers();
                actualiserActivity();

            }
        });

        //Serialize lors d'une modification de la liste de scores
        String file_name = "Activity";
        Serializer.serialize(file_name, ListScores.getScores(), getApplicationContext());
    }


    private void dialogConfirmationDelete() {
        // Dialog box


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Voulez vous vraiment supprimer tout vos scores ?");
        alertDialogBuilder.setPositiveButton("Oui",
                (arg0, arg1) -> {
                    ListScores.getScores().clear();
                    Serializer.serialize(file_name, ListScores.getScores(), getApplicationContext());

                    Intent intent = new Intent(getApplicationContext(), TableauScore.class);
                    startActivity(intent);
                    finish();

                });

        alertDialogBuilder.setNegativeButton("Non",
                (arg0, arg1) -> {

                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    @Override
    public void onBackPressed() {
        retourActivity();

    }

    private void actualiserActivity() {
        Intent intent = new Intent(getApplicationContext(), TableauScore.class);
        startActivity(intent);
        finish();
    }

    private void retourActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}