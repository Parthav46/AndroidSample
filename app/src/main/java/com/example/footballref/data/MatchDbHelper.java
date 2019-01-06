package com.example.footballref.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.footballref.data.MatchContract.MatchEntry;

public class MatchDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "matches.db";
    private static final int DATABASE_VERSION = 1;

    MatchDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE " + MatchEntry.TABLE_NAME + " ("
                + MatchEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MatchEntry.COLUMN_TEAM_A + " TEXT NOT NULL, "
                + MatchEntry.COLUMN_TEAM_B + " TEXT NOT NULL, "
                + MatchEntry.COLUMN_SCORE_A + " INTEGER DEFAULT 0, "
                + MatchEntry.COLUMN_SCORE_B + " INTEGER DEFAULT 0);";

        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
