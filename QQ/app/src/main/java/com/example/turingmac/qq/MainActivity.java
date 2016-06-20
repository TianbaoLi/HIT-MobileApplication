package com.example.turingmac.qq;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private EditText editbox;
    private TextView chatbox;

    private Button send;
    private Button config;
    private Button link;
    private Button server;
    private Button open;
    private Handler handler;
    private String ip;
    private int port;
    private int serverport;




    //定义与服务器通信的子线程
    private ClientThread clientThread;

    private ServiceThreada serverThread;


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.config:
                Intent i = new Intent(MainActivity.this,configuration.class);
                startActivity(i);
                break;
            case R.id.link:
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            //当用户按下按钮后，将输入数据封装为Message
                            //发送给子线程Handler
                            Message msg = new Message();
                            //标签
                            msg.what = 0x345;
                            msg.obj = new Date().toString()+"\n"+ editbox.getText().toString();
                            chatbox.append(new Date().toString()+"\n"+"客户端： "+editbox.getText().toString()+"\n");
                            clientThread.revHandler.sendMessage(msg);
                            editbox.setText("");
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();

                ip = bundle.getString("ip");
                port = Integer.parseInt(bundle.getString("port"));

                clientThread =  new ClientThread(handler,ip,port);
                new Thread(clientThread).start();
                Toast.makeText(MainActivity.this, "连接成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.open:
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            //当用户按下按钮后，将输入数据封装为Message
                            //发送给子线程Handler
                            Message msg = new Message();
                            //标签
                            msg.what = 0x345;
                            msg.obj = new Date().toString()+"\n"+ editbox.getText().toString();
                            chatbox.append(new Date().toString()+"\n"+"服务器端： "+editbox.getText().toString()+"\n");
                            serverThread.revHandler.sendMessage(msg);
                            editbox.setText("");
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

                Intent intent1 = getIntent();
                Bundle bundle1 = intent1.getExtras();

                serverport = Integer.parseInt(bundle1.getString("serverport"));
                serverThread = new ServiceThreada(handler,serverport);
                new Thread(serverThread).start();
                Toast.makeText(MainActivity.this, "服务器开启！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.server:
                Intent i1 = new Intent(MainActivity.this,Server.class);
                startActivity(i1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editbox = (EditText)this.findViewById(R.id.editbox);
        chatbox = (TextView)this.findViewById(R.id.chatbox);
        chatbox.setMovementMethod(ScrollingMovementMethod.getInstance());
        send = (Button)this.findViewById(R.id.send);
       /* config = (Button)this.findViewById(R.id.config);
        link = (Button)this.findViewById(R.id.link);
        server = (Button)this.findViewById(R.id.server);
        open = (Button)this.findViewById(R.id.open);*/


        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                //如果消息来自子线程
                if(msg.what == 0x123) {
                    //将读取的内容显示在文本框中
                    chatbox.append(msg.obj.toString()+"\n");
                }
            }
        };

        /*config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,configuration.class);
                startActivity(i);
            }
        });

        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Server.class);
                startActivity(i);
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            //当用户按下按钮后，将输入数据封装为Message
                            //发送给子线程Handler
                            Message msg = new Message();
                            //标签
                            msg.what = 0x345;
                            msg.obj = new Date().toString()+"\n"+ editbox.getText().toString();
                            chatbox.append(new Date().toString()+"\n"+"服务器端： "+editbox.getText().toString()+"\n");
                            serverThread.revHandler.sendMessage(msg);
                            editbox.setText("");
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();

                serverport = Integer.parseInt(bundle.getString("serverport"));
                serverThread = new ServiceThreada(handler,serverport);
                new Thread(serverThread).start();
                Toast.makeText(MainActivity.this, "服务器开启！", Toast.LENGTH_SHORT).show();
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            //当用户按下按钮后，将输入数据封装为Message
                            //发送给子线程Handler
                            Message msg = new Message();
                            //标签
                            msg.what = 0x345;
                            msg.obj = new Date().toString()+"\n"+ editbox.getText().toString();
                            chatbox.append(new Date().toString()+"\n"+"客户端： "+editbox.getText().toString()+"\n");
                            clientThread.revHandler.sendMessage(msg);
                            editbox.setText("");
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();

                ip = bundle.getString("ip");
                port = Integer.parseInt(bundle.getString("port"));

                clientThread =  new ClientThread(handler,ip,port);
                new Thread(clientThread).start();
                Toast.makeText(MainActivity.this, "连接成功！", Toast.LENGTH_SHORT).show();
            }
        });*/


        }
}
