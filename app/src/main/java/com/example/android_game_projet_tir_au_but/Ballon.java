package com.example.android_game_projet_tir_au_but;

import android.view.ViewGroup;
import android.widget.ImageView;

public class Ballon {
    private ImageView view;

    public Ballon(Game game) {

        view = null;
        this.view = new ImageView(game);
        this.view.setBackgroundResource(R.drawable._0193_1_serviette_en_papier_carre_ballon_de_foot);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(50, 50);
        this.view.setLayoutParams(params);

        this.view.setX(500);
        this.view.setY(1300);

        game.getFenetrePrincipale().addView(view);

    }
    public void debut(){
        this.view.setX(500);
        this.view.setY(1300);
    }

    public void setX(float x){
        view.setX(x);
    }
    public void setY(float y){
        view.setY(y);
    }

    public float getX(){
      return  view.getX();
    }
    public float getY(){
      return  view.getY();
    }

    public ImageView getView() {
        return view;
    }
}
