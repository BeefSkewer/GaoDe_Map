package com.example.administrator.gaode_map;

/**
 * Created by Administrator on 2017/2/11.
 */

public class Adress {
    private double lat;
    private double lon;
    private String user;
    public double getlat(){
        return lat;
    }
    public double getlon(){
        return lon;
    }
    public String getuser(){
        return user;
    }
    public void setLat(String lat){
        this.lat=Double.valueOf(lat).doubleValue();
    }
    public void setLon(String lon){
        this.lon=Double.valueOf(lon).doubleValue();
    }
    public void setUser(String user){
        this.user=user;
    }
}
