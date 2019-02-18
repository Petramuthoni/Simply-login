package com.example.simplelogin;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Data extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "simple.db";
    public static final String TABLE_NAME = "simpleTable";


    public Data(Context context, Class<login> registrationClass) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE simpleTable (ID INTEGER PRIMARY KEY AUTOINCREMENT ,FirstName TEXT,LastName TEXT ,Password TEXT,Email TEXT,Phone TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists userTable");//DROP OLDER TABLE
        //onCreate(sqLiteDatabase);
    }

    public Boolean insert(String Firstname, String LastName, String Password, String Email, String Phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName", Firstname);
        contentValues.put("LastName", LastName);
        contentValues.put("Password", Password);
        contentValues.put("Email", Email);
        contentValues.put("Phone", Phone);
        long id = sqLiteDatabase.insert(Data.TABLE_NAME, null, contentValues);
        long ins = sqLiteDatabase.insert(Data.TABLE_NAME, null, contentValues);
        if (ins == 1) return false;
        else return true;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from simpleTable where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public Boolean emailpass(String Email, String Password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" select * from simpleTable where Email=? and Password=?", new String[]{Email, Password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
}




