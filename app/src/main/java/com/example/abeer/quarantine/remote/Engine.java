package com.example.abeer.quarantine.remote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class Engine extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "plantqur_ver.db";
    private static final int DATABASE_VERSION = 1;


    public Engine(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
