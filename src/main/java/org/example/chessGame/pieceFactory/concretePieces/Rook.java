package org.example.chessGame.pieceFactory.concretePieces;

import org.example.chessGame.movementStrategy.concreteMovementStategies.KingMovementStrategy;
import org.example.chessGame.movementStrategy.concreteMovementStategies.RookMovementStrategy;
import org.example.chessGame.pieceFactory.Piece;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite, new RookMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
