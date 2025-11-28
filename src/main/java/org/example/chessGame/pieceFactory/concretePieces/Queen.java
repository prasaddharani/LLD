package org.example.chessGame.pieceFactory.concretePieces;

import org.example.chessGame.movementStrategy.concreteMovementStategies.QueenMovementStrategy;
import org.example.chessGame.pieceFactory.Piece;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite, new QueenMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
