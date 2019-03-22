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
        //check is numberOfButtons a square number
        //so we can have square board(rows == columns)
        if(((int)Math.sqrt(numberOfTiles) % 1) != 0) {
            System.err.println("Error: numberOfTiles need to be a square of an integer :(");
            System.exit(-1);
        }
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
