package com.example.android_game_projet_tir_au_but.process.threadBallon;

import android.util.Log;

import com.example.android_game_projet_tir_au_but.model.Gardien;
import com.example.android_game_projet_tir_au_but.model.NbGardiens;

public class ButProcess {
    private final BallonProcess ballonProcess;

    public ButProcess(BallonProcess ballonProcess) {
        this.ballonProcess = ballonProcess;
    }

    public void detectionBut() {
        if (CollisionProcess.isCollisionDetected(ballonProcess.getGame().getBallon().getView(), ballonProcess.getGame().getBut())) {
            //Augmentation de nbGardiens

            NbGardiens.setNbGardiens(NbGardiens.getNbGardiens() + 1);

            //Suite


            ballonProcess.getGame().setVelocityX(0);
            ballonProcess.getGame().setVelocityY(0);

            ballonProcess.getGame().getScore().setScore();
            ballonProcess.getProcessScore().actualiserScore();


            ballonProcess.getGame().setBooleanMouvementBallonThread(false);

            //Gestion du son

            ballonProcess.getSon().jouerSonGoal();


            Log.e("chiffre i : ", ballonProcess.getGame().getNbGardiensActivites() + "");
            for (Gardien gardienEach : ballonProcess.getGame().getGardiens().getGardiens()) {

                gardienEach.getView().post(() -> {


                    gardienEach.augmenter();
                    ballonProcess.getColisionProcess().collision();


                });

            }


        }
    }
}