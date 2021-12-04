package com.example.android_game_projet_tir_au_but.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_game_projet_tir_au_but.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterScores extends BaseAdapter {


    private List<Score> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public AdapterScores(Context applicationContext, List<Score> listData) {
        this.context = applicationContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(applicationContext);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Score getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        convertview = layoutInflater.inflate(R.layout.adapter_score, null);

        Score score = getItem(position);

        TextView date = convertview.findViewById(R.id.id_text_score_date);
        TextView score_number = convertview.findViewById(R.id.id_adapter_score_score);


        date.setText(String.valueOf(score.getDate()));
        score_number.setText(String.valueOf(score.getScore()));


        //   Log.e("nom de l'aliment", aliment.getNom());

        return convertview;
    }
}
