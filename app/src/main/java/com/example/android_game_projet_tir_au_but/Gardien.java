package com.example.android_game_projet_tir_au_but;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class Gardien {

    private ImageView main;
    private static int nb_instance = 0;
    private static int nb_gardien = 1;
    private static int nb_grande = 0;
    private static int taille = 0;
    private MainActivity mainActivity;


    public Gardien(MainActivity mainActivity, int multiplicateur_y ) {

        this.mainActivity = mainActivity;





    this.main = new ImageView(mainActivity);
    this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100 + taille * 10, 20);
    this.main.setLayoutParams(params);

    this.main.setX(500 + (multiplicateur_y *100));
    this.main.setY(300 + (multiplicateur_y * 100));



    mainActivity.getFenetrePrincipale().addView(main);

        if (nb_grande >= 10) {
            nb_grande = 0;
            nb_gardien++;

        }


        nb_instance++;


    }

    public void remove() {
        mainActivity.getFenetrePrincipale().removeView(main);
    }

    public void setX(float x) {
        main.setX(x);
    }

    public void setY(float y) {
        main.setY(y);
    }

    public float getX() {
        return main.getX();
    }

    public float getY() {
        return main.getY();
    }

    public ImageView getView() {
        return main;
    }

    public void augmenter() {


        nb_grande++;
        taille++;


        Intent intent = new Intent(mainActivity.getApplicationContext(), mainActivity.getClass());
        mainActivity.finish();
        mainActivity.startActivity(intent);


    }

    public static int getNb_gardien() {
        return nb_gardien;
    }

    public static int getNb_grande() {
        return nb_grande;
    }

    public static int getNb_instance() {
        return nb_instance;
    }
}
