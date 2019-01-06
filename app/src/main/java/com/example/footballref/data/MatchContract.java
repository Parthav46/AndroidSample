package com.example.footballref.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class MatchContract {

    private MatchContract() {}

    static final String CONTENT_AUTHORITY = "com.example.footballref";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final class MatchEntry implements BaseColumns {
        static final String TABLE_NAME = "matches";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);
        static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_TEAM_A = "teamA";
        public static final String COLUMN_SCORE_A = "scoreA";
        public static final String COLUMN_TEAM_B = "teamB";
        public static final String COLUMN_SCORE_B = "scoreB";
    }

}
