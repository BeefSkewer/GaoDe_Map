package com.example.administrator.gaode_map.model;

/**
 * Created by Administrator on 2017/2/17.
 */

public class User {
    private String account;
    private String password;
    private String bike_num;
    private String available_time;
    private String bike_location;
    private int id;
    public String getAccount(){
        return account;
    }
    public void setAccount(String account){
        this.account=account;
    }
    public String getPassword(){
        return  password;

    }
    public void setPassword(String password){
        this.password=password;

    }
    public String getBike_num(){
        return  bike_num;
    }
    public void setBike_num(String bike_num){
        this.bike_num=bike_num;

    }
    public String getAvailable_time(){
        return available_time;
    }
    public void setAvailable_time(String available_time){
        this.available_time=available_time;
    }
    public String getBike_location(){
        return bike_location;
    }
    public void setBike_location(String bike_location){
        this.bike_location=bike_location;
    }
    public int getId(){
        return  id;
    }
    public void setId(int id){
        this.id=id;
    }
}
