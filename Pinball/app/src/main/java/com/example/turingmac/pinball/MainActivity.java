package com.example.turingmac.pinball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animationForward = AnimationUtils.loadAnimation(this, R.anim.forward);
        Animation animationReverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
        int duration = 2000;
        int step = 100;
        while(duration > 0)
        {
            animationForward.setDuration(duration);
            image.startAnimation(animationForward);
            animationReverse.setDuration(duration);
            image.startAnimation(animationReverse);
            duration -= step;
        }
    }

}
