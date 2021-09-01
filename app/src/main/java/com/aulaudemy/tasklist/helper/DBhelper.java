package com.aulaudemy.tasklist.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static final String DB_NAME = "DB_TASK";
    public static String TABLE_TASKS = "tasks";

    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_TASKS+
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT NOT NULL);";
        try {
            db.execSQL(sql);
            Log.i("INFO.DB", "Success on create "+TABLE_TASKS+" table");
        } catch (Exception e) {
            Log.e("INFO.DB", "Error "+e.getMessage()+" on create "+TABLE_TASKS+" table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_TASKS+";";
        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO.DB", "Success on upgrade app to version: "+newVersion);
        } catch (Exception e) {
            Log.e("INFO DB", "Erro ao atualizar app" +e.getMessage());
        }
    }
}
