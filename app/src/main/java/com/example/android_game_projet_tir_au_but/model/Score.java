package com.example.android_game_projet_tir_au_but.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Score implements Serializable {

    private int score;
    private final Date date;
    private static int meilleurScore = 0;

    public Score() {
        super();
        this.date = Calendar.getInstance().getTime();
    }

    public int getScore() {
        return score;
    }

    public void setScore() {


        score = score + 1;
    }

    public static int getMeilleurScore() {
        return meilleurScore;
    }

    public Date getDate() {
        return date;
    }

    public static void acctualiserMeilleurScore(){
        meilleurScore = 0;
        for(Score s : ListScores.getScores()){
            if(s.getScore() > Score.getMeilleurScore()){
                meilleurScore = s.getScore();
            }
        }
    }
}
