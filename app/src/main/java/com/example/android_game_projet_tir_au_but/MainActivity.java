package com.example.android_game_projet_tir_au_but;

import static com.example.android_game_projet_tir_au_but.Tools.Serializer.deSerialize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private Button jouer;
    private Button voir_scores;
    private Button manuelUtilisation;
    //File Serializer
    private String file_name = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.jouer = findViewById(R.id.id_button_jouer);
        this.jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game.class);
                startActivity(intent);
                finish();
            }
        });

        this.voir_scores = findViewById(R.id.id_button_voir_scores);

        this.voir_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TableauScore.class);
                startActivity(intent);
                finish();
            }
        });

        this.manuelUtilisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManuelUtilisation.class);
                startActivity(intent);
                finish();
            }
        });



        ListScores.setScores((ArrayList<Score>) Serializer.deSerialize(file_name, getApplicationContext()));

        if(ListScores.getScores()==null){
         List<Score> listPermute = new ArrayList<Score>();
            ListScores.setScores(listPermute);
        }

    }
}