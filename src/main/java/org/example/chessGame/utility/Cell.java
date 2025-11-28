package org.example.chessGame.utility;

import org.example.chessGame.pieceFactory.Piece;

public class Cell {
    private int rows, cols;
    private String label;
    private Piece piece;

    public Cell(int rows, int cols, Piece piece) {
        this.rows = rows;
        this.cols = cols;
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
