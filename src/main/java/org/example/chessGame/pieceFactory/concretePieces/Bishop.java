package org.example.chessGame.pieceFactory.concretePieces;

import org.example.chessGame.movementStrategy.concreteMovementStategies.BishopMovementStrategy;
import org.example.chessGame.movementStrategy.concreteMovementStategies.RookMovementStrategy;
import org.example.chessGame.pieceFactory.Piece;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite, new BishopMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
