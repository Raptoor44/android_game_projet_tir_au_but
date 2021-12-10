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
import com.example.android_game_projet_tir_au_but.process.threadBallon.BallonProcess;
import com.example.android_game_projet_tir_au_but.process.threadGardiens.GardiensProcess;
import com.example.android_game_projet_tir_au_but.process.threadBallon.ScoreProcess;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


    /*
  Ceci est la classe principale de l'objet qui va simuler le jeu. Au départ la taille de classe n'était
  pas loin de 500 lignes, j'ai dû pratiquer du refactoring pour respecter le principe objet. (tout ce qui est
  dans le package process était issu de la classe Game). Maintenant, elle se présente comme une classe qui
  contient toutes les variables associé à l'activité Game et leurs instanciations avec quelques méthodes.
  Si vous voulez avoir la classe entière avant le refactoring, je vous invite à
  regarder dans les branches du github associé à ce projet (voir le fichier .txt que je vous ai fourni.
     */


    private final BallonProcess butProcess = new BallonProcess(this);

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
    private TextView scoreCourant;
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

        Game game = this;

        //init affichage score
        this.scoreCourant = findViewById(R.id.id_score_courant);


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


                ballon.getView().post(butProcess::tirProcess);

            }
        }
    };

    private void tirProcess() {

        butProcess.tirProcess();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

        ScoreProcess s = new ScoreProcess(this);
        s.enregistrerScore();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX_, float velocityY_) {

        this.velocityX = velocityX_ / 500;
        this.velocityY = velocityY_ / 500;


        this.booleanMouvementBallonThread = true;
        this.booleanMouvementGardiensThread = true;

        return true;

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


    public ImageView getBut() {
        return but;
    }

    public TextView getScoreCourant() {
        return scoreCourant;
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