package com.example.android_game_projet_tir_au_but.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Score implements Serializable {

    private int score;
    private Date date;
    private static int meilleurScore;

    public Score() {
        this.date = Calendar.getInstance().getTime();
    }

    public int getScore() {
        return score;
    }

    public void setScore() {

        if (score > meilleurScore) {
            meilleurScore = score;
        }

        score = score + 1;
    }

    public static int getMeilleurScore() {
        return meilleurScore;
    }

    public Date getDate() {
        return date;
    }
}
