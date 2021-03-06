package com.ramneet.zombieseeker.model;

import java.util.ArrayList;

/**
 * The GameLogic class initializes the gameBoard, and keeps it up to date with the users moves.
 * Exposes the relevant information to UI classes to be displayed on the device.
 */

public class GameLogic {

    private int row;
    private int column;
    private Cell gameBoard[][];
    private int totalZombies;
    private int currentZombiesCounter;

    public GameLogic(int row, int column, int totalZombies) {
        this.row = row;
        this.column = column;
        this.gameBoard = new Cell[row][column];
        this.totalZombies = totalZombies;
    }

    public void initializeGameBoard(int row, int column){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                insertCellInGameBoard(new Cell(i, j, false, false, false, 0));
            }
        }
        setupZombies();
        currentZombiesCounter = 0;
    }

    public void setupZombies() {
        ArrayList<Cell> zombies = new ArrayList<>();
        Zombie zombie = new Zombie(zombies);
        zombie.initializeZombies(row, column, totalZombies, gameBoard);
    }

    public void updateUserInputInGameBoard(Cell userInput){
        Cell userInputInGameBoard = gameBoard[userInput.getRow()][userInput.getColumn()];

        if (userInputInGameBoard.hasZombie()){
            if (userInputInGameBoard.isClicked()){
                userInputInGameBoard.setHasScan(true);
                userInputInGameBoard.setScanOfZombies(scanZombies(userInputInGameBoard));
                insertCellInGameBoard(userInputInGameBoard);
            } else {
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


    public int scanZombies(Cell cell){
        int zombieCounter = 0;
        for (int i = 0; i < column; i++) {
            if (gameBoard[cell.getRow()][i].hasZombie() && !gameBoard[cell.getRow()][i].isClicked()){
                zombieCounter++;
            }
        }

        for (int i = 0; i < row; i++) {
            if (gameBoard[i][cell.getColumn()].hasZombie() && !gameBoard[i][cell.getColumn()].isClicked()){
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
                int temp = cell.getScanOfZombies() - 1;
                gameBoard[cell.getRow()][i].setScanOfZombies(temp);
            }
        }

        for (int i = 0; i <row; i++) {
            if (gameBoard[i][cell.getColumn()].hasScan()) {
                int temp = cell.getScanOfZombies() - 1;
                gameBoard[i][cell.getColumn()].setScanOfZombies(temp);
            }
        }
    }

    public void updateCellClicked(Cell cell){
        gameBoard[cell.getRow()][cell.getColumn()].setClicked(true);

    }

    public int getCurrentZombiesCounter() {
        return currentZombiesCounter;
    }
}
