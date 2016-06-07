package com.example.turingmac.pinball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private int width;
    private int height;
    private final int ballSize = 12;
    private int ySpeed = 10;
    Random rand = new Random();
    private double xyRate = rand.nextDouble() - 0.5;
    private int xSpeed = (int) (ySpeed * xyRate * 2);
    private int x = width / 2;
    private int y = height / 2;
    //private ImageView trans_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        width = display.getWidth();
        height = display.getHeight();
*/
        //trans_image = (ImageView) findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_tween);
        animation.setRepeatCount(Animation.INFINITE);
        Button button = (Button)findViewById(R.id.button);
        button.startAnimation(animation);
/*

    public class GameView extends View {
        public GameView(Context context)
        {
            super(context);
            setFocusable(true);
        }
        public void onDraw(Canvas canvas)
        {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);
            canvas.drawCircle(x, y, ballSize, paint);
        }
 */
    }

}
