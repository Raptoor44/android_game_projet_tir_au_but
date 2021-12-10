package com.example.android_game_projet_tir_au_but.controllerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.android_game_projet_tir_au_but.R;
import com.example.android_game_projet_tir_au_but.model.Ballon;
import com.example.android_game_projet_tir_au_but.model.Gardien;
import com.example.android_game_projet_tir_au_but.model.ListGardiens;
import com.example.android_game_projet_tir_au_but.model.Score;
import com.example.android_game_projet_tir_au_but.process.ButProcess;
import com.example.android_game_projet_tir_au_but.process.GardiensProcess;
import com.example.android_game_projet_tir_au_but.process.ScoreProcess;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


    private final ButProcess but1 = new ButProcess(this);
    private Game game;

    private GestureDetectorCompat gestureDetector;

    //init image view
    private Ballon ballon;
    private RelativeLayout fenetrePrincipale;

    //init Thread
    private final ScheduledExecutorService mouvementBallonThread = Executors.newScheduledThreadPool(1);

    private final ScheduledExecutorService mouvementGardiensThread = Executors.newScheduledThreadPool(1);
    //init activation thread
    private boolean booleanMouvementBallonThread = false;
    private boolean booleanMouvementGardiensThread = false;


    //Variables de vitesse de ballon :
    private float velocityX;
    private float velocityY;


    //init bande droite et gauche
    private ImageView bandeDroite;
    private ImageView bandeGauche;

    //init bords
    private ImageView bordDroit;
    private ImageView bordGauche;
    private ImageView bordBas;
    //init list gardiens

    private ListGardiens gardiens;

    //init but

    private ImageView but;

    //Autres
    private int nbGardiensActivites = 0;

    //Scores
    private TextView score_courant;
    private Score score;


    //Tools
    private GardiensProcess gardiensProcess;


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CreateProcess(savedInstanceState);


    }

    private void CreateProcess(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.gestureDetector = new GestureDetectorCompat(this, this);

        this.fenetrePrincipale = findViewById(R.id.fenetre_principale);

        //init thread
        mouvementGardiensThread.scheduleAtFixedRate(mouvementGardien, 5, 5, TimeUnit.MILLISECONDS);
        mouvementBallonThread.scheduleAtFixedRate(lancerBallon, 17, 17, TimeUnit.MILLISECONDS);


        //init ballon

        ballon = new Ballon(this);

        //init bandes
        this.bandeDroite = findViewById(R.id.id_bande_droite);
        this.bandeGauche = findViewById(R.id.id_bande_gauche);

        //init bords
        this.bordBas = findViewById(R.id.id_activity_game_bas);
        this.bordDroit = findViewById(R.id.id_activity_game_droit);
        this.bordGauche = findViewById(R.id.id_activity_game_gauche);


        //init gardien


        gardiens = new ListGardiens();


        Gardien gardienUn = new Gardien(this);
        gardiens.add(gardienUn);


        //init but
        this.but = findViewById(R.id.id_but);

        game = this;

        //init affichage score
        this.score_courant = findViewById(R.id.id_score_courant);


        //Score
        this.score = new Score();

        ScoreProcess s = new ScoreProcess(this);

        s.actualiserScore();


        //tools
        gardiensProcess = new GardiensProcess(this);
    }


    public Runnable mouvementGardien = new Runnable() {
        @Override

        public void run() {
            if (booleanMouvementGardiensThread) {

                int x = 5;

                gardiensProcess.mouvementsGardiens(x);

            }
        }
    };


    public Runnable lancerBallon = new Runnable() {
        @Override
        public void run() {

            if (booleanMouvementBallonThread) {


                ballon.getView().post(but1::tirProcess);

            }
        }
    };

    private void tirProcess() {

        but1.tirProcess();
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX_, float velocityY_) {

        this.velocityX = velocityX_ / 500;
        this.velocityY = velocityY_ / 500;


        this.booleanMouvementBallonThread = true;
        this.booleanMouvementGardiensThread = true;

        return true;

    }

    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public RelativeLayout getFenetrePrincipale() {
        return fenetrePrincipale;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public ImageView getBandeDroite() {
        return bandeDroite;
    }

    public ImageView getBandeGauche() {
        return bandeGauche;
    }


    public void creerBallon() {
        ballon = new Ballon(this);
    }


    public ImageView getBut() {
        return but;
    }

    public TextView getScore_courant() {
        return score_courant;
    }

    public Score getScore() {
        return score;
    }

    public ListGardiens getGardiens() {
        return gardiens;
    }


    public int getNbGardiensActivites() {
        return nbGardiensActivites;
    }

    public void setNbGardiensActivites(int nbGardiensActivites) {
        this.nbGardiensActivites = nbGardiensActivites;
    }

    public Game getGame() {
        return game;
    }


    public Ballon getBallon() {
        return ballon;
    }


    public float getVelocityX() {
        return velocityX;
    }

    public ImageView getBordDroit() {
        return bordDroit;
    }

    public ImageView getBordGauche() {
        return bordGauche;
    }

    public ImageView getBordBas() {
        return bordBas;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

        ScoreProcess s = new ScoreProcess(this);
        s.enregistrerScore();
    }


    public void setScore(Score score) {
        this.score = score;
    }


    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }


    public void setBooleanMouvementBallonThread(boolean booleanMouvementBallonThread) {
        this.booleanMouvementBallonThread = booleanMouvementBallonThread;
    }





}