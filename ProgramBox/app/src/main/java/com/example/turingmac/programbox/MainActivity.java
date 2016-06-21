package com.example.turingmac.programbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, PinballActivity.class);
                        startActivity(intent0);
                        break;
                    /*case 1:
                        Intent intent1 = new Intent(MainActivity.this,playball.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this,chat.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, Maps.class);
                        startActivity(intent3);
                        break;*/
                }
            }
        });

    }
}
