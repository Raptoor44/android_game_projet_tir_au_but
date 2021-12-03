package com.example.android_game_projet_tir_au_but.model;

public class Score {

    private static int score;
    private static int meilleur_score;


    public int getScore() {
        return score;
    }

    public void setScore() {

        if (score > meilleur_score) {
            meilleur_score = score;
        }

        score = score + 1;
    }

    public static int getMeilleur_score() {
        return meilleur_score;
    }


}
