package com.example.numberguess;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    private ImageView img;
    private TextView text;

    Animation imgAnimation,textAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img=findViewById(R.id.icon);
        text=findViewById(R.id.text1);
        imgAnimation= AnimationUtils.loadAnimation(this,R.anim.image_animation);
        textAnimation=AnimationUtils.loadAnimation(this,R.anim.text_animation);
        img.setAnimation(imgAnimation);
        text.setAnimation(textAnimation);
        new CountDownTimer(5000,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
            }
        }.start();
    }
}