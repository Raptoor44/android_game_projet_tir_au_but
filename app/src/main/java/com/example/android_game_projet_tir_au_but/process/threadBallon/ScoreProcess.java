package com.example.android_game_projet_tir_au_but.process.threadBallon;

import android.annotation.SuppressLint;

import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

public class ScoreProcess {

    private final AnnexeGardiensProcess annexeGardiensProcess = new AnnexeGardiensProcess(this);
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


        annexeGardiensProcess.Annexe();

    }


    @SuppressLint("SetTextI18n")
    public void actualiserScore() {
        this.game.getScoreCourant().setText("Votre score actuel : " + this.game.getScore().getScore());
    }

    public Game getGame() {
        return game;
    }
}
