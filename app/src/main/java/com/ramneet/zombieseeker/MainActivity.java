package com.ramneet.zombieseeker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 5;

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLaunchGameScreen();
        setupMainMenu();
    }

    private void setupLaunchGameScreen() {
        Button button = findViewById(R.id.buttonLaunchGameScreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = GameScreen.makeLaunchIntent(MainActivity.this);
                startActivity(i);
            }
        });
    }

    private void setupMainMenu() {
        Button button = findViewById(R.id.buttonMainMenu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainMenu = new Intent(MainActivity.this, MainMenu.class);
                startActivity(openMainMenu);
            }
        });
    }

}
