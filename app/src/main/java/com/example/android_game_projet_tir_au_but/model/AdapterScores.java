package com.example.android_game_projet_tir_au_but.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_game_projet_tir_au_but.R;
import com.example.android_game_projet_tir_au_but.TableauScore;

import java.util.List;

public class AdapterScores extends BaseAdapter {


    private List<Score> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private TableauScore tableauScore;

    private RelativeLayout layout;

    public AdapterScores(Context applicationContext, List<Score> listData, TableauScore score) {
        this.context = applicationContext;
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

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        /*
        layout = convertview.findViewById(R.id.id_adapter_score_layout_);
        if (position == 0) {
            ImageView trophee;


            trophee = new ImageView(this.tableauScore);

            trophee.setBackgroundResource(R.drawable.trophee);


            trophee.setX(300);

            layout.addView(trophee);
        }
*/

        convertview = layoutInflater.inflate(R.layout.adapter_score, null);

        Score score = getItem(position);

        TextView date = convertview.findViewById(R.id.id_text_score_date);
        TextView score_number = convertview.findViewById(R.id.id_adapter_score_score);

        //init image
        ImageButton delete = convertview.findViewById(R.id.id_adapter_score_delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListScores.getScores().remove(position);
                Intent intent = new Intent(tableauScore.getApplicationContext(), TableauScore.class);
                tableauScore.startActivity(intent);
                tableauScore.finish();

            }
        });


        date.setText(String.valueOf(score.getDate()));
        score_number.setText(String.valueOf(score.getScore()));


        //   Log.e("nom de l'aliment", aliment.getNom());

        return convertview;
    }
}
