package com.ramneet.zombieseeker;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    //default row and column
    private int row = 4;
    private int column = 7;
    private ArrayList<ArrayList<Cell>> gameBoard = new ArrayList<>();
    private ArrayList<Cell> zombies = new ArrayList<>();

    //to get the row and column info from what the user inputs in option
    public void initializeRowAndColumn(int row, int column){
        this.row = row;
        this.column = column;
    }

    //lmao im tired and cant figure out how to add an element to a 2d arraylist. i was doing
    // [row][col] but then forgot thats for arrays

//    public void inputZombies(){
//        for (Cell zombie : zombies) {
//           gameBoard.add(zombie.getRow()).add(zombie.getColumn())
//        }
//        for (int i = 0; i < zombies.size(); i++) {
//            gameBoard.get(zombies.get(i).getRow()).get(zombies.get(i).getColumn()) = zombies.get(i);
//        }
//    }

    public void initializeZombies(int numberOfZombies) {
        zombies.clear(); //to make sure that we're not fucking up by inputting new zombies in an old array
        int zombieCounter = 0;
        while (zombieCounter != numberOfZombies) {
            int zombieRow = new Random().nextInt(row);
            int zombieColumn = new Random().nextInt(column);
            Cell zombieCell = new Cell(zombieRow, zombieColumn, true, false);
            if (isUniqueZombie(zombieCell)){
                zombies.add(zombieCell);
                zombieCounter++;
            }
        }
    }

    private boolean isUniqueZombie(Cell zombieCell) {
        for (Cell zombie : zombies) {
            if (zombieCell.equals(zombie)){
                return false;
            }
        }
        return false;
    }
}
