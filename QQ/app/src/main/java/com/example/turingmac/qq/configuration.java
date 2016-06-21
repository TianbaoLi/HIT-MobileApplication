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

        Button buttonSubmitConfig = (Button)findViewById(R.id.buttonSubmitConfig);
        buttonSubmitConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextIP = (EditText)findViewById(R.id.editTextIP);
                EditText editTextPort = (EditText)findViewById(R.id.editTextPort);

                Bundle data = new Bundle();
                data.putString("ip",editTextIP.getText().toString());
                data.putString("port",editTextPort.getText().toString());

                Intent intent = new Intent(configuration.this,MainActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
}
