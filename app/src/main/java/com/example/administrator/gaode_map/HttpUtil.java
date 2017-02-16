package com.example.administrator.gaode_map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
/**
 * Created by Administrator on 2017/2/11.
 */

public class HttpUtil {
    public static void sendHttpRequest(final String  address,
                                         final HttpCallbackListener listener){
       new Thread(new Runnable() {
           @Override
           public void run() {

               try{
                   String response;
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpGet httpGet=new HttpGet(address);
                  HttpResponse httpResponse=httpClient.execute(httpGet);
                   if(httpResponse.getStatusLine().getStatusCode()==200){
                       HttpEntity entity=httpResponse.getEntity();
                       response=EntityUtils.toString(entity,"utf-8");
                       if(listener!=null){
                           listener.onFinish(response);
                       }
                   }

               }catch (Exception e){
                   e.printStackTrace();
                   listener.onError(e);
               }
           }
       }).start();


    }
}
