package com.example.turingmac.programbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.List;

public class BaiduMapActivity extends AppCompatActivity {


    TextureMapView textureMapView = null;
    BaiduMap baiduMap = null;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    Button buttonLongLati = null;
    Button buttonDescription = null;
    Button buttonCurrent = null;
    MyLocationConfiguration.LocationMode mCurrentMode = null;
    double longitude;
    double latitude;
    BitmapDescriptor bitmap = null;
    OnGetGeoCoderResultListener onGetGeoCoderResultListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(myListener);
        SDKInitializer.initialize(context);

        setContentView(R.layout.activity_baidu_map);
        textureMapView = (TextureMapView) findViewById(R.id.textureMapView);
        buttonLongLati = (Button) findViewById(R.id.buttonLongLati);
        buttonDescription = (Button) findViewById(R.id.buttonDescription);
        buttonCurrent = (Button) findViewById(R.id.buttonCurrent);
        baiduMap = textureMapView.getMap();
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        onGetGeoCoderResultListener = new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                Log.i("!!!!!!", "" + geoCodeResult.toString());
                if(geoCodeResult == null||geoCodeResult.error!= SearchResult.ERRORNO.NO_ERROR)
                {
                    Toast.makeText(BaiduMapActivity.this, "抱歉，未能找到结果",Toast.LENGTH_LONG).show();
                }
                else
                {
                    LatLng lat = geoCodeResult.getLocation();
                    MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(lat);
                    baiduMap.animateMapStatus(msu);
                    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(lat)
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                    baiduMap.addOverlay(option);
                }

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        };
        initLocation();

        try
        {
            Intent intent = getIntent();
            Bundle data = intent.getExtras();
            try
            {
                Log.i("===", data.getString("POS"));
                String[] aa = data.getString("POS").split(" ");
                latitude = Double.parseDouble(aa[0]);
                longitude = Double.parseDouble(aa[1]);

                LatLng point = new LatLng(latitude, longitude);
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(point);
                baiduMap.animateMapStatus(msu);
                OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
                baiduMap.addOverlay(option);
            }
            catch(Exception e)
           {

            }
            try
            {
                String s = data.getString("DES");
                Log.i("===========DES:", s);
                GeoCoder geoCoder = GeoCoder.newInstance();
                geoCoder.setOnGetGeoCodeResultListener(onGetGeoCoderResultListener);
                geoCoder.geocode(new GeoCodeOption().address(s));
            }
            catch(Exception e)
            {
                try {
                    String s = data.getString("DES");
                    GeoCoder geoCoder = GeoCoder.newInstance();
                    geoCoder.setOnGetGeoCodeResultListener(onGetGeoCoderResultListener);
                    Log.i("===========DES:", s);
                    geoCoder.geocode(new GeoCodeOption().city("").address(s));
                    Log.i("===========DES:", s);
                } catch (Exception Ee) {

                }
            }
            }
            catch(Exception e)
            {

            }
            buttonLongLati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(BaiduMapActivity.this, InputLongLatiActivity.class);
                    startActivity(intent);
                }
            });

            buttonDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(BaiduMapActivity.this, InputDescriptionActivity.class);
                    startActivity(intent);
                }
            });

            buttonCurrent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    mLocationClient.start();
                    mLocationClient.requestLocation();
                }
            });
            //Toast.makeText(MainActivity.this, "" + myListener.,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onDestroy() {
            mLocationClient.stop();
            super.onDestroy();
            textureMapView.onDestroy();
        }
        @Override
        protected void onResume() {
            super.onResume();
            textureMapView.onResume();
        }
        @Override
        protected void onPause() {
            super.onPause();
            textureMapView.onPause();
        }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(true);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(true);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：公里每小时
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    sb.append("\ndirection : ");
                    sb.append(location.getDirection());// 单位度
                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());
                    //运营商信息
                            sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                    sb.append("\npoilist size = : ");
                    sb.append(list.size());
                    for (Poi p : list) {
                            sb.append("\npoi= : ");
                            sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                        }
                }
            Log.i("BaiduLocationApiDem", sb.toString());
                    LatLng lat = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(lat);
            baiduMap.animateMapStatus(msu);
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
            //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(lat)
                            .icon(bitmap);
            //在地图上添加Marker，并显示
                    baiduMap.addOverlay(option);
            }
        }
}