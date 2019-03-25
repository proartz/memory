package com.proartz.model;

import java.util.ArrayList;

public class Model {
    private int selectionCounter;
    private int numberOfTiles;
    private int toGuess;
    private ArrayList<Tile> tiles;
    private Tile[] selectedTiles;

    public Model() {
        selectionCounter = 0;
        numberOfTiles = 16;
        toGuess = numberOfTiles / 2;
        tiles = new ArrayList<>();
        selectedTiles = new Tile[2];
    }

    public void addTile(int index, String value) {
        tiles.add(new Tile(index, value));
    }

    public void incrementSelectionCounter() {
        selectionCounter++;
    }

    public void resetSelectionCounter() {
        selectionCounter = 0;
    }

    public int getSelectionCounter() {
        return selectionCounter;
    }

    public void setSelectionCounter(int selectionCounter) {
        this.selectionCounter = selectionCounter;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public Tile getTile(int indexOfTile) {
        return tiles.get(indexOfTile);
    }

    public void selectTile(int tileNumber) {
        tiles.get(tileNumber).setSelected(true);
    }

    public void saveSelectedTile(int tileNumber, Tile selectedTile) {
        selectedTiles[tileNumber - 1] = selectedTile;
    }

    public void decrementToGuess() {
        toGuess--;
    }

    public int getToGuess() {
        return toGuess;
    }

    public boolean compareSelectedTiles() {
        String value1 = selectedTiles[0].getValue();
        String value2 = selectedTiles[1].getValue();

        return value1.equals(value2);
    }

    public void setSelectedTilesToGuessed() {
        selectedTiles[0].setGuessed(true);
        selectedTiles[1].setGuessed(true);
    }

    public void resetSelectedTiles() {
        selectedTiles[0] = null;
        selectedTiles[1] = null;
    }

    public void deselectSelectedTiles() {
        selectedTiles[0].setSelected(false);
        selectedTiles[1].setSelected(false);
    }

    public int getSelectedTileIndex(int tileNumber) {
        return selectedTiles[tileNumber - 1].getIndex();
    }
}
