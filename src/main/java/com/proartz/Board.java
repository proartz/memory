package com.proartz;

/**
 * Square board of tiles
 */

public class Board {
    private int numberOfTiles;

    private Tile[][] board;

    public Board(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
        initializeBoard();
    }

    private void initializeBoard() {

        int numbersOfRows = (int)Math.sqrt(numberOfTiles);

        // create a table of a tables
        board = new Tile[numbersOfRows][];

        // initialize the matrix with an empty Tile<T> objects
        for(int i = 0; i < numbersOfRows; i++) {
            board[i] = new Tile[numbersOfRows];
            for(int j = 0; j < numbersOfRows; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Tile getTile(int rowNumber, int columnNumber) {
        return board[rowNumber][columnNumber];
    }
}
