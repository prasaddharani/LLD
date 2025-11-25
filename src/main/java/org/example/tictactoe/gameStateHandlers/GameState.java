package org.example.tictactoe.gameStateHandlers;

import org.example.tictactoe.gameStateHandlers.context.GameContext;

public interface GameState {
    void makeMove(GameContext gameContext, Player player, boolean hasWon);
    boolean isGameOver();
}
