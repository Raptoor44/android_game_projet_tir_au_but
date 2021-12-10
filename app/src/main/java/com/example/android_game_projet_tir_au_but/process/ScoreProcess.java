package com.example.android_game_projet_tir_au_but.process;

import android.annotation.SuppressLint;

import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.Gardien;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

public class ScoreProcess {

    private Game game;

    public ScoreProcess(Game game) {
        this.game = game;
    }

    public void enregistrerScore() {


        ListScores.getScores().add(this.game.getScore());
        String file_name = "Activity";
        Serializer.serialize(file_name, ListScores.getScores(), this.game.getApplicationContext());



        this.game.setScore(new Score());
        actualiserScore();


        for (int i = 0; i < this.game.getGardiens().getGardiens().size(); i++) {
            this.game.getFenetrePrincipale().removeView(this.game.getGardiens().getGardiens().get(i).getView());


        }
        Gardien gardien_1 = new Gardien(this.game, this.game.getGardiens().getGardiens().get(0).getX());
        this.game.getGardiens().getGardiens().clear();

        this.game.getGardiens().add(gardien_1);

        Gardien.resetStatic();


    }

    @SuppressLint("SetTextI18n")
    public void actualiserScore() {
        this.game.getScore_courant().setText("Votre score actuel : " + this.game.getScore().getScore());
    }
}
