package com.example.administrator.gaode_map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.amap.api.maps.offlinemap.Province;
import com.example.administrator.gaode_map.db.GaodeOenHelper;
import com.example.administrator.gaode_map.model.Bike;
import com.example.administrator.gaode_map.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class GaoDe_MapDB {
    /**
     * 数据库名
     *
     */
    public static final String DB_NAME="GaoDe_Map";
    /**
     * 数据库版本
     */
    public static final int VERSION=1;
    private static GaoDe_MapDB gaoDe_mapDB;
    private SQLiteDatabase db;
    /**
     * 构造方法私有
     */
    private GaoDe_MapDB(Context context){
        GaodeOenHelper dbhelper=new GaodeOenHelper(context,DB_NAME,null
        ,VERSION);
        db=dbhelper.getWritableDatabase();
    }
    /**
     * 获取GaoDe_Map实例
     */
    public synchronized static GaoDe_MapDB getInstance(Context context){
        if(gaoDe_mapDB==null){
            gaoDe_mapDB=new GaoDe_MapDB(context);
        }
        return  gaoDe_mapDB;
    }
    /**
     * 将bike实例存储到数据库
     */
    public void saveBike(Bike bike ){
        if(bike!=null){
           db.execSQL("insert into bike(location,bike_num,area)" +
                   "values(?,?,?)",new String[]{bike.getLocation()
           ,bike.getBike_num(),bike.getArea()});
        }
    }
    /**
     * 从数据库读取bike信息
     */
    public List<Bike> loadBike(String area){
        List<Bike> list=new ArrayList<>();
        Cursor cursor=db.query("bike",null,"used=?",new String[]{"false"},null,null,null);
        if(cursor.moveToFirst()) {
            do {
                Bike bike = new Bike();
                bike.setBike_num(cursor.getString(cursor.getColumnIndex("bike_num")));
                bike.setArea(cursor.getString(cursor.getColumnIndex("area")));
                bike.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                bike.setUsed(cursor.getInt(cursor.getColumnIndex("used")));
            } while (cursor.moveToNext());

        }
    return list;
    }
    public void saveUser(User user){
        if(user!=null){
            db.execSQL("insert into user(account,password,bike_num,available_time,bike_location)" +
                    "values(?,?,?,?,?)",new String[]{user.getAccount(),user.getPassword(),
              user.getBike_num(),user.getAvailable_time(),user.getBike_location()});
        }
    }
}
