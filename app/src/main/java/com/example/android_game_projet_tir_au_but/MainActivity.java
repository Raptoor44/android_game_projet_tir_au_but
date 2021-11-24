package com.example.android_game_projet_tir_au_but;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


    private MainActivity mainActivity;

    private GestureDetectorCompat gestureDetector;

    //init image view
    private Ballon ballon;
    private RelativeLayout fenetrePrincipale;

    //init Thread
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledExecutorService gardien = Executors.newScheduledThreadPool(1);
    //init activation thread
    private boolean thread_bouger_ballon = false;
    private boolean thread_gardien = false;


    //Variables de vitesse de ballon :
    private float velocityX;
    private float velocityY;


    //init bande droite et gauche
    private ImageView bande_droite;
    private ImageView bande_gauche;

    //init list gardiens

    private ListGardiens gardiens;

    //init but

    private ImageView but;

    //Autres
    private boolean parcourt_termine = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.gestureDetector = new GestureDetectorCompat(this, this);

        this.fenetrePrincipale = findViewById(R.id.fenetre_principale);

        //init thread
        gardien.scheduleAtFixedRate(mouvement_gardien, 10, 10, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(lancer_ballon, 30, 30, TimeUnit.MILLISECONDS);

        //init ballon

        ballon = new Ballon(this);

        //init bandes
        this.bande_droite = findViewById(R.id.id_bande_droite);
        this.bande_gauche = findViewById(R.id.id_bande_gauche);

        //init gardien


        gardiens = new ListGardiens();
        int multiplicateur_y = 0;
        for (int i = 0; i < Gardien.getNb_gardien(); i++) {
            Gardien gardien_1 = new Gardien(this, multiplicateur_y);
            gardiens.add(gardien_1);
            multiplicateur_y++;
        }


        //init but
        this.but = findViewById(R.id.id_but);

        mainActivity = this;

    }

    public Runnable mouvement_gardien = new Runnable() {
        @Override
        public void run() {
            if (thread_gardien == true) {
                int x = 5;


                int vitesse_differente = 0;
                for (Gardien gardien_1 : gardiens.getGardiens()) {
                    if (parcourt_termine == false) {
                        gardien_1.setX(gardien_1.getX() + (x + vitesse_differente) );
                        if (gardien_1.getX() >= 1000) {
                            parcourt_termine = true;
                        }
                    } else {
                        gardien_1.setX(gardien_1.getX() - (x + vitesse_differente) );
                        if (gardien_1.getX() <= 0) {
                            parcourt_termine = false;
                        }
                    }
                    int max = 10;
                    int min = 1;
                    int range = max - min + 1;
                    vitesse_differente = (int) (Math.random() * range) + min;
                }

            }
        }
    };

    public Runnable lancer_ballon = new Runnable() {
        @Override
        public void run() {

            if (thread_bouger_ballon == true) {
                ballon.setX(ballon.getX() + velocityX);
                ballon.setY(ballon.getY() + velocityY);


                //DETECTIONS DES COLISIONS DE TOUS LES GARDIENS
                for (Gardien gardien_1 : gardiens.getGardiens()) {
                    if (isCollisionDetected(gardien_1.getView(), ballon.getBallon())) {

                        Log.e("co de ballon ", " x : " + ballon.getX() + " y : " + ballon.getY());
                        velocityX = 0;
                        velocityY = 0;


                        ballon.debut();

                        Log.e("co de ballon ", " x : " + ballon.getX() + " y : " + ballon.getY());

                        thread_gardien = false;
                        thread_bouger_ballon = false;


                    }


                }
                //DETECTIONS DE BUT
                if (isCollisionDetected(ballon.getBallon(), but)) {
                    velocityX = 0;
                    velocityY = 0;


                    thread_gardien = false;
                    thread_bouger_ballon = false;

                    for (Gardien gardien_1 : gardiens.getGardiens()) {
                        gardien_1.augmenter();
                    }


                }
            }
        }
    };


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

        this.velocityX = velocityX_ / 100;
        this.velocityY = velocityY_ / 100;


        this.thread_bouger_ballon = true;
        this.thread_gardien = true;

        return true;

    }

    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public static boolean isCollisionDetected(View v1, View v2) {
        if (v1 == null || v2 == null) {
            Log.e("giome", "Views must not be null");
            throw new IllegalArgumentException("Views mut be not null");
        }
        Rect R1 = new Rect();
        v1.getHitRect(R1);
        Rect R2 = new Rect();
        v2.getHitRect(R2);
        return Rect.intersects(R1, R2);
    }

    public GestureDetectorCompat getGestureDetector() {
        return gestureDetector;
    }

    public ImageView getBallon() {
        return ballon.getBallon();
    }

    public RelativeLayout getFenetrePrincipale() {
        return fenetrePrincipale;
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public ScheduledExecutorService getGardien() {
        return gardien;
    }

    public boolean isThread_bouger_ballon() {
        return thread_bouger_ballon;
    }

    public boolean isThread_gardien() {
        return thread_gardien;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public ImageView getBande_droite() {
        return bande_droite;
    }

    public ImageView getBande_gauche() {
        return bande_gauche;
    }


    public boolean isParcourt_termine() {
        return parcourt_termine;
    }

    public Runnable getMouvement_gardien() {
        return mouvement_gardien;
    }

    public Runnable getLancer_ballon() {
        return lancer_ballon;
    }

    public void creer_ballon() {
        ballon = new Ballon(this);
    }

    public void setFenetrePrincipale(RelativeLayout fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
    }

    public ListGardiens getGardiens() {
        return gardiens;
    }
}