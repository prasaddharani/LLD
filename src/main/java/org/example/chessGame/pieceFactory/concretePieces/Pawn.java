package org.example.chessGame.pieceFactory.concretePieces;

import org.example.chessGame.movementStrategy.concreteMovementStategies.PawnMovementStrategy;
import org.example.chessGame.movementStrategy.concreteMovementStategies.RookMovementStrategy;
import org.example.chessGame.pieceFactory.Piece;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite, new PawnMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
