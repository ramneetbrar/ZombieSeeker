package com.ramneet.zombieseeker;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Zombie {

    private ArrayList<Cell> zombies = new ArrayList<>();

    public Zombie(ArrayList<Cell> zombies) {
        this.zombies = zombies;
    }

    public ArrayList<Cell> getZombies() {
        return zombies;
    }

    public void inputZombies(Cell gameBoard[][]){
        for (Cell zombie : zombies) {
            gameBoard[zombie.getRow()][zombie.getColumn()] = zombie;
        }
//        Log.d("Zombie", "in input zombie");
    }

    public void initializeZombies(int row, int column, int numberOfZombies, Cell gameBoard[][]) {
        zombies.clear(); //to make sure that we're not fucking up by inputting new zombies in an old array
        int zombieCounter = 0;
        while (zombieCounter != numberOfZombies) {
//            System.out.println("hello");
            int zombieRow = new Random().nextInt(row);
            int zombieColumn = new Random().nextInt(column);
            Cell zombieCell = new Cell(zombieRow, zombieColumn, true, false, false, 0);
//            System.out.println("row is: " + zombieRow + " col is: " + zombieColumn);
            zombieCounter++;
            if (isUniqueZombie(zombieCell)){
//                System.out.println("hello");
                zombies.add(zombieCell);
                zombieCounter++;
            }
        }
        inputZombies(gameBoard);
    }

    private boolean isUniqueZombie(Cell zombieCell) {
//        System.out.println("zombie list: ");
//        System.out.println(zombies);
        for (Cell zombie : zombies) {
            if (zombieCell.equals(zombie)){
//                System.out.println("in false");
                return false;
            }
        }
        return true;
    }
}
