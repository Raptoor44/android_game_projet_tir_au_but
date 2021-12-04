package com.example.android_game_projet_tir_au_but;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android_game_projet_tir_au_but.Tools.ScoreComparator;
import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.model.AdapterScores;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TableauScore extends AppCompatActivity  {


    private ListView listScores;
    private AdapterScores adapterScores;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_score);




        ListScores.getScores().sort(new ScoreComparator());


        listScores = findViewById(R.id.id_activity_tableau_score_list_view);

        adapterScores = new AdapterScores(this.getApplicationContext(), ListScores.getScores());

        listScores.setAdapter(adapterScores);
    }
}