package com.example.android_game_projet_tir_au_but.process.threadBallon;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.example.android_game_projet_tir_au_but.controllerView.Game;
import com.example.android_game_projet_tir_au_but.model.Gardien;
import com.example.android_game_projet_tir_au_but.model.NbGardiens;

public class CollisionProcess {
    private Game game;

    public CollisionProcess(Game game) {
        this.game = game;
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

    public void collision() {

        game.setVelocityX(0);
        game.setVelocityY(0);


        game.getBallon().debut();


        game.setBooleanMouvementBallonThread(false);
        game.setNbGardiensActivites(0);


    }

}
