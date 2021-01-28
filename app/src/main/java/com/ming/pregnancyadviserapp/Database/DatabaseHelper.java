package com.ming.pregnancyadviserapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "PregnancyAdviserAppDatabase.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table basicData(id integer primary key, " +
                "dateType varchar(20), " +
                "mainDate varchar(20), " +
                "mh varchar(100)," +
                "ph integer, " +
                "height integer, " +
                "weight integer ," +
                "BMI varchar(7),"+
                "hypertension varchar(7)," +
                "diabetes varchar(7)," +
                "firstName varchar(30)," +
                "lastName varchar(30))"
        );

        db.execSQL("create table pHistory(id integer," +
                "pNumbers integer," +
                "DeliveryDate varchar(20),"+
                "wayDelivery varchar(20)," +
                "pree varchar(10)," +
                "ba varchar(10)," +
                "bf37 varchar(10)," +
                "primary key(id,pNumbers))");

        db.execSQL("create table hypertension (id integer primary key," +
                "highbp integer, lowbp integer, longBPMedication varchar(20)," +
                "longPrepregnancyMedication varchar(20),longCurrentMedication varchar(20),time varchar(30))");

        db.execSQL("create table Diabetes (id integer primary key, " +
                "type varchar(10), years integer, fGlucose integer, onehrglucose integer," +
                "twohrglucose integer, increaseInsulin integer,time varchar(30))");

        db.execSQL("create table addVisit (id integer, visitNumber integer, visitType varchar(20),visitDate varchar(20),visitTime varchar(20)," +
                "primary key (id,visitNumber))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
