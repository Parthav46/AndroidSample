package com.example.footballref;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<MatchData> {
    private final Activity context;
    private ArrayList<MatchData> data;

    CustomListAdapter(Activity context, ArrayList<MatchData> data) {
        super(context, R.layout.listviewcustom, data);
        this.context = context;
        this.data = data;

    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if (view == null) {
            view = inflater.inflate(R.layout.listviewcustom, parent, false);
        }

        TextView titleText1 = view.findViewById(R.id.text_team1);
        TextView titleText1S = view.findViewById(R.id.text_team1score);
        TextView titleText2 = view.findViewById(R.id.text_team2);
        TextView titleText2S = view.findViewById(R.id.text_team2score);
        if(data.get(position).getScoreA() > data.get(position).getScoreB()){
            titleText1S.setTextColor(parent.getResources().getColor(R.color.green));
            titleText2S.setTextColor(parent.getResources().getColor(R.color.cardRed));
        } else if(data.get(position).getScoreA() < data.get(position).getScoreB()){
            titleText1S.setTextColor(parent.getResources().getColor(R.color.cardRed));
            titleText2S.setTextColor(parent.getResources().getColor(R.color.green));
        } else {
            titleText1S.setTextColor(parent.getResources().getColor(android.R.color.black));
            titleText2S.setTextColor(parent.getResources().getColor(android.R.color.black));
        }
        titleText1.setText(data.get(position).getTeamA());
        titleText1S.setText(String.valueOf(data.get(position).getScoreA()));
        titleText2.setText(data.get(position).getTeamB());
        titleText2S.setText(String.valueOf(data.get(position).getScoreB()));

        return view;

    }


}
