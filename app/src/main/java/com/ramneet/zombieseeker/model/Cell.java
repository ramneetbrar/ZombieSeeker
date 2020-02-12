package com.ramneet.zombieseeker.model;

public class Cell {
    private int row;
    private int col;
    private boolean zombie;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasZombie() {
        return zombie;
    }
}
