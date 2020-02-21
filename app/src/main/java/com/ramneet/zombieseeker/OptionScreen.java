package com.ramneet.zombieseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

        createRadioButtonsNumZombies();
        createRadioButtonsBoardSize();
        setupSaveSelectedButton();

        int savedZombie = getNumZombiesChosen(this);
        String savedBoardSize = getBoardSizeChosen(this);

        Toast.makeText(this, "Saved Zombie: " + savedZombie, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Saved Board Size: " + savedBoardSize, Toast.LENGTH_SHORT).show();
    }

    private void createRadioButtonsNumZombies() {
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

    private void createRadioButtonsBoardSize() {
        RadioGroup group = findViewById(R.id.radio_group_board_size);
        final String[] board_sizes = getResources().getStringArray(R.array.board_size);

        // Create the buttons:
        for (int i = 0; i < board_sizes.length; i++ ) {
            final String board_size = board_sizes[i];

            RadioButton button = new RadioButton(this);
            button.setText(board_size);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionScreen.this, "You clicked " + board_size + " board size.", Toast.LENGTH_SHORT)
                            .show();
                    saveBoardSizeChosen(board_size);
                }
            });

            // add to radio group:
            group.addView(button);

            // Select default button
            if (board_size.equals(getBoardSizeChosen(this))) {
                button.setChecked(true);
            }
        }
    }


    private void saveNumZombiesChosen(int numZombie) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefsZombie", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("numZombiesChosen", numZombie);
        editor.apply();
    }

    private void saveBoardSizeChosen(String board_size) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefsBoard", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("boardSize", board_size);
        editor.apply();
    }

    public static int getNumZombiesChosen(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefsZombie", MODE_PRIVATE);
        int defaultZombie = context.getResources().getInteger(R.integer.default_num_zombies);
        return prefs.getInt("numZombiesChosen", defaultZombie);

    }

    public static String getBoardSizeChosen(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefsBoard", MODE_PRIVATE);
        String defaultBoardSize = context.getResources().getString(R.string.default_board_size);
        return prefs.getString("boardSize", defaultBoardSize);
    }

    private void setupSaveSelectedButton() {
        Button btn = findViewById(R.id.save_selected);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup group_num_zombies = findViewById(R.id.radio_group_num_zombies);
                int idOfSelectedZombie = group_num_zombies.getCheckedRadioButtonId();
                RadioButton radioButtonZombie = findViewById(idOfSelectedZombie);
                String numZombies = radioButtonZombie.getText().toString();

                RadioGroup group_board_size = findViewById(R.id.radio_group_board_size);
                int idOfSelectedBoardSize = group_board_size.getCheckedRadioButtonId();
                RadioButton radioButtonBoardSize = findViewById(idOfSelectedBoardSize);
                String boardSize = radioButtonBoardSize.getText().toString();
                int rows = getRowFromBoardSize(boardSize);
                int cols = getColFromBoardSize(boardSize);

                Toast.makeText(OptionScreen.this, "NumZombies are: " + numZombies, Toast.LENGTH_SHORT)
                        .show();

                Toast.makeText(OptionScreen.this, "Rows are: " + rows + " Cols are: " + cols, Toast.LENGTH_SHORT)
                        .show();

                // Extract Data from UI:

                //Pass data back:
                Intent intent = new Intent();
                intent.putExtra("zombieseeker.optionScreen.numRows", rows);
                intent.putExtra("zombieseeker.optionScreen.numCols", cols);
                intent.putExtra("zombieseeker.optionScreen.numZombies", numZombies);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private int getRowFromBoardSize(String boardSize) {
        char charRow = boardSize.charAt(0);
        int row = Character.getNumericValue(charRow);
        return row;
    }

    private int getColFromBoardSize(String boardSize) {
        if (boardSize.length() == 5) {
            char charCol = boardSize.charAt(4);
            int col = Character.getNumericValue(charCol);
            return col;
        }
        char[] boardSizeArray = boardSize.toCharArray();
        String charCol = "" + boardSizeArray[4] + boardSizeArray[5];
        int col = Integer.valueOf(charCol);
        return col;
    }

}
