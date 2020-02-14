package com.ramneet.zombieseeker;

import java.util.ArrayList;
import java.util.Random;

public class Zombie {

    private ArrayList<Cell> zombies = new ArrayList<>();

    public void inputZombies(Cell gameBoard[][]){
        for (Cell zombie : zombies) {
            gameBoard[zombie.getRow()][zombie.getColumn()] = zombie;
        }
    }

    public void initializeZombies(int numberOfZombies, int row, int column) {
        zombies.clear(); //to make sure that we're not fucking up by inputting new zombies in an old array
        int zombieCounter = 0;
        while (zombieCounter != numberOfZombies) {
            int zombieRow = new Random().nextInt(row);
            int zombieColumn = new Random().nextInt(column);
            Cell zombieCell = new Cell(zombieRow, zombieColumn, true, false, false, 0);
            if (isUniqueZombie(zombieCell)){
                zombies.add(zombieCell);
                zombieCounter++;
            }
        }
    }

    public ArrayList<Cell> getZombies() {
        return zombies;
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
