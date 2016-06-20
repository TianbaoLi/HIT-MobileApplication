package com.example.turingmac.qq;

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

import android.os.Handler;

/**
 * Created by turingmac on 2016/6/5.
 */
public class ClientThread implements Runnable {
    private Socket s;
    public String IP;
    public int port;

    //定义向UI线程发送消息的Handle对象
    Handler handler;

    //定义向接受UI线程的Handle对象
    Handler revHandler;

    //该线程处理Socket所用的输入输出流
    BufferedReader br = null;
    OutputStream os = null;

    public ClientThread(Handler handler,String IP,int port)
    {
        this.handler = handler;
        this.IP = IP;
        this.port = port;
    }

    @Override
    public void run() {

        s=new Socket();
        try{
            s.connect(new InetSocketAddress(IP,port),5000);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = s.getOutputStream();

            //启动一条子线程来读取服务器相应的数据
            new Thread()
            {
                @Override
                public void run() {
                    String content = null;
                    try{
                        while((content = br.readLine())!=null)
                        {
                            //每当读取到来自服务器的数据之后发送的消息通知程序
                            //界面显示该数据
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = "服务器端："+content;
                            handler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            //为当前线程初始化Looper
            Looper.prepare();
            //创建revHandler对象

            revHandler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    //接受到UI线程中的用户输入的数据
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

            //启动Looper
            Looper.loop();

        }catch (SocketTimeoutException e)
        {
            Message msg = new Message();
            msg.what = 0x123;
            msg.obj = "网络连接超时！";
            handler.sendMessage(msg);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
