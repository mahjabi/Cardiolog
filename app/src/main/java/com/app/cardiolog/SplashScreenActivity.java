package com.app.cardiolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
/**
 * this class implements the splash screen
 */
public class SplashScreenActivity extends AppCompatActivity {
    /**
     * this is used for generating a delay and declaring animation
     */
    Animation leftanim,rightanim;
    ImageView logo,logotext;
    private static int SPLASH_SCREEN = 3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * implements the animation of splash screen
         */
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        leftanim= AnimationUtils.loadAnimation(this
                ,R.anim.left_right_animation);
        rightanim= AnimationUtils.loadAnimation(this ,R.anim.right_left_animation);
        logo=findViewById(R.id.logo);
        logotext=findViewById(R.id.logotext);
        logo.setAnimation(rightanim);
        logotext.setAnimation(leftanim);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               /**
                * this method is for going to main activity from the splash screen
                */
               Intent intent =new Intent(SplashScreenActivity.this,MainActivity.class);
               startActivity(intent);
               finish();
           }
       },SPLASH_SCREEN);

    }
}