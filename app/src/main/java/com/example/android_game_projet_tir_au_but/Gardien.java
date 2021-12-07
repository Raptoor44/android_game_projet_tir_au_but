package com.example.android_game_projet_tir_au_but;

import android.view.ViewGroup;
import android.widget.ImageView;

public class Gardien {

    private final ImageView main;
    private static int nbInstance = 0;
    private static int nbGardien = 1;
    private static int nbGrande = 0;
    private int vitesseGardien;
    private final Game game;
    private boolean parcourtTermine = false;


    public Gardien(Game game) { // Constructeur d'ajout du premier gardien

        this.game = game;


        this.main = new ImageView(game);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 20);
        this.main.setLayoutParams(params);


        this.main.setY(400 + nbGardien * 100);


        game.getFenetrePrincipale().addView(main);


        nbInstance++;


    }

    public Gardien(Game game, int x) { // Constructeur d'ajout du premier gardien

        this.game = game;


        this.main = new ImageView(game);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 20);
        this.main.setLayoutParams(params);


        this.main.setY(400 + nbGardien * 100);
        this.main.setX(x);

        game.getFenetrePrincipale().addView(main);


        nbInstance++;


    }

    public Gardien(Game game, float X) {//Constructeur d'ajout d'un gardien

        this.game = game;


        this.main = new ImageView(game);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 20);
        this.main.setLayoutParams(params);


        this.main.setY(400 + nbGardien * 100);
        this.main.setX(X + 50);

        game.getFenetrePrincipale().addView(main);


        nbInstance++;

        vitesseGardien = (int) (Math.random() * 2);

    }

    public Gardien(Game game, float Y, float X, int vitesse_gardien, boolean termine, int width) { // Constructeur d'augmentation des gardiens

        this.game = game;


        this.main = new ImageView(game);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width + 20, 20);
        this.main.setLayoutParams(params);


        this.main.setY(Y);
        this.main.setX(X);


        game.getFenetrePrincipale().addView(main);

        this.vitesseGardien = vitesse_gardien;
        this.setParcourtTermine(termine);

        nbInstance++;


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

    public void augmenter(int i) {


        nbGrande++;
        if (nbGrande >= 10 + i * 10) { //le i *10 prend en compte le nombre croissant de gardiens.
            nbGrande = 0;
            nbGardien++;
            game.getGardiens().add(new Gardien(this.game, this.getX()));

        }


        this.game.getFenetrePrincipale().removeView(this.getView());
        game.getGardiens().add(new Gardien(this.game,
                this.getView().getY(),
                this.getView().getX(),
                this.vitesseGardien,
                this.parcourtTermine,
                this.getView().getWidth()));

        //this.game.getGardien().scheduleAtFixedRate(this.game.getMouvement_gardien(), 10, 10, TimeUnit.MILLISECONDS);
        nbInstance++;


    }

    public static int getNbGardien() {
        return nbGardien;
    }

    public static int getNbGrande() {
        return nbGrande;
    }

    public static int getNbInstance() {
        return nbInstance;
    }

    public int getVitesseGardien() {
        return vitesseGardien;
    }

    public boolean isParcourtTermine() {
        return parcourtTermine;
    }

    public void setParcourtTermine(boolean parcourtTermine) {
        this.parcourtTermine = parcourtTermine;
    }

    public static void resetStatic() {


        nbInstance = 0;
        nbGardien = 0;
        nbGrande = 0;

    }
}
