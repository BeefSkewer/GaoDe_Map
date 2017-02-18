package com.example.administrator.gaode_map.model;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Bike {
    private String bike_num;
    private String area;
    private String location;
    private int id;
    private int used;
    public String getBike_num(){
        return  bike_num;
    }
    public String getArea(){
        return  area;
    }
    public String getLocation(){
        return location;
    }
    public void setBike_num(String bke_num){
        this.bike_num=bike_num;
    }
    public void setArea(String area){
        this.area=area;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public int getId(){
        return  id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getUsed(){
        return used;
    }
    public void setUsed(int used){
        this.used=used;
    }
}
