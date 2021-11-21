package com.example.android_game_projet_tir_au_but;

import android.view.ViewGroup;
import android.widget.ImageView;

public class Ballon {
    private ImageView ballon;
    private MainActivity mainActivity;

    public Ballon(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        ballon = null;
        this.ballon = new ImageView(mainActivity);
        this.ballon.setBackgroundResource(R.drawable._0193_1_serviette_en_papier_carre_ballon_de_foot);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(50, 50);
        this.ballon.setLayoutParams(params);

        this.ballon.setX(500);
        this.ballon.setY(1300);

        mainActivity.getFenetrePrincipale().addView(ballon);

    }
    public void debut(){
        this.ballon.setX(500);
        this.ballon.setY(1300);
    }

    public void setX(float x){
        ballon.setX(x);
    }
    public void setY(float y){
        ballon.setY(y);
    }

    public float getX(){
      return  ballon.getX();
    }
    public float getY(){
      return  ballon.getY();
    }

    public ImageView getBallon() {
        return ballon;
    }
}
