package com.example.android_game_projet_tir_au_but.process.threadBallon;

public class ColissionBordsProcess {
    private final BallonProcess but;

    public ColissionBordsProcess(BallonProcess but) {
        this.but = but;
    }

    void colissionBords() {
        but.getGame().getBandeGauche().post(() -> {
            //Detection de poteau
            if (CollisionProcess.isCollisionDetected(but.getGame().getBallon().getView(), but.getGame().getBandeGauche())) {
                but.getColisionProcess().collision();
                but.getProcessScore().enregistrerScore();
            }

        });
        but.getGame().getBandeDroite().post(() -> {
            if (CollisionProcess.isCollisionDetected(but.getGame().getBallon().getView(), but.getGame().getBandeDroite())) {
                but.getColisionProcess().collision();
                but.getProcessScore().enregistrerScore();
            }
        });

        //Détections bords

        but.getGame().getBordBas().post(() -> {
            if (CollisionProcess.isCollisionDetected(but.getGame().getBallon().getView(), but.getGame().getBordBas())) {
                but.getColisionProcess().collision();
                but.getProcessScore().enregistrerScore();
            }
        });


        but.getGame().getBordDroit().post(() -> {
            if (CollisionProcess.isCollisionDetected(but.getGame().getBallon().getView(), but.getGame().getBordDroit())) {
                but.getColisionProcess().collision();
                but.getProcessScore().enregistrerScore();
            }
        });

        but.getGame().getBordGauche().post(() -> {
            if (CollisionProcess.isCollisionDetected(but.getGame().getBallon().getView(), but.getGame().getBordGauche())) {
                but.getColisionProcess().collision();
                but.getProcessScore().enregistrerScore();
            }
        });
    }
}