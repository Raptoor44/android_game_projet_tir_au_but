package com.example.android_game_projet_tir_au_but.controllerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.android_game_projet_tir_au_but.R;
import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

import java.util.ArrayList;

public class ManuelUtilisation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuel_utilisation);

        Button retour = findViewById(R.id.id_activity_manuel_utilisation_retour);

        //File Serializer
        String file_name = "Activity";
        ListScores.setScores((ArrayList<Score>) Serializer.deSerialize(file_name, getApplicationContext()));

        retour.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}