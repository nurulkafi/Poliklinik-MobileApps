package com.kadazi.poliklinikapps.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "poliklinik.db";
    private static final int DATABASE_VERSION = 5;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table login(id integer primary key, email text ,password text,pasien_id text);";
        db.execSQL(sql);
        Log.d("Data2", "onCreate: " + sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
