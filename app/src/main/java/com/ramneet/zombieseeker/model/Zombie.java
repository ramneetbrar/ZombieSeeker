package com.ramneet.zombieseeker.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Inputs the zombies into the gameboard in a random order.
 */
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
    }

    public void initializeZombies(int row, int column, int numberOfZombies, Cell gameBoard[][]) {
        zombies.clear();
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
        inputZombies(gameBoard);
    }

    private boolean isUniqueZombie(Cell zombieCell) {
        for (Cell zombie : zombies) {
            if (zombieCell.equals(zombie)){
                return false;
            }
        }
        return true;
    }
}
