package org.example.tictactoe;

import org.example.tictactoe.controller.gameController.TicTacToeGame;
import org.example.tictactoe.strategy.PlayerStrategy;
import org.example.tictactoe.strategy.concreteStategies.HumanPlayerStrategy;
import org.example.tictactoe.utility.Player;

public class Main {
    public static void main(String[] args) {
        PlayerStrategy xPlayer = new HumanPlayerStrategy("Dharani");
        PlayerStrategy oPlayer = new HumanPlayerStrategy("Prasad");
        TicTacToeGame ticTacToeGame = new TicTacToeGame(xPlayer, oPlayer, 3, 3);
        ticTacToeGame.play();
    }
}
