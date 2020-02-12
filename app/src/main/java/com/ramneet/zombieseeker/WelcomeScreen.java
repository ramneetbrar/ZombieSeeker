package com.ramneet.zombieseeker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import pl.droidsonroids.gif.GifImageView;

public class WelcomeScreen extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context context) {
        Intent intent = new Intent(context, WelcomeScreen.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        // Fade-in animation tutorial: https://www.youtube.com/watch?v=5HmX9bwiFII
        //Animation from https://i.pinimg.com/originals/5c/cf/da/5ccfdaddef13b6dcd7122f20e6f551f1.gif
        GifImageView zombieGIF = findViewById(R.id.zombieImageGif);
        Animation animationIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        zombieGIF.startAnimation(animationIn);

        setupSkipButton();
    }

    private void setupSkipButton() {
        Button skipBtn = findViewById(R.id.btnSkipWelcome);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(1, android.R.anim.fade_out);

            }
        });
    }
}
