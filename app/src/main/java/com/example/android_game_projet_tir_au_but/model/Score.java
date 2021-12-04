package com.example.android_game_projet_tir_au_but.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Score implements Serializable {

    private int score;
    private Date date;
    private static int meilleur_score;

    public Score() {
        this.date = Calendar.getInstance().getTime();
    }

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

    public Date getDate() {
        return date;
    }
}
