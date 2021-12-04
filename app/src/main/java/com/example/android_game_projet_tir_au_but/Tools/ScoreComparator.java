package com.example.android_game_projet_tir_au_but.Tools;

import com.example.android_game_projet_tir_au_but.model.Score;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {


    public int compare(Score s1, Score s2){


        int a = s1.getScore();
        int b = s2.getScore();

        if(a > b){
            return -1;
        }else{
            return 1;
        }
    }
}
