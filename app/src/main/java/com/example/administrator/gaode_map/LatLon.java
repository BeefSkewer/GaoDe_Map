package com.example.administrator.gaode_map;

/**
 * Created by Administrator on 2017/2/11.
 */

/**
 * 用于把String型的坐标数据转换成double
 */
public class LatLon {
    private String Poin;
   private String[] LatLonPoint;
    LatLon(String poin){
        this.Poin=poin;

    }
    //返回一个横坐标
    public double getLat(){
        LatLonPoint=Poin.split(",");
        double Lat;
        Lat=Double.valueOf(LatLonPoint[0]).doubleValue();
        return Lat;

    }
    //返回一个纵坐标
    public double getLon(){
        LatLonPoint=Poin.split(",");
        double Lon;
        Lon=Double.valueOf(LatLonPoint[1]).doubleValue();
        return  Lon;
    }
}
