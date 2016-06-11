package com.example.turingmac.baidumap;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class InputLongLatiActivity extends AppCompatActivity {

    EditText editTextInputLong = null;
    EditText editTextInputLati = null;
    Button buttonLocateLangLati = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_long_lati);

        editTextInputLong = (EditText) this.findViewById(R.id.editTextInputLong);
        editTextInputLati = (EditText) this.findViewById(R.id.editTextInputLati);
        buttonLocateLangLati = (Button) this.findViewById(R.id.buttonLocateLongLati);


        buttonLocateLangLati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longitudeStr = editTextInputLong.getText().toString();
                String latitudeStr = editTextInputLati.getText().toString();
                double longitude = Double.parseDouble(longitudeStr);
                double latitude = Double.parseDouble(latitudeStr);

                GeoCoder coder = GeoCoder.newInstance();
                ReverseGeoCodeOption option = new ReverseGeoCodeOption();
                option.location(new LatLng(latitude,longitude));
                coder.reverseGeoCode(option);
                coder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                        Log.i("===", result.getAddress() + result.getBusinessCircle() + result.getAddressDetail() + result.getLocation() + result.getPoiList());
                        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR)
                            Toast.makeText(InputLongLatiActivity.this, "抱歉，未能找到结果", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(InputLongLatiActivity.this, "位置：" + result.getAddress(), Toast.LENGTH_SHORT).show();
                    }
                    // 地理编码查询结果回调函数
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult result) {

                    }
                });
            }

        });
    }

}
