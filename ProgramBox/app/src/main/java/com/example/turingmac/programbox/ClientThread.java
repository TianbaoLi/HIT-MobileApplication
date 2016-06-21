package com.example.turingmac.programbox;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by turingmac on 2016/6/5.
 */
public class ClientThread implements Runnable {
    private Socket socket;
    private String ip;
    private int port;

    Handler sendHandler;
    Handler receiveHandler;

    BufferedReader br = null;
    OutputStream os = null;

    public ClientThread(Handler handler,String IP,int port)
    {
        this.sendHandler = handler;
        this.ip = IP;
        this.port = port;
    }

    @Override
    public void run() {

        socket = new Socket();
        try{
            socket.connect(new InetSocketAddress(ip,port),5000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = socket.getOutputStream();

            new Thread()
            {
                @Override
                public void run() {
                    String content;
                    try{
                        while((content = br.readLine())!=null)
                        {
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = "SERVER:"+content;
                            sendHandler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            Looper.prepare();
            receiveHandler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what == 0x345)
                        try{
                            os.write((msg.obj.toString()+"\r\n").getBytes("utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            };
            Looper.loop();
        }catch (SocketTimeoutException e)
        {
            Message msg = new Message();
            msg.what = 0x123;
            msg.obj = "TIMEOUT";
            sendHandler.sendMessage(msg);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
