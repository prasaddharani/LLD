package org.example.chessGame.pieceFactory.concretePieces;

import org.example.chessGame.movementStrategy.concreteMovementStategies.KnightMovementStrategy;
import org.example.chessGame.movementStrategy.concreteMovementStategies.RookMovementStrategy;
import org.example.chessGame.pieceFactory.Piece;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite, new KnightMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
