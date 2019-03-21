package com.proartz;

public class Model<T> {
    private int selectionCounter;
    private int numberOfTiles;
    private int toGuess;
    private Board board;

    private Tile selectedTile1, selectedTile2;

    public Model() {
        selectionCounter = 0;
        numberOfTiles = 50;
        toGuess = numberOfTiles / 2;
        board = new Board(numberOfTiles);
        selectedTile1 = new Tile(null);
        selectedTile2 = new Tile(null);
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

    public Tile getSelectedTile1() {
        return selectedTile1;
    }

    public void setSelectedTile1(Tile selectedTile1) {
        this.selectedTile1 = selectedTile1;
    }

    public Tile getSelectedTile2() {
        return selectedTile2;
    }

    public void setSelectedTile2(Tile selectedTile2) {
        this.selectedTile2 = selectedTile2;
    }

    public void decrementToGuess() {
        toGuess--;
    }

    public int getToGuess() {
        return toGuess;
    }

    public void setToGuess(int toGuess) {
        this.toGuess = toGuess;
    }
}
