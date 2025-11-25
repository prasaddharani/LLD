package org.example.tictactoe.gameStateHandlers.concreteStates;

import org.example.tictactoe.gameStateHandlers.GameState;
import org.example.tictactoe.gameStateHandlers.context.GameContext;

public class XTurnState implements GameState {

    @Override
    public void makeMove(GameContext gameContext, Player player, boolean hasWon) {
        if (hasWon) {
            gameContext.setCurrentState();
        }
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
