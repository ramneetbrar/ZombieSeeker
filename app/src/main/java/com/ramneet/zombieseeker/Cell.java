package com.ramneet.zombieseeker;

class Cell {
    private int row;
    private int column;
    private boolean hasZombie;
    private boolean hasScan;
    private boolean isClicked; //then in the ui, if this is true, it'll print, else, it wont
    private int scanOfZombies;

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", hasZombie=" + hasZombie +
                ", hasScan=" + hasScan +
                ", isClicked=" + isClicked +
                ", scanOfZombies=" + scanOfZombies +
                '}';
    }

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

    //when i overrided the equals operator, it gave me this but im not sure what it is and it gives
    //an error at "hash" you can uncomment and check if youwant
//    @Override
//    public int hashCode() {
//        return Objects.hash(row, column);
//    }
}
