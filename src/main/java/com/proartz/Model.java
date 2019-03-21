package com.proartz;

public class Model<T> {
    private int selectionCounter;
    private int numberOfTiles;
    private tile<T>[][] board;

    public Model() {
        selectionCounter = 0;
        numberOfTiles = 50;
        initializeBoard();
    }

    private void initializeBoard() {
        int numbersOfRows = (int)Math.sqrt(numberOfTiles);

        // create a table of a tables
        board = new tile[numbersOfRows][];

        // initialize the matrix with an empty tile<T> objects
        for(int i = 0; i < numbersOfRows; i++) {
            board[i] = new tile[numbersOfRows];
            for(int j = 0; j < numbersOfRows; j++) {
                board[i][j] = new tile<T>();
            }
        }
    }

    public void incrementCounter() {
        selectionCounter++;
    }

    public void resetCounter() {
        selectionCounter = 0;
    }

    public int getSelectionCounter() {
        return selectionCounter;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }
}
