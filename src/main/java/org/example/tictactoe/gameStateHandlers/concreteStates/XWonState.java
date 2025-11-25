package org.example.tictactoe.gameStateHandlers.concreteStates;

import org.example.tictactoe.gameStateHandlers.GameState;
import org.example.tictactoe.gameStateHandlers.context.GameContext;

public class XWonState implements GameState {
    @Override
    public void makeMove(GameContext gameContext, Player player, boolean hasWon) {

    }

    @Override
    public boolean isGameOver() {
        return true;
    }
}
