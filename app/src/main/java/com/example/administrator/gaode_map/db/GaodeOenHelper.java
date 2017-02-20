package com.example.administrator.gaode_map.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/2/16.
 */

public class GaodeOenHelper extends SQLiteOpenHelper {
    /**
     * 用户信息的建表语句
     */
    public static final String CREATE_USER="create table user("
            +"id integer primary key autoincrement,"
            +"account text,"
            +"password text,"
            +"bike_num,"
            +"bike_location,"
            +" available_time)";
    /**
     * 可用自行车信息的建表语句
     */
    public static final String CREATE_BIKE="create table bike("
            +" id integer primary key autoincrement,"
            +"location text,"
            +"used blob,"
            +"bike_num text,"
            +"area text5)";//待使用自行车所在的区域


    public GaodeOenHelper(Context context, String name, SQLiteDatabase.CursorFactory
                          factory,int version){
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db){
          db.execSQL(CREATE_BIKE);
          db.execSQL(CREATE_USER);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}

