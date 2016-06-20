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
public class Server extends Activity{
    private  Button btnOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sever);

        btnOK =(Button)this.findViewById(R.id.confirm);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText serverport = (EditText)findViewById(R.id.serverport);

                Bundle data = new Bundle();
                data.putString("serverport",serverport.getText().toString());

                Intent intent = new Intent(Server.this,MainActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
}
