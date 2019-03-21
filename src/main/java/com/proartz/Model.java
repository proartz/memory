package com.proartz;

public class Model<T> {
    private int selectionCounter;
    private int numberOfTiles;
    private Board board;

    public Model() {
        selectionCounter = 0;
        numberOfTiles = 50;
        board = new Board(numberOfTiles);
    }

    public Board getBoard() {
        return board;
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
