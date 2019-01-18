package com.example.footballref;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<MatchData> {
    private final Activity context;
    private ArrayList<MatchData> data;

    public CustomListAdapter(Activity context, ArrayList<MatchData> data) {
        super(context, R.layout.listviewcustom, data);
        this.context = context;
        this.data = data;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listviewcustom, parent, false);
        }

        TextView titleText1 = (TextView) view.findViewById(R.id.text_team1);
        TextView titleText1S = (TextView) view.findViewById(R.id.text_team1score);
        TextView titleText2 = (TextView) view.findViewById(R.id.text_team2);
        TextView titleText2S = (TextView) view.findViewById(R.id.text_team2score);
        titleText1.setText(data.get(position).getTeamA());
        titleText1S.setText(String.valueOf(data.get(position).getScoreA()));
        titleText2.setText(data.get(position).getTeamB());
        titleText2S.setText(String.valueOf(data.get(position).getScoreB()));

        return view;

    }


}
