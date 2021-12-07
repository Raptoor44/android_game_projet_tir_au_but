package com.example.android_game_projet_tir_au_but;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_game_projet_tir_au_but.Tools.ScoreComparator;
import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.model.AdapterScores;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

public class TableauScore extends AppCompatActivity {


    private final String file_name = "Activity";


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


        Button retour = findViewById(R.id.id_activity_tableau_score_bouton_retour);

        retour.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });


        Button supprimer = findViewById(R.id.id_activity_tableau_score_supprimer);

        supprimer.setOnClickListener(view -> dialogConfirmationDelete());
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}