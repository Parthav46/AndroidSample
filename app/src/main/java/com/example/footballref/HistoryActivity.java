package com.example.footballref;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.footballref.data.MatchContract.MatchEntry;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<MatchData> dataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        fetchData();
    }

    private void fetchData(){
        dataArrayList = new ArrayList<>();
        String[] projection = {MatchEntry.COLUMN_TEAM_A, MatchEntry.COLUMN_TEAM_B, MatchEntry.COLUMN_SCORE_A, MatchEntry.COLUMN_SCORE_B};
        Cursor cursor = getContentResolver().query(MatchEntry.CONTENT_URI, projection, null, null, null);
        try{
            int columnTeamA = cursor.getColumnIndex(MatchEntry.COLUMN_TEAM_A);
            int columnTeamB = cursor.getColumnIndex(MatchEntry.COLUMN_TEAM_B);
            int columnScoreA = cursor.getColumnIndex(MatchEntry.COLUMN_SCORE_A);
            int columnScoreB = cursor.getColumnIndex(MatchEntry.COLUMN_SCORE_B);
            while (cursor.moveToNext()){
                MatchData currentData = new MatchData(cursor.getString(columnTeamA), cursor.getString(columnTeamB), cursor.getInt(columnScoreA), cursor.getInt(columnScoreB));
                dataArrayList.add(currentData);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }
}
