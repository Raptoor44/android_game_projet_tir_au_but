package com.example.android_game_projet_tir_au_but.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_game_projet_tir_au_but.R;
import com.example.android_game_projet_tir_au_but.TableauScore;

import java.util.List;

public class AdapterScores extends BaseAdapter {


    private final List<Score> listData;
    private final LayoutInflater layoutInflater;
    private final TableauScore tableauScore;

    private RelativeLayout layout;

    public AdapterScores(Context applicationContext, List<Score> listData, TableauScore score) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(applicationContext);
        this.tableauScore = score;

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

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {


        convertview = layoutInflater.inflate(R.layout.adapter_score, null);

        Score score = getItem(position);

        TextView date = convertview.findViewById(R.id.id_text_score_date);
        TextView score_number = convertview.findViewById(R.id.id_adapter_score_score);

        //init image
        Button delete = convertview.findViewById(R.id.id_adapter_score_delete_button);
        delete.setOnClickListener(view -> {
            ListScores.getScores().remove(position);
            Intent intent = new Intent(tableauScore.getApplicationContext(), TableauScore.class);
            tableauScore.startActivity(intent);
            tableauScore.finish();

        });


        date.setText(String.valueOf(score.getDate()));
        score_number.setText(String.valueOf(score.getScore()));




        return convertview;
    }
}
