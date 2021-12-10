package com.example.android_game_projet_tir_au_but.process.threadBallon;

import static com.example.android_game_projet_tir_au_but.process.threadBallon.CollisionProcess.isCollisionDetected;

import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.Gardien;

public class BallonProcess {
    private final Game game;
    private final ColissionBordsProcess colissionBordsProcess = new ColissionBordsProcess(this);
    private final ButProcess butProcess = new ButProcess(this);
    private final CollisionProcess colisionProcess;
    private final SonProcess son;

    private final ScoreProcess processScore;


    public BallonProcess(Game game) {

        this.game = game;
        this.colisionProcess = new CollisionProcess(this.game);
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

                    this.colisionProcess.collision();
                    this.processScore.enregistrerScore();

                }
            });


        }

        //DETECTIONS DE BUT
        game.getBut().post(butProcess::detectionBut);


        colissionBordsProcess.colissionBords();
    }

    public void detectionBut() {
        butProcess.detectionBut();
    }

    private void colissionBords() {

        //Détections bords


        colissionBordsProcess.colissionBords();
    }

    public Game getGame() {
        return game;
    }

    public CollisionProcess getColisionProcess() {
        return colisionProcess;
    }

    public ScoreProcess getProcessScore() {
        return processScore;
    }

    public SonProcess getSon() {
        return son;
    }
}