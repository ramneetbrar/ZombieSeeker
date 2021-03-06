package com.ramneet.zombieseeker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ramneet.zombieseeker.R;

/**
 *  MainActivity allows the user to navigate to the help, game and options screens. Recieves data
 *  from the OptionScreen and passes it to the GameScreen to update user preferences.
 */
//popup: https://www.youtube.com/watch?v=0DH2tZjJtm0
// MP3 Sound Citation: http://soundbible.com/944-Stab.html

public class MainActivity extends AppCompatActivity {

    private int NUM_ROWS = 4;
    private int NUM_COLS = 6;
    private int NUM_ZOMBIES = 6;
    public static final int REQUEST_CODE_GET_VALUES = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = WelcomeScreen.makeLaunchIntent(MainActivity.this);
        startActivity(i);

        setupLaunchGameScreen();
        setupHelpScreen();
        setupOptionScreen();
        refreshScreen();
    }


    private void setupLaunchGameScreen() {
        Button button = findViewById(R.id.playButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = GameScreen.makeLaunchIntent(MainActivity.this, NUM_ROWS, NUM_COLS, NUM_ZOMBIES);
                startActivity(i);
            }
        });
    }

    private void setupHelpScreen() {
        Button button = findViewById(R.id.helpButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpScreen.class);
                startActivity(intent);
            }
        });
    }

    private void setupOptionScreen() {
        Button button = findViewById(R.id.optionsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OptionScreen.makeLaunchIntent(MainActivity.this);
                startActivityForResult(i, REQUEST_CODE_GET_VALUES);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_GET_VALUES:
                if (resultCode == Activity.RESULT_OK) {
                    int rows = data.getIntExtra("zombieseeker.optionScreen.numRows", 0);
                    int cols = data.getIntExtra("zombieseeker.optionScreen.numCols", 0);
                    int numZombies = data.getIntExtra("zombieseeker.optionScreen.numZombies", 0);

                    NUM_ROWS = rows;
                    NUM_COLS = cols;
                    NUM_ZOMBIES = numZombies;

                }
                else {
                    Log.i("MainActivity", "Activity Cancelled.");
                }
        }
    }

    private void refreshScreen() {
        NUM_ZOMBIES = OptionScreen.getNumZombiesChosen(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
    }
}
