package com.example.turingmac.qq;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by turingmac on 2016/6/5.
 */
public class ServiceThreada implements Runnable{

    //定义当前线程处理的Socket
    Socket socket = null;
    //该线程所处理的Socket所对应的输入流
    BufferedReader br = null;
    OutputStream os = null;

    Handler handler;
    Handler revHandler;

    String ip;
    int port;

    public ServiceThreada(Handler handler,int port)
    {
        this.handler = handler;
        this.port = port;
    }


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            boolean flag = true;
            while(flag)
            {
                socket = serverSocket.accept();
                new Thread()
                {
                    @Override
                    public void run() {
                        try
                        {
                            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            os = socket.getOutputStream();

                            new Thread()
                            {
                                @Override
                                public void run() {
                                    String content = null;
                                    //不断读取Socket输入流的内容
                                    try
                                    {
                                        while((content = br.readLine())!= null)
                                        {
                                            Message msg = new Message();
                                            msg.what = 0x123;
                                            msg.obj = "客户端："+content;
                                            handler.sendMessage(msg);
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();

                            Looper.prepare();
                            revHandler = new Handler(){
                                @Override
                                public void handleMessage(Message msg) {
                                    if(msg.what == 0x345)
                                    {
                                        try{
                                            os.write((msg.obj.toString()+"\r\n").getBytes("utf-8"));
                                        }catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            };
                            Looper.loop();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
