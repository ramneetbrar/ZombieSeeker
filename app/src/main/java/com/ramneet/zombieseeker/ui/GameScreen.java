package com.ramneet.zombieseeker.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ramneet.zombieseeker.R;
import com.ramneet.zombieseeker.model.Cell;
import com.ramneet.zombieseeker.model.GameLogic;
import com.ramneet.zombieseeker.model.MyBounceInterpolator;

/**
 * GameScreen activity links with the GameLogic class to create the game board and interacts with
 * the user.
 */
public class GameScreen extends AppCompatActivity {

    private static final String EXTRA_NUM_ROWS = "zombieseeker.gameScreen.numRows";
    private static final String EXTRA_NUM_COLS = "zombieseeker.gameScreen.numCols";
    private static final String EXTRA_NUM_ZOMBIES = "zombieseeker.gameScreen.numZombies";
    private int num_rows;
    private int num_cols;
    private int num_zombies;
    private int num_zombies_found = 0;
    private int num_scans = 0;
    Button[][] buttons;
    GameLogic gameLogic;
    Dialog dialog;

    public static Intent makeLaunchIntent(Context context, int rows, int cols, int numZombies) {
        Intent intent = new Intent(context, GameScreen.class);
        intent.putExtra(EXTRA_NUM_ROWS, rows);
        intent.putExtra(EXTRA_NUM_COLS, cols);
        intent.putExtra(EXTRA_NUM_ZOMBIES, numZombies);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        dialog = new Dialog(this);

        extractDataFromIntent();
        buttons = new Button[num_rows][num_cols];

        gameLogic = new GameLogic(num_rows, num_cols, num_zombies);
        gameLogic.initializeGameBoard(num_rows, num_cols);
        populateButtons();
        setupCountTextDisplays();
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        num_rows = intent.getIntExtra(EXTRA_NUM_ROWS, 0);
        num_cols = intent.getIntExtra(EXTRA_NUM_COLS, 0);
        num_zombies = intent.getIntExtra(EXTRA_NUM_ZOMBIES, 0);
    }

    public void showDialog(){
        TextView close;
        dialog.setContentView(R.layout.congrats_pop_up);
        close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

        for (int row = 0; row < num_rows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < num_cols; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                // Make text not clip on small buttons
                button.setPadding(0, 0, 0, 0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                        setupCountTextDisplays();
                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Button button = buttons[row][col];

        lockButtonSizes();

        Cell cell = gameLogic.getCellFromGameBoard(row,col);
        gameLogic.updateUserInputInGameBoard(cell);
        Cell updatedCell = gameLogic.getCellFromGameBoard(row, col);

        if (updatedCell.hasZombie()){
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zombie_walking);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            // Code tutorial from: https://www.stechies.com/add-sound-play-button-click/
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.zombie_stab);
            mp.start();
            if (updatedCell.isClicked()) {
                num_scans++;
                int zombieScan = updatedCell.getScanOfZombies();
                makeButtonsBounce(updatedCell);
                button.setText(zombieScan + "");
                button.setTextSize(16);
                button.setTextColor(Color.rgb(255, 255, 255));
            } else {
                num_zombies_found = gameLogic.getCurrentZombiesCounter();
            }
            gameLogic.updateCellClicked(updatedCell);
            updateScansInUI(updatedCell);
        } else {
            num_scans++;
            int zombieScan = updatedCell.getScanOfZombies();
            makeButtonsBounce(updatedCell);
            button.setText(zombieScan + "");
            button.setTextSize(16);
            button.setTextColor(Color.rgb(105, 12, 12));
        }

        if (didPlayerWin()){
            showDialog();
        }
    }

    private void makeButtonsBounce(Cell cell){
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        animation.setInterpolator(interpolator);

        for (int i = 0; i < num_cols; i++) {
            buttons[cell.getRow()][i].startAnimation(animation);
        }

        for (int i = 0; i < num_rows; i++) {
            buttons[i][cell.getColumn()].startAnimation(animation);
        }
    }

    private void lockButtonSizes() {
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void updateScansInUI(Cell cell){
        int cellRow = cell.getRow();
        int cellCol = cell.getColumn();

        Cell temp;
        int numScan;

        for (int i = 0; i < num_cols; i++) {
            temp = gameLogic.getCellFromGameBoard(cellRow, i);
            if (temp.hasScan() && (temp.getScanOfZombies()!= 0) ){
                numScan = gameLogic.scanZombies(temp);
                buttons[cellRow][i].setText(numScan + "");
            }
        }

        for (int i = 0; i < num_rows; i++) {
            temp = gameLogic.getCellFromGameBoard(i, cellCol);
            if (temp.hasScan() && (temp.getScanOfZombies()!= 0) ){
                numScan = gameLogic.scanZombies(temp);
                buttons[i][cellCol].setText(numScan + "");
            }
        }
    }

    private void setupCountTextDisplays() {
        TextView zombieText = findViewById(R.id.zombieCountTextView);
        TextView scanText = findViewById(R.id.scanCountTextView);

        zombieText.setText(getString(R.string.zombie_count, num_zombies_found, num_zombies));
        scanText.setText(getString(R.string.scan_count,num_scans));
    }

    private boolean didPlayerWin(){
        return gameLogic.getCurrentZombiesCounter() == num_zombies;
    }
}
