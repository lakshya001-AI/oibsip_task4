package com.example.quizgame;

// SPLASH SCREEN ACTIVITY WITH THE ANIMATION //

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imagesplashscreen;

    Animation animation;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesplashscreen = findViewById(R.id.splashscreenview);

        animation = AnimationUtils.loadAnimation(this,R.anim.side_slide);

        imagesplashscreen.setAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this,loginactivity.class);
                startActivity(i);
                finish();

            }
        },10000);






    }
}