package com.example.android_game_projet_tir_au_but.process;

import android.media.MediaPlayer;

import com.example.android_game_projet_tir_au_but.R;
import com.example.android_game_projet_tir_au_but.controllerView.Game;

public class SonProcess {


    private Game game;

    public SonProcess(Game game) {
        this.game = game;
    }

    public void jouerSonGoal() {
        MediaPlayer mediaPlayer = MediaPlayer.create(game.getApplicationContext(), R.raw.goal);
        mediaPlayer.start();
    }

}
