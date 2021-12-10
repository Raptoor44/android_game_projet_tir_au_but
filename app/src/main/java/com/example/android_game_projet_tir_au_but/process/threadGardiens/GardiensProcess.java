package com.example.android_game_projet_tir_au_but.process.threadGardiens;

import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.Gardien;

public class GardiensProcess {

    private final Game game;

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

    public void nouveauPremierGardien() {
        Gardien gardienUn = new Gardien(this.game);
        this.game.getGardiens().getGardiens().clear();

        this.game.getGardiens().add(gardienUn);

        Gardien.resetStatic();
    }
}
