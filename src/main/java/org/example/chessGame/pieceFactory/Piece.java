package org.example.chessGame.pieceFactory;

import org.example.chessGame.movementStrategy.MovementStrategy;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public abstract class Piece {
    private boolean isWhite;
    private boolean isKilled;
    private MovementStrategy movementStrategy;

    public Piece(boolean isWhite, MovementStrategy movementStrategy) {
        this.isWhite = isWhite;
        this.movementStrategy = movementStrategy;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public boolean getIsKilled() {
        return isKilled;
    }

    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
