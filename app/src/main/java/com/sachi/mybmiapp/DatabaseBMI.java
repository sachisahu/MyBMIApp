package com.sachi.mybmiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.sachi.mybmiapp.CustomeListAdapters.BMIHistoryCustomListAdapter;
import com.sachi.mybmiapp.DataClass.BMIHistoryDataClass;

import java.util.ArrayList;

public class DatabaseBMI extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "BMIDb.db";
    public static String TABLE_NAME = "BMIData";
    public static String COLUMN_HEIGHT = "height";
    public static String COLUMN_WEIGHT = "weight";
    public static String COLUMN_GENDER = "gender";
    public static String COLUMN_BMI = "bmi";

    public DatabaseBMI(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE BMIData(id integer primary key,height text,weight text,gender text,bmi text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public boolean insertData(String height,String weight,String gender,String bmi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUMN_HEIGHT,height);
        values.put(COLUMN_WEIGHT,weight);
        values.put(COLUMN_GENDER,gender);
        values.put(COLUMN_BMI,bmi);

        db.insert(TABLE_NAME,null,values);

        return true;
    }

    BMIHistoryCustomListAdapter bmiHistoryCustomListAdapter;

    public ArrayList<BMIHistoryDataClass> bmiHistoryDataClasses = new ArrayList<>();

    public void fetchData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from BMIData",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String height =cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT));
            String weight =cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT));
            String gender =cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
            String bmi =cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BMI));
            bmiHistoryDataClasses.add(new BMIHistoryDataClass(""+id,""+height,""+weight,""+gender,""+bmi));
            Log.d("Fetched Data:","ID"+id+" Height : "+height+" Weight : "+weight+" Gender : "+gender+" BMI : "+bmi);
            cursor.moveToNext();
        }
    }
}
