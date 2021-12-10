package com.example.android_game_projet_tir_au_but.model;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.R;

public class Gardien {

    private final ImageView view;
    private static int nbInstance = 0;
    private static int nbGardien = 1;
    private static int nbGrande = 0;
    private int vitesseGardien;
    private final Game game;
    private boolean parcourtTermine = false;


    public Gardien(Game game) { // Constructeur d'ajout du premier gardien

        this.game = game;


        this.view = new ImageView(game);
        this.view.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 20);
        this.view.setLayoutParams(params);


        this.view.setY(400 + nbGardien * 100);


        game.getFenetrePrincipale().addView(view);


        nbInstance++;


    }


    public Gardien(Game game, float X) {//Constructeur d'ajout d'un gardien

        this.game = game;


        this.view = new ImageView(game);
        this.view.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 20);
        this.view.setLayoutParams(params);


        this.view.setY(400 + nbGardien * 100);
        this.view.setX(X + 50);

        game.getFenetrePrincipale().addView(view);


        nbInstance++;

        if (nbInstance == 0) {
            vitesseGardien = 0;
            this.view.setX(X);
        } else {
            vitesseGardien = (int) (Math.random() * 2);
        }


    }

    public Gardien(Game game, float Y, float X, int vitesse_gardien, boolean termine, int width) { // Constructeur d'augmentation des gardiens

        this.game = game;


        this.view = new ImageView(game);
        this.view.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width + 20, 20);
        this.view.setLayoutParams(params);


        this.view.setY(Y);
        this.view.setX(X);


        game.getFenetrePrincipale().addView(view);

        this.vitesseGardien = vitesse_gardien;
        this.setParcourtTermine(termine);

        nbInstance++;


    }

    public void remove() {
        game.getFenetrePrincipale().removeView(view);
    }

    public void setX(float x) {
        view.setX(x);
    }

    public void setY(float y) {
        view.setY(y);
    }

    public float getX() {
        return view.getX();
    }

    public float getY() {
        return view.getY();
    }

    public ImageView getView() {
        return view;
    }

    public void augmenter() {


        //Permet en dessous d'ajouter des gardiens.
        nbGrande++;
        if (nbGrande >= 10 + game.getNbGardiensActivites() * 10) { //le i *10 prend en compte le nombre croissant de gardiens.
            nbGrande = 0;
            nbGardien++;
            game.getGardiens().add(new Gardien(this.game, this.getX()));
            game.setNbGardiensActivites(game.getNbGardiensActivites() + 1);

        }

        //Permute d'augmenter la taille des garidens
        this.game.getFenetrePrincipale().removeView(this.getView());


        game.getGardiens().add(new Gardien(this.game,
                this.getView().getY(),
                this.getView().getX(),
                this.vitesseGardien,
                this.parcourtTermine,
                this.getView().getWidth()));

        //this.game.getGardien().scheduleAtFixedRate(this.game.getMouvement_gardien(), 10, 10, TimeUnit.MILLISECONDS);


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

    public static void setNbInstance(int nbInstance) {
        Gardien.nbInstance = nbInstance;
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
