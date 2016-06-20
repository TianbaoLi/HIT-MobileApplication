package com.example.turingmac.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by turingmac on 2016/6/5.
 */
public class configuration extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        Button btnOk = (Button)findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ip = (EditText)findViewById(R.id.ip);
                EditText port = (EditText)findViewById(R.id.port);


                Bundle data = new Bundle();


                    data.putString("ip",ip.getText().toString());
                    data.putString("port",port.getText().toString());




                Intent intent = new Intent(configuration.this,MainActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
}
