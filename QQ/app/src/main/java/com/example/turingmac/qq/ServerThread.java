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
public class ServerThread implements Runnable{

    Socket socket = null;
    BufferedReader br = null;
    OutputStream os = null;
    Handler sendHandler;
    Handler receiveHandler;
    int port;

    public ServerThread(Handler handler, int port)
    {
        this.sendHandler = handler;
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
                                    String content;
                                    try
                                    {
                                        while((content = br.readLine())!= null)
                                        {
                                            Message msg = new Message();
                                            msg.what = 0x123;
                                            msg.obj = "CLIENT:"+content;
                                            sendHandler.sendMessage(msg);
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();

                            Looper.prepare();
                            receiveHandler = new Handler(){
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
