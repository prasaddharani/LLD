package org.example.chessGame.movementStrategy;

import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;

public interface MovementStrategy {

    boolean canMove(Board board, Cell startCell, Cell endCell);
}
