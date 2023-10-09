package com.example.demoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.demoapp.model.UserModel;
import com.example.demoapp.params.Params;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table if not exists user_table (id integer primary key, name text, number text, email text, password text, dt text)";
        String query = "create table if not exists notes (id integer primary key, msg text, dt text)";
        sqLiteDatabase.execSQL(qry);
        sqLiteDatabase.execSQL(query);
    }

    public void addUser(UserModel userModel) {
        SQLiteDatabase dbs = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(Params.COL_NAME, userModel.getName());
        values1.put(Params.COL_PHONE_NO, userModel.getNumber());
        values1.put(Params.COL_EMAIL, userModel.getEmail());
        values1.put(Params.COL_PASSWORD, userModel.getPassword());
        values1.put(Params.COL_DT, userModel.getDt());
        dbs.insert("user_table",null, values1);
    }

    public void addNotes(String msg, String dt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.COL_MSG, msg);
        values.put(Params.COL_DT, dt);
        db.insert("notes", null, values);
    }

    public List<UserModel> getAllUser(){
        List<UserModel> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "select * from user_table";
        Cursor cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()){
            do{
                UserModel userModel= new UserModel("","","","","");
                userModel.setName(cursor.getString(1));
                userModel.setNumber(cursor.getString(2));
                userModel.setEmail(cursor.getString(3));
                userModel.setPassword(cursor.getString(4));
                userModel.setDt(cursor.getString(5));
                userList.add(userModel);
            }while (cursor.moveToNext());
        }
        return  userList;
    }

    public int update(int id, String msg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.COL_MSG, msg);
        return db.update("notes", contentValues, Params.COL_ID + "=?",
                new String[]{String.valueOf(id)});
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
