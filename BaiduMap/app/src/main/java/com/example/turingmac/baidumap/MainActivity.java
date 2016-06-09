package com.example.turingmac.baidumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.TextureMapView;

public class MainActivity extends AppCompatActivity {

    TextureMapView textureMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        textureMapView = (TextureMapView) findViewById(R.id.textureMapView);
    }
    @Override
    protected void onDestroy() {
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
}
