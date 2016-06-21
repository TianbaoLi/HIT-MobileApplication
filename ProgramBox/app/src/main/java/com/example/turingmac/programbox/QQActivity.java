package com.example.turingmac.programbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class QQActivity extends AppCompatActivity {

    private EditText editTextInput;
    private TextView textViewShow;
    private Button buttonSend;
    private Button buttonSetConfig;
    private Button buttonLink;
    private Button buttonStartServer;
    private Button buttonSetServerPort;

    private Handler handler;
    private String ip;
    private int port;
    private int serverPort;
    private com.example.turingmac.programbox.ClientThread clientThread;
    private com.example.turingmac.programbox.ServerThread serverThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);

        editTextInput = (EditText)this.findViewById(R.id.editTextInput);
        textViewShow = (TextView)this.findViewById(R.id.textViewShow);
        textViewShow.setMovementMethod(ScrollingMovementMethod.getInstance());
        buttonSend = (Button)this.findViewById(R.id.buttonSend);

        buttonSetConfig = (Button)this.findViewById(R.id.buttonSetConfig);
        buttonLink = (Button)this.findViewById(R.id.buttonLink);
        buttonStartServer = (Button)this.findViewById(R.id.buttonStartServer);
        buttonSetServerPort = (Button)this.findViewById(R.id.buttonSetServerPort);

        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 0x123) {
                    textViewShow.append(msg.obj.toString()+"\n");
                }
            }
        };

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = new Date().toString()+"\n"+ editTextInput.getText().toString();
                    textViewShow.append(new Date().toString()+"\n"+"SERVER:"+ editTextInput.getText().toString()+"\n");
                    try
                    {
                        clientThread.receiveHandler.sendMessage(msg);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    editTextInput.setText("");
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        buttonSetConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSetConfig = new Intent(QQActivity.this, com.example.turingmac.programbox.configuration.class);
                startActivity(intentSetConfig);
            }
        });

        buttonSetServerPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSetServerPort = new Intent(QQActivity.this, com.example.turingmac.programbox.ServerPortInput.class);
                startActivity(intentSetServerPort);
            }
        });

        buttonStartServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intentStartServer = getIntent();
                    Bundle bundleStartServer = intentStartServer.getExtras();

                    serverPort = Integer.parseInt(bundleStartServer.getString("serverPort"));
                    serverThread = new com.example.turingmac.programbox.ServerThread(handler,serverPort);
                    new Thread(serverThread).start();
                    Toast.makeText(QQActivity.this, "SERVER START", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        buttonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLink = getIntent();
                Bundle bundleLink = intentLink.getExtras();
                ip = bundleLink.getString("ip");
                port = Integer.parseInt(bundleLink.getString("port"));

                clientThread =  new com.example.turingmac.programbox.ClientThread(handler,ip,port);
                new Thread(clientThread).start();
                Toast.makeText(QQActivity.this, "CONNECTION BUILT", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
