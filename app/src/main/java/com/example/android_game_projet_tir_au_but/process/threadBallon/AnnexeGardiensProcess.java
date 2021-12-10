package com.example.android_game_projet_tir_au_but.process.threadBallon;

import com.example.android_game_projet_tir_au_but.model.NbGardiens;
import com.example.android_game_projet_tir_au_but.process.threadGardiens.GardiensProcess;

public class AnnexeGardiensProcess {
    private final ScoreProcess scoreProcess;

    public AnnexeGardiensProcess(ScoreProcess scoreProcess) {
        this.scoreProcess = scoreProcess;
    }

    public void Annexe() {
        for (int i = 0; i < scoreProcess.getGame().getGardiens().getGardiens().size(); i++) {
            scoreProcess.getGame().getFenetrePrincipale().removeView(scoreProcess.getGame().getGardiens().getGardiens().get(i).getView());


        }
        GardiensProcess gardienp = new GardiensProcess(scoreProcess.getGame());

        gardienp.nouveauPremierGardien();

        NbGardiens.setNbGardiens(0);
    }
}