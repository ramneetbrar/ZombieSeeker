package com.ramneet.zombieseeker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.ramneet.zombieseeker.R;
import pl.droidsonroids.gif.GifImageView;

/**
 *  WelcomeScreen displays an animation to welcome the user to the application.
 */

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
