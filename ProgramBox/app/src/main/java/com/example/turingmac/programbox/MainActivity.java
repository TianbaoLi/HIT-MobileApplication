package com.example.turingmac.programbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<HashMap<String,Object>> items;

    void InitImageName()
    {
        items = new ArrayList<HashMap<String, Object>>();
        int[] images = {
                R.drawable.ball,
                R.drawable.calculator,
                R.drawable.map,
                R.drawable.qq
        };
        String[] names = {
                "弹球",
                "计算器",
                "地图",
                "即时聊天"
        };
        for(int i=0;i<4;i++)
        {
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("image",images[i]);
            map.put("name",names[i]);
            items.add(map);
        }
        SimpleAdapter imageItems = new SimpleAdapter(this, items, R.layout.grid_items, new String[]{"image", "name"}, new int[]{R.id.itemImage, R.id.itemText});
        gridView.setAdapter(imageItems);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gridView);
        InitImageName();

    }
}
