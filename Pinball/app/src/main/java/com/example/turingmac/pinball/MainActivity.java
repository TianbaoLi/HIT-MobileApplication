package com.example.turingmac.pinball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity{

    private int duration = 2000;
    private final int step = 200;
    private final int ballSize = 30;
    private int height;
    private int width;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        image = (ImageView) findViewById(R.id.imageView);
        image.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                if(duration <= 0)
                    duration = 2000;
                TranslateAnimation animForward = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.5f * (height - 3 * ballSize - 15) / height);
                animForward.setDuration(duration);
                animForward.setInterpolator(new AccelerateInterpolator());
                animForward.setAnimationListener(new myAnimationListener());
                image.startAnimation(animForward);
            }
        });
    }

    private class myAnimationListener implements Animation.AnimationListener
    {
        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            TranslateAnimation animReverse = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.5f * (height - 3 * ballSize - 15) / height,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            animReverse.setDuration(duration);
            animReverse.setInterpolator(new DecelerateInterpolator());
            image.startAnimation(animReverse);
            duration -= step;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    }
}
