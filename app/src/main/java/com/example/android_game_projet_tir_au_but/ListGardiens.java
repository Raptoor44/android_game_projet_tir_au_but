package com.example.android_game_projet_tir_au_but;

import java.util.ArrayList;
import java.util.List;

public class ListGardiens {

    private List<Gardien> gardiens;

    public ListGardiens() {
        this.gardiens = new ArrayList<Gardien>();
    }

    public void remove(Gardien gardien){

        this.gardiens.remove(gardien);
    }

    public void add(Gardien gardien){
        this.gardiens.add(gardien);
    }

    public List<Gardien> getGardiens() {
        return gardiens;
    }
}
