package com.ramneet.zombieseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionScreen extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context context) {
        Intent intent = new Intent(context, OptionScreen.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

        createRadioButtons();
        setupSaveSelectedButton();

        int savedValue = getNumZombiesChosen(this);
        Toast.makeText(this, "Saved value: " + savedValue, Toast.LENGTH_SHORT).show();
    }

    private void createRadioButtons() {
        RadioGroup group = findViewById(R.id.radio_group_num_zombies);
        final int[] num_Zombies = getResources().getIntArray(R.array.num_zombies);

        // Create the buttons:
        for (int i = 0; i < num_Zombies.length; i++) {
            final int numZombie = num_Zombies[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.zombies, numZombie));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionScreen.this, "You clicked " + numZombie + " zombies.", Toast.LENGTH_SHORT)
                            .show();

                    saveNumZombiesChosen(numZombie);
                }
            });

            // add to radio group:
            group.addView(button);

            // Select default button:
            if (numZombie == getNumZombiesChosen(this)) {
                button.setChecked(true);
            }
        }

    }

    private void saveNumZombiesChosen(int numZombie) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("numZombiesChosen", numZombie);
        editor.apply();
    }

    public static int getNumZombiesChosen(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        // TODO: change default value
        int defaultZombie = context.getResources().getInteger(R.integer.default_num_zombies);
        return prefs.getInt("numZombiesChosen", defaultZombie);

    }

    private void setupSaveSelectedButton() {
        Button btn = findViewById(R.id.save_selected);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup group = findViewById(R.id.radio_group_num_zombies);
                int idOfSelected = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(idOfSelected);
                String message = radioButton.getText().toString();

                Toast.makeText(OptionScreen.this, "Selected Button's text is: " + message, Toast.LENGTH_SHORT)
                        .show();

                finish();
            }
        });
    }



















}
