package com.example.administrator.gaode_map;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationSource,
        AMapLocationListener,View.OnClickListener{
    private AMap aMap;
    private MapView mapView;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private TextView mLocationErrText;
    private EditText editText;
    private Button search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mapView=(MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
       init();
       activate(mListener);

    }
    private void init(){
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
        mLocationErrText=(TextView)findViewById(R.id.textview);
        mLocationErrText.setVisibility(View.GONE);

        search=(Button)findViewById(R.id.search);
        search.setOnClickListener(this);




    }

    //点击搜索地点
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                mlocationClient.stopLocation();
                final Marker marker1 = aMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.102589, 109.597932)).title("剩余1台"));
                final Marker marker2 = aMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.102599, 109.595932)).title("剩余2台"));
                final Marker marker3 = aMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.102689, 109.599932)).title("剩余5台"));
                final Marker marker4 = aMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.101589, 109.577932)).title("剩余3台"));
                marker1.showInfoWindow();
                marker2.showInfoWindow();
                marker3.showInfoWindow();
                marker4.showInfoWindow();


                aMap.animateCamera(new CameraUpdateFactory().changeLatLng(new LatLng(
                        23.102589, 109.597932
                )));
                break;

        }
    }
    private void setUpMap(){
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);

    }

    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(null != mlocationClient){
            mlocationClient.onDestroy();
            deactivate();
        }
    }

    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mLocationErrText.setVisibility(View.GONE);
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
                mLocationErrText.setVisibility(View.VISIBLE);
                mLocationErrText.setText(errText);
            }
        }
    }
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

}
