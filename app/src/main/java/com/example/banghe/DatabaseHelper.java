package com.example.banghe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "chair.db";
    public String TABLE_NAME = "CHAIR";

    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "cost";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (id integer primary key autoincrement, name text, cost integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertData(String n, String p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); //content values put key and value
        contentValues.put(COL_2,n);
        contentValues.put(COL_3,p);

        db.insert(TABLE_NAME,null,contentValues);
    }
    public Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
