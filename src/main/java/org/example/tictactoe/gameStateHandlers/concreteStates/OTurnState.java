package org.example.tictactoe.gameStateHandlers.concreteStates;

import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.gameStateHandlers.GameState;
import org.example.tictactoe.gameStateHandlers.context.GameContext;
import org.example.tictactoe.utility.Player;

public class OTurnState implements GameState {

    @Override
    public void makeMove(GameContext gameContext, Player player, boolean hasWon) {
        if (hasWon) {
            gameContext.setCurrentState(player.getSymbol() == Symbol.O? new OWonState(): new XWonState());
        } else {
            gameContext.setCurrentState(new OWonState());
        }
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
