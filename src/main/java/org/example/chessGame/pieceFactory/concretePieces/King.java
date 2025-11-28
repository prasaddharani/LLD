package org.example.chessGame.pieceFactory.concretePieces;

import org.example.chessGame.movementStrategy.MovementStrategy;
import org.example.chessGame.movementStrategy.concreteMovementStategies.KingMovementStrategy;
import org.example.chessGame.pieceFactory.Piece;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite, new KingMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
