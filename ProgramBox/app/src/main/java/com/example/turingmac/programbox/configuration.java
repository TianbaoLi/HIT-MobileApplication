package com.example.turingmac.programbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                Intent intent = new Intent(configuration.this,QQActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
}
