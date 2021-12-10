package com.example.android_game_projet_tir_au_but.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ListScores implements Serializable {


    private static List<Score> scores;

    public static List<Score> getScores() {
        return scores;
    }

    public static void setScores(List<Score> scores_parametres) {
        scores = scores_parametres;
    }


    public static void garderCinqPremiers() {

        List<Score> permute = new LinkedList<Score>();

        for (int i = 0; i < 5; i++) {
            permute.add(scores.get(i));
        }
        scores.clear();

        for (Score score : permute) {
            scores.add(score);
        }


    }
}
