package com.example.helpinghands_v02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table userdetails(id TEXT primary key, nombre TEXT, telefono TEXT, direccion TEXT, correo TEXT, contraseña TEXT, edad TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
    }


    public Boolean insertuserdata(String id, String nombre, String telefono, String direccion, String correo, String contraseña, String edad) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("nombre", nombre);
        contentValues.put("telefono", telefono);
        contentValues.put("direccion", direccion);
        contentValues.put("correo", correo);
        contentValues.put("contraseña", contraseña);
        contentValues.put("edad", edad);

        long result = DB.insert("Userdetails", null, contentValues);
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }





    public Boolean updateuserdata(String id, String nombre, String telefono, String direccion, String correo, String contraseña, String edad){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("telefono", telefono);
        contentValues.put("direccion", direccion);
        contentValues.put("correo", correo);
        contentValues.put("contraseña", contraseña);
        contentValues.put("edad", edad);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ? ",new String[]{id});
        if(cursor.getCount()>0){
            long result = DB.update("Userdetails", contentValues, "id=?",new String[]{id});
            if (result==1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }





    public Boolean deletedata(String id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "id=?", new String[]{id});
            if (result==1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
             }
        }



    public Cursor getdata() {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from userdetails", null);
        return cursor;

        }
    }



