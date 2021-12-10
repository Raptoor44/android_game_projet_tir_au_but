package com.example.android_game_projet_tir_au_but.process;

import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.Gardien;

public class GardiensProcess {

    private Game game;

    public GardiensProcess(Game game) {
        this.game = game;
    }

    public void mouvementsGardiens(int x) {
        for (Gardien gardienEach : game.getGardiens().getGardiens()) {
            gardienEach.getView().post(() -> {


                if (!gardienEach.isParcourtTermine()) {
                    gardienEach.setX(gardienEach.getX() + (x + gardienEach.getVitesseGardien()));
                    if (gardienEach.getX() >= 1000) {
                        gardienEach.setParcourtTermine(true);
                    }
                } else {
                    gardienEach.setX(gardienEach.getX() - (x + gardienEach.getVitesseGardien()));
                    if (gardienEach.getX() <= 0) {
                        gardienEach.setParcourtTermine(false);
                    }
                }
            });


        }
    }
}
