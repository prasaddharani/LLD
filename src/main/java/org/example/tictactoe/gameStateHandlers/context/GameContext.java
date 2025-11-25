package org.example.tictactoe.gameStateHandlers.context;

import org.example.tictactoe.gameStateHandlers.GameState;
import org.example.tictactoe.gameStateHandlers.concreteStates.XTurnState;

public class GameContext {
    private GameState currentState;

    GameContext(GameState currentState) {
        this.currentState = new XTurnState();
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void next(Player player, boolean hasWon) {
        currentState.makeMove(this, player, hasWon);
    }

    public boolean isGameOver () {
        return this.isGameOver();
    }

}
