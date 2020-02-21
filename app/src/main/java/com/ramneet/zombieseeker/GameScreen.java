package com.ramneet.zombieseeker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 6;
    private static final int NUM_ZOMBIES = 6;
    private static final String TAG = "GameScreen";

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
    GameLogic gameLogic;

    public static Intent makeLaunchIntent(Context context) {
        Intent intent = new Intent(context, GameScreen.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        gameLogic = new GameLogic(NUM_ROWS, NUM_COLS, NUM_ZOMBIES);
        gameLogic.initializeGameBoard(NUM_ROWS, NUM_COLS, NUM_ZOMBIES);
        gameLogic.setupZombies();
        populateButtons();
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                //button.setBackgroundResource(R.drawable.zombie_walking);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                // Make text not clip on small buttons
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(TAG, "After button clicked, in setOnClickListener");

                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Log.e(TAG, "After button clicked, in gridButton method");
        Toast.makeText(this, "Button clicked: " + col + "," + row,
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        Cell cell = gameLogic.getCellFromGameBoard(row,col);
        if (cell.hasZombie() && !cell.isClicked()) {
            button.setBackgroundResource(R.drawable.zombie_walking);
            gameLogic.updateScans(cell);
        }
        if (!cell.hasZombie() || (cell.hasZombie() && cell.isClicked()) ){
            gameLogic.scanZombies(cell);
            int numZombiesInScan = cell.getScanOfZombies();
            button.setText(numZombiesInScan + "");
        }
    }
}
