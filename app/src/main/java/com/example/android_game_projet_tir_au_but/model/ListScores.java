package com.example.android_game_projet_tir_au_but.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListScores implements Serializable {


    private static List<Score> scores = new ArrayList<Score>();

    public static List<Score> getScores() {
        return scores;
    }

    public static void setScores(List<Score> scores_parametres) {
        scores = scores_parametres;
    }



}
