package com.ramneet.zombieseeker;

import java.util.Objects;

class Cell {
    private int row;
    private int column;
    private boolean hasZombie;
    private boolean isClicked; //then in the ui, if this is true, it'll print, else, it wont

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell(int row, int column, boolean hasZombie, boolean isClicked) {
        this.row = row;
        this.column = column;
        this.hasZombie = hasZombie;
        this.isClicked = isClicked;
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
