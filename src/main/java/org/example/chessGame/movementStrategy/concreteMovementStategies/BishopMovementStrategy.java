package org.example.chessGame.movementStrategy.concreteMovementStategies;

import org.example.chessGame.movementStrategy.MovementStrategy;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public class BishopMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
