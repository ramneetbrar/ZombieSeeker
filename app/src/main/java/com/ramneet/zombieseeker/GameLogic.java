package com.ramneet.zombieseeker;

import java.util.ArrayList;

public class GameLogic {

    private int row;
    private int column;
    private Cell gameBoard[][];
    private int totalZombies;
    private int currentZombiesCounter;

//    public void playGame(Cell userInput){
//        while (currentZombiesCounter != totalZombies){
//            updateUserInputInGameBoard(userInput);
//        }
//    }

    public GameLogic(int row, int column, int totalZombies) {
        this.row = row;
        this.column = column;
        this.gameBoard = new Cell[row][column];
        this.totalZombies = totalZombies;
        //initializeGameBoard(row, column, totalZombies);
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    public void initializeGameBoard(int row, int column, int totalZombies){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                insertCellInGameBoard(new Cell(i, j, false, false, false, 0));
            }
        }
        currentZombiesCounter = 0;
    }

    public void setupZombies() {
        ArrayList<Cell> zombies = new ArrayList<>();
        Zombie zombie = new Zombie(zombies);
        zombie.initializeZombies(row, column, totalZombies, gameBoard);
    }

    public void updateUserInputInGameBoard(Cell userInput){
        //Cell userInputInGameBoard = gameBoard[row][col];
        Cell userInputInGameBoard = gameBoard[userInput.getRow()][userInput.getColumn()];


        if (userInputInGameBoard.hasZombie()){
            if (userInputInGameBoard.isClicked()){
                userInputInGameBoard.setHasScan(true);
                userInputInGameBoard.setScanOfZombies(scanZombies(userInputInGameBoard));
                insertCellInGameBoard(userInputInGameBoard);
            } else {
                //need to keep updating the cells to decrement
                userInputInGameBoard.setClicked(true);
                updateScans(userInputInGameBoard);
                insertCellInGameBoard(userInputInGameBoard);
                currentZombiesCounter++;
            }
        } else {
            userInputInGameBoard.setClicked(true);
            userInputInGameBoard.setHasScan(true);
            userInputInGameBoard.setScanOfZombies(scanZombies(userInputInGameBoard));
            insertCellInGameBoard(userInputInGameBoard);
        }
    }

    public void incrementZombiesCounter(){
        currentZombiesCounter++;
    }


    public int scanZombies(Cell cell){
        int zombieCounter = 0;
        for (int i = 0; i < column; i++) {
            if (gameBoard[cell.getRow()][i].hasZombie()){
                zombieCounter++;
            }
        }

        for (int i = 0; i < row; i++) {
            if (gameBoard[i][cell.getColumn()].hasZombie()){
                zombieCounter++;
            }
        }
        return zombieCounter;
    }

    public void insertCellInGameBoard(Cell cell){
        gameBoard[cell.getRow()][cell.getColumn()] = cell;
    }

    public Cell getCellFromGameBoard(int row, int col) {
        return gameBoard[row][col];
    }

    public void updateScans(Cell cell) {
        for (int i = 0; i <column; i++) {
            if (gameBoard[cell.getRow()][i].hasScan()) {
                int temp = cell.getScanOfZombies();
                cell.setScanOfZombies(temp-1);
            }
        }

        for (int i = 0; i <row; i++) {
            if (gameBoard[i][cell.getColumn()].hasScan()) {
                int temp = cell.getScanOfZombies();
                cell.setScanOfZombies(temp-1);
            }
        }
    }


}
