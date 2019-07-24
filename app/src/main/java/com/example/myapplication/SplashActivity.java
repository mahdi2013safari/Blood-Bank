package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {


    private ImageView img;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img = findViewById(R.id.img_splash);
        tv = findViewById(R.id.text_splash);

        Animation splash_animation = AnimationUtils.loadAnimation(this , R.anim.splash_transition);
        img.startAnimation(splash_animation);
        tv.startAnimation(splash_animation);

        final Intent i = new Intent(this , MainActivity.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }

            }
        };

        timer.start();
    }
}
