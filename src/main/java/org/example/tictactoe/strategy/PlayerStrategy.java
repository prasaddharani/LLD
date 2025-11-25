package org.example.tictactoe.strategy;

import org.example.tictactoe.utility.Board;
import org.example.tictactoe.utility.Position;

public interface PlayerStrategy {

    Position makeMove(Board board);
}
