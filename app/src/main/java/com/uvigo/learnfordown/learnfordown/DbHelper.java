package com.uvigo.learnfordown.learnfordown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juani on 03/02/2017.
 */

public class DbHelper  extends SQLiteOpenHelper  {
    private static final String DB_learn = "learn.sqlite";
    private static final int DB_SCHEME_VERSION=3;
    public DbHelper(Context context) {
        super(context, DB_learn, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println(DataBaseManager.CREATE_TABLE_USER);
        System.out.println(DataBaseManager.CREATE_TABLE_LEVEL);
        System.out.println(DataBaseManager.CREATE_TABLE_WORD);
        System.out.println(DataBaseManager.CREATE_TABLE_AFFINITY);
        System.out.println(DataBaseManager.CREATE_TABLE_NIVELUSER);
        System.out.println(DataBaseManager.CREATE_TABLE_SYSTEM);
        System.out.println(DataBaseManager.CREATE_TABLE_STARS);
        System.out.println(DataBaseManager.CREATE_TABLE_GAMES);

        /*
        db.execSQL("DROP TABLE IF EXITS "+DataBaseManager.TABLE_LEVEL_USER);
        db.execSQL("DROP TABLE IF EXITS "+DataBaseManager.TABLE_AFFINITY);
        db.execSQL("DROP TABLE IF EXITS "+DataBaseManager.TABLE_SYSTEM_LOG);
        db.execSQL("DROP TABLE IF EXITS "+DataBaseManager.TABLE_USER);
        db.execSQL("DROP TABLE IF EXITS "+DataBaseManager.TABLE_WORD);
        db.execSQL("DROP TABLE IF EXITS "+DataBaseManager.TABLE_LEVEL);
        */
        db.execSQL(DataBaseManager.CREATE_TABLE_USER);
        db.execSQL(DataBaseManager.CREATE_TABLE_LEVEL);
        db.execSQL(DataBaseManager.CREATE_TABLE_WORD);
        db.execSQL(DataBaseManager.CREATE_TABLE_AFFINITY);
        db.execSQL(DataBaseManager.CREATE_TABLE_NIVELUSER);
        db.execSQL(DataBaseManager.CREATE_TABLE_SYSTEM);
        db.execSQL(DataBaseManager.CREATE_TABLE_STARS);
        db.execSQL(DataBaseManager.CREATE_TABLE_GAMES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
