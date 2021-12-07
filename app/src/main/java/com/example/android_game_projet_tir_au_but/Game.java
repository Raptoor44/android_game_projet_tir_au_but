package com.example.android_game_projet_tir_au_but;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.android_game_projet_tir_au_but.Tools.Serializer;
import com.example.android_game_projet_tir_au_but.model.ListScores;
import com.example.android_game_projet_tir_au_but.model.Score;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


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

    //init list gardiens

    private ListGardiens gardiens;

    //init but

    private ImageView but;

    //Autres
    private int i = 0;

    //Scores
    private TextView score_courant;
    private Score score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.gestureDetector = new GestureDetectorCompat(this, this);

        this.fenetrePrincipale = findViewById(R.id.fenetre_principale);

        //init thread
        mouvementGardiensThread.scheduleAtFixedRate(mouvementGardien, 5, 5, TimeUnit.MILLISECONDS);
        mouvementBallonThread.scheduleAtFixedRate(lancerBallon, 20, 20, TimeUnit.MILLISECONDS);

        //init ballon

        ballon = new Ballon(this);

        //init bandes
        this.bandeDroite = findViewById(R.id.id_bande_droite);
        this.bandeGauche = findViewById(R.id.id_bande_gauche);

        //init gardien


        gardiens = new ListGardiens();


        Gardien gardien_1 = new Gardien(this);
        gardiens.add(gardien_1);


        //init but
        this.but = findViewById(R.id.id_but);

        game = this;

        //init affichage score
        this.score_courant = findViewById(R.id.id_score_courant);



        //Score
        this.score = new Score();

        actualiserScore();

    }


    public Runnable mouvementGardien = new Runnable() {
        @Override

        public void run() {
            if (booleanMouvementGardiensThread) {

                int x = 5;

                for (Gardien gardien_1 : gardiens.getGardiens()) {
                    gardien_1.getView().post(() -> {


                        if (!gardien_1.isParcourtTermine()) {
                            gardien_1.setX(gardien_1.getX() + (x + gardien_1.getVitesseGardien()));
                            if (gardien_1.getX() >= 1000) {
                                gardien_1.setParcourtTermine(true);
                            }
                        } else {
                            gardien_1.setX(gardien_1.getX() - (x + gardien_1.getVitesseGardien()));
                            if (gardien_1.getX() <= 0) {
                                gardien_1.setParcourtTermine(false);
                            }
                        }
                    });


                }

            }
        }
    };

    public Runnable lancerBallon = new Runnable() {
        @Override
        public void run() {

            if (booleanMouvementBallonThread) {


                ballon.getView().post(() -> {
                    ballon.setX(ballon.getX() + velocityX);
                    ballon.setY(ballon.getY() + velocityY);


                    //DETECTIONS DES COLISIONS DE TOUS LES GARDIENS
                    for (Gardien gardien_1 : gardiens.getGardiens()) {


                        gardien_1.getView().post(() -> {
                            if (isCollisionDetected(gardien_1.getView(), ballon.getView())) {

                                collisionNonBut();
                                enregistrerScore();

                            }
                        });


                    }


                    //DETECTIONS DE BUT
                    if (isCollisionDetected(ballon.getView(), but)) {
                        velocityX = 0;
                        velocityY = 0;

                        score.setScore();
                        actualiserScore();


                        booleanMouvementBallonThread = false;

                        //Gestion du son
                        if (Gardien.getNbInstance() == 5 ||
                                Gardien.getNbInstance() == 10
                                || Gardien.getNbInstance() == 15
                                || Gardien.getNbInstance() == 20
                                || Gardien.getNbInstance() == 25) {
                            jouerSonGoal();
                        }

                        i = 0;
                        for (Gardien gardien_1 : gardiens.getGardiens()) {

                            gardien_1.getView().post(() -> {


                                gardien_1.augmenter(i);
                                collisionNonBut();

                                i++;

                            });

                        }


                    }


                    //Detection de poteau
                    if (isCollisionDetected(ballon.getView(), bandeGauche)) {
                        collisionNonBut();
                        enregistrerScore();
                    }

                    if (isCollisionDetected(ballon.getView(), bandeDroite)) {
                        collisionNonBut();
                        enregistrerScore();
                    }

                });

            }
        }
    };

    private void jouerSonGoal() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.goal);
        mediaPlayer.start();
    }

    @SuppressLint("SetTextI18n")
    private void actualiserScore() {
        score_courant.setText("Votre score actuel : " + score.getScore());
    }


    private void collisionNonBut() {

        velocityX = 0;
        velocityY = 0;


        ballon.debut();


        booleanMouvementBallonThread = false;
    }

    private void enregistrerScore() {


        ListScores.getScores().add(this.score);
        String file_name = "Activity";
        Serializer.serialize(file_name, ListScores.getScores(), getApplicationContext());


        score = null;
        score = new Score();
        actualiserScore();


        for (int i = 0; i < gardiens.getGardiens().size(); i++) {
            this.fenetrePrincipale.removeView(gardiens.getGardiens().get(i).getView());





        }
        Gardien gardien_1 = new Gardien(this, gardiens.getGardiens().get(0).getX());
        gardiens.getGardiens().clear();

        gardiens.add(gardien_1);

        Gardien.resetStatic();



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

        this.velocityX = velocityX_ / 300;
        this.velocityY = velocityY_ / 300;


        this.booleanMouvementBallonThread = true;
        this.booleanMouvementGardiensThread = true;

        return true;

    }

    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public static boolean isCollisionDetected(View v1, View v2) {
        if (v1 == null || v2 == null) {
            Log.e("information", "Views must not be null");
            throw new IllegalArgumentException("Views mut be not null");
        }
        Rect R1 = new Rect();
        v1.getHitRect(R1);
        Rect R2 = new Rect();
        v2.getHitRect(R2);
        return Rect.intersects(R1, R2);
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


    public Runnable getMouvementGardien() {
        return mouvementGardien;
    }

    public Runnable getLancerBallon() {
        return lancerBallon;
    }

    public void creer_ballon() {
        ballon = new Ballon(this);
    }

    public void setFenetrePrincipale(RelativeLayout fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
    }

    public Game getMainActivity() {
        return game;
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

    public ScheduledExecutorService getMouvementGardiensThread() {
        return mouvementGardiensThread;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}