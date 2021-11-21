package com.example.android_game_projet_tir_au_but;

import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class Gardien {

    private ImageView main;
    private static int nb_instance = 0;
    private static int nb_grande = 0;
    private MainActivity mainActivity;


    public Gardien(MainActivity mainActivity) {

        this.mainActivity = mainActivity;
        main = null;
        this.main = new ImageView(mainActivity);
        this.main.setBackgroundResource(R.drawable.jersey_viscose_uni_couleur_noir_profond);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 20);
        this.main.setLayoutParams(params);

        this.main.setX(500);
        this.main.setY(300 );



        mainActivity.getFenetrePrincipale().addView(main);

        nb_instance++;
    }

    public void remove(){
        mainActivity.getFenetrePrincipale().removeView(main);
    }

    public void setX(float x){
        main.setX(x);
    }
    public void setY(float y){main.setY(y);
    }

    public float getX(){
        return  main.getX();
    }
    public float getY(){
        return  main.getY();
    }

    public ImageView getView() {
        return main;
    }

    public void augmenter(){


        nb_grande++;


        mainActivity.setFenetrePrincipale(null);

        mainActivity.setFenetrePrincipale(mainActivity.findViewById(R.id.fenetre_principale));

        for(Gardien ggg : mainActivity.getGardiens().getGardiens()){


            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ggg.getView().getWidth() + 20, 20);
            ggg.getView().setLayoutParams(params);

            mainActivity.getFenetrePrincipale().addView(ggg.getView());
        }




        if(nb_grande >= 10){
            nb_grande = 0;
            Gardien gg = new Gardien(mainActivity);
          mainActivity.getGardiens().add(gg);

        }



    }
}
