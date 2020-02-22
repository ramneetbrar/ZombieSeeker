package com.ramneet.zombieseeker.model;

/**
 * The Cell class stores relevant information relating to each cell on the game board.
 */
public class Cell {
    private int row;
    private int column;
    private boolean hasZombie;
    private boolean hasScan;
    private boolean isClicked;
    private int scanOfZombies;


    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean hasZombie() {
        return hasZombie;
    }

    public boolean hasScan() {
        return hasScan;
    }

    public void setHasScan(boolean hasScan) {
        this.hasScan = hasScan;
    }

    public void setScanOfZombies(int scanOfZombies) {
        this.scanOfZombies = scanOfZombies;
    }

    public int getScanOfZombies() {
        return scanOfZombies;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell(int row, int column, boolean hasZombie, boolean isClicked, boolean hasScan, int scanOfZombies) {
        this.row = row;
        this.column = column;
        this.hasZombie = hasZombie;
        this.isClicked = isClicked;
        this.hasScan = hasScan;
        this.scanOfZombies = scanOfZombies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row &&
                column == cell.column;
    }

}
