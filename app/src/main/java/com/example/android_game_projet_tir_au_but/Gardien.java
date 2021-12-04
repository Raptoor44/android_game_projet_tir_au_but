package com.example.android_game_projet_tir_au_but;

import android.view.ViewGroup;
import android.widget.ImageView;

public class Gardien {

    private final ImageView main;
    private static int nb_instance = 0;
    private static int nb_gardien = 1;
    private static int nb_grande = 0;
    private int vitesse_gardien;
    private final Game game;
    private boolean parcourt_termine = false;


    public Gardien(Game game) {

        this.game = game;


        this.main = new ImageView(game);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100 + nb_instance * 10, 20);
        this.main.setLayoutParams(params);


        this.main.setY(400 + nb_gardien * 100);


        game.getFenetrePrincipale().addView(main);


        nb_instance++;


    }

    public Gardien(Game game, float X) {

        this.game = game;


        this.main = new ImageView(game);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100 + nb_instance * 10, 20);
        this.main.setLayoutParams(params);


        this.main.setY(400 + nb_gardien * 100);
        this.main.setX(X + 50);

        game.getFenetrePrincipale().addView(main);


        nb_instance++;

        vitesse_gardien = (int) (Math.random() * 10);

    }

    public Gardien(Game game, float Y, float X, int vitesse_gardien, boolean termine) {

        this.game = game;


        this.main = new ImageView(game);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100 + nb_instance * 10, 20);
        this.main.setLayoutParams(params);


        this.main.setY(Y);
        this.main.setX(X);


        game.getFenetrePrincipale().addView(main);

        this.vitesse_gardien = vitesse_gardien;
        this.setParcourt_termine(termine);

        nb_instance++;




    }

    public void remove() {
        game.getFenetrePrincipale().removeView(main);
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
            game.getGardiens().add(new Gardien(this.game, this.getX()));

        }


        this.game.getFenetrePrincipale().removeView(this.getView());
        game.getGardiens().add(new Gardien(this.game,
                this.getView().getY(),
                this.getView().getX(),
                this.vitesse_gardien, this.parcourt_termine));


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

    public int getVitesse_gardien() {
        return vitesse_gardien;
    }

    public boolean isParcourt_termine() {
        return parcourt_termine;
    }

    public void setParcourt_termine(boolean parcourt_termine) {
        this.parcourt_termine = parcourt_termine;
    }
}
