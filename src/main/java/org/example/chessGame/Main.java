package org.example.chessGame;

import org.example.chessGame.controller.concreteGames.ChessGame;
import org.example.chessGame.utility.Player;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Dharani", true);
        Player player2 = new Player("Prasad", false);
        ChessGame chessGame = new ChessGame(player1, player2, 8);
        chessGame.start();
    }
}
