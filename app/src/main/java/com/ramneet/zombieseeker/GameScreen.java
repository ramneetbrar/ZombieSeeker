package com.ramneet.zombieseeker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 6;
    private static final int NUM_ZOMBIES = 6;

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
    Cell gameBoard[][];
    GameLogic gameLogic = new GameLogic(NUM_ROWS, NUM_COLS, NUM_ZOMBIES);


    public static Intent makeLaunchIntent(Context context) {
        Intent intent = new Intent(context, GameScreen.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //gameLogic = new GameLogic(NUM_ROWS, NUM_COLS, NUM_ZOMBIES);



        populateButtons();

    }


    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

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
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void gridButtonClicked(int col, int row) {
        Toast.makeText(this, "Button clicked: " + col + "," + row,
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        gameLogic.updateUserInputInGameBoard(row, col);
        //Cell cell = gameLogic.getCellFromGameBoard(row, col);

//        if (cell.hasZombie() && !cell.isClicked()) {
//            button.setBackgroundResource(R.drawable.zombie_walking);
//            gameLogic.updateScans(cell);
//        }
//        if (!cell.hasZombie() || (cell.hasZombie() && cell.isClicked()) ){
//            gameLogic.scanZombies(cell);
//            int numZombiesInScan = cell.getScanOfZombies();
//            button.setText(numZombiesInScan + "");
//        }

    }

}
