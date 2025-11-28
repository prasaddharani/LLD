package org.example.chessGame.utility;

public class Board {
    private static Board instance = null;
    private Cell[][] board;

    private Board(int rows) {
        initializeBoard(rows);
    }

    public Board getInstance(int rows) {
        if (instance == null) {
            instance = new Board(rows);
        }
        return instance;
    }

    private void initializeBoard(int rows) {

    }
}
