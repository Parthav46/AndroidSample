package com.example.footballref.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.footballref.data.MatchContract.MatchEntry;

public class MatchProvider extends ContentProvider {

    MatchDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new MatchDbHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(MatchEntry.TABLE_NAME, null, values);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(MatchEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
