package com.example.android_game_projet_tir_au_but.process;

import static com.example.android_game_projet_tir_au_but.process.CollisionProcess.isCollisionDetected;

import android.util.Log;

import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.Gardien;

public class ButProcess {
    private final Game game;
    private final ColissionBordsProcess colissionBordsProcess = new ColissionBordsProcess(this);
    private CollisionProcess coli;
    private SonProcess son;

    private ScoreProcess processScore;


    public ButProcess(Game game) {

        this.game = game;
        this.coli = new CollisionProcess(this.game);
        this.son = new SonProcess(this.game);
        this.processScore = new ScoreProcess(this.game);
    }

    public void tirProcess() {
        game.getBallon().setX(game.getBallon().getX() + game.getVelocityX());
        game.getBallon().setY(game.getBallon().getY() + game.getVelocityY());


        //DETECTIONS DES COLISIONS DE TOUS LES GARDIENS
        for (Gardien gardienEach : game.getGardiens().getGardiens()) {


            gardienEach.getView().post(() -> {
                if (isCollisionDetected(gardienEach.getView(), game.getBallon().getView())) {

                    this.coli.collision();
                    this.processScore.enregistrerScore();

                }
            });


        }

        game.getBut().post(() -> {
            //DETECTIONS DE BUT
            if (isCollisionDetected(game.getBallon().getView(), game.getBut())) {
                game.setVelocityX(0);
                game.setVelocityY(0);

                game.getScore().setScore();
                this.processScore.actualiserScore();


                game.setBooleanMouvementBallonThread(false);

                //Gestion du son
                if (Gardien.getNbInstance() == 5 ||
                        Gardien.getNbInstance() == 10
                        || Gardien.getNbInstance() == 15
                        || Gardien.getNbInstance() == 20
                        || Gardien.getNbInstance() == 25) {
                       son.jouerSonGoal();
                }


                Log.e("chiffre i : ", game.getNbGardiensActivites() + "");
                for (Gardien gardienEach : game.getGardiens().getGardiens()) {

                    gardienEach.getView().post(() -> {


                        gardienEach.augmenter();
                        this.coli.collision();


                    });

                }


            }
        });


        colissionBordsProcess.colissionBords();
    }

    private void colissionBords() {

        //Détections bords


        colissionBordsProcess.colissionBords();
    }

    public Game getGame() {
        return game;
    }

    public CollisionProcess getColi() {
        return coli;
    }

    public ScoreProcess getProcessScore() {
        return processScore;
    }
}