package com.example.android_game_projet_tir_au_but.process.threadBallon;

import android.media.MediaPlayer;

import com.example.android_game_projet_tir_au_but.R;
import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.Gardien;

public class SonProcess {


    private Game game;

    public SonProcess(Game game) {
        this.game = game;
    }

    public void jouerSonGoal() {

        if (Gardien.getNbInstance() == 5 ||
                Gardien.getNbInstance() == 10
                || Gardien.getNbInstance() == 15
                || Gardien.getNbInstance() == 20
                || Gardien.getNbInstance() == 25) {//Ce grand if permet que le son ne soit pas là à tous les buts.
            MediaPlayer mediaPlayer = MediaPlayer.create(game.getApplicationContext(), R.raw.goal);
            mediaPlayer.start();
        }
    }

}
