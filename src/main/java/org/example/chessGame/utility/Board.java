package org.example.chessGame.utility;

import org.example.chessGame.pieceFactory.PieceFactory;

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
        board = new Cell[rows][rows];
        setPieceRow(0, true);
        setPawns(0, rows, true);

        setPieceRow(rows - 1, false);
        setPawns(rows - 2, rows, false);

        for (int i = 2; i < rows - 2; i++) {
            for (int j = 0; j < rows - 2; j++) {
                board[i][j] = new Cell(i, j, null);
            }
        }
    }

    private void setPieceRow(int row, boolean isWhite) {
        board[row][0] = new Cell(row, 0, PieceFactory.getPiece("rook", isWhite));
        board[row][1] = new Cell(row, 1, PieceFactory.getPiece("knight", isWhite));
        board[row][2] = new Cell(row, 2, PieceFactory.getPiece("bishop", isWhite));
        board[row][3] = new Cell(row, 3, PieceFactory.getPiece("king", isWhite));
        board[row][4] = new Cell(row, 4, PieceFactory.getPiece("queen", isWhite));
        board[row][5] = new Cell(row, 5, PieceFactory.getPiece("bishop", isWhite));
        board[row][6] = new Cell(row, 6, PieceFactory.getPiece("knight", isWhite));
        board[row][7] = new Cell(row, 7, PieceFactory.getPiece("rook", isWhite));
    }

    private void setPawns(int row, int rows, boolean isWhite) {
        for (int col = 0; col < rows; col++) {
            board[row][col] = new Cell(row, col, PieceFactory.getPiece("pawn", isWhite));
        }
    }
}
