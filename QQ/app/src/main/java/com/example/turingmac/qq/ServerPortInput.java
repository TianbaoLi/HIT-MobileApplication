package com.example.turingmac.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by turingmac on 2016/6/9.
 */
public class ServerPortInput extends Activity{
    private  Button buttonInputServerPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sever_port_input);

        buttonInputServerPort =(Button)this.findViewById(R.id.buttonInputServerPort);

        buttonInputServerPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextServerPort = (EditText)findViewById(R.id.editTextServerPort);

                Bundle data = new Bundle();
                data.putString("serverPort",editTextServerPort.getText().toString());

                Intent intent = new Intent(ServerPortInput.this,MainActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
}
