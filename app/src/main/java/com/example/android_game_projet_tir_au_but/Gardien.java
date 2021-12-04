package com.example.android_game_projet_tir_au_but;

import android.view.ViewGroup;
import android.widget.ImageView;

public class Gardien {

    private final ImageView main;
    private static int nb_instance = 0;
    private static int nb_gardien = 1;
    private static int nb_grande = 0;
    private final MainActivity mainActivity;


    public Gardien(MainActivity mainActivity) {

        this.mainActivity = mainActivity;


        this.main = new ImageView(mainActivity);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100 + nb_instance * 10, 20);
        this.main.setLayoutParams(params);


        this.main.setY(400 + nb_gardien * 100);


        mainActivity.getFenetrePrincipale().addView(main);


        nb_instance++;


    }
    public Gardien(MainActivity mainActivity, float X) {

        this.mainActivity = mainActivity;


        this.main = new ImageView(mainActivity);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100 + nb_instance * 10, 20);
        this.main.setLayoutParams(params);


        this.main.setY(400 + nb_gardien * 100);
        this.main.setX(X + 50);

        mainActivity.getFenetrePrincipale().addView(main);


        nb_instance++;


    }
    public Gardien(MainActivity mainActivity, float Y, float X) {

        this.mainActivity = mainActivity;


        this.main = new ImageView(mainActivity);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100 + nb_instance * 10, 20);
        this.main.setLayoutParams(params);


        this.main.setY(Y);
        this.main.setX(X);


        mainActivity.getFenetrePrincipale().addView(main);


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
        if (nb_grande >= 10) {
            nb_grande = 0;
            nb_gardien++;
            mainActivity.getGardiens().add(new Gardien(this.mainActivity, this.getX()));

        }


        this.mainActivity.getFenetrePrincipale().removeView(this.getView());
        mainActivity.getGardiens().add(new Gardien(this.mainActivity, this.getView().getY(), this.getView().getX()));


        nb_instance++;


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
