package com.ramneet.zombieseeker;

public class GameLogic {

    private int row;
    private int column;
    private Cell gameBoard[][];
    private int totalZombies;
    private int currentZombiesCounter;

    public void playGame(Cell userInput){
        while (currentZombiesCounter != totalZombies){
            updateUserInputInGameBoard(userInput);
        }
    }

    public GameLogic(int row, int column, int totalZombies) {
        this.row = row;
        this.column = column;
        this.gameBoard = new Cell[row][column];
        this.totalZombies = totalZombies;
    }

    public void initializeGameBoard(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                insertCellInGameBoard(new Cell(i, j, false, false, false, 0));
            }
        }
        currentZombiesCounter = 0;
    }

    public void updateUserInputInGameBoard(Cell userInput){
        Cell userInputInGameBoard = gameBoard[userInput.getRow()][userInput.getColumn()];

        if (userInputInGameBoard.hasZombie()){
            if (userInputInGameBoard.isClicked()){
                userInputInGameBoard.setHasScan(true);
                userInputInGameBoard.setScanOfZombies(scanZombies(userInputInGameBoard));
                insertCellInGameBoard(userInputInGameBoard);
            } else {
                //need to keep updating the cells to decrement
                userInputInGameBoard.setClicked(true);
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

}
