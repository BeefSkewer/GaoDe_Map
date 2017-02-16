package com.example.administrator.gaode_map;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
