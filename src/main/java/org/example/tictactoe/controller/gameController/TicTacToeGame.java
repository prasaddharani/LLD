package org.example.tictactoe.controller.gameController;

import org.example.tictactoe.controller.BoardGame;
import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.gameStateHandlers.GameState;
import org.example.tictactoe.gameStateHandlers.concreteStates.OWonState;
import org.example.tictactoe.gameStateHandlers.concreteStates.XWonState;
import org.example.tictactoe.gameStateHandlers.context.GameContext;
import org.example.tictactoe.strategy.PlayerStrategy;
import org.example.tictactoe.utility.Board;
import org.example.tictactoe.utility.Player;
import org.example.tictactoe.utility.Position;

public class TicTacToeGame implements BoardGame {
    Board board;
    Player playerX;
    Player playerO;
    Player currentPlayer;
    GameContext gameContext;

    public TicTacToeGame(PlayerStrategy xStrategy, PlayerStrategy oStrategy,
                         int rows, int cols) {
        this.board = new Board(rows, cols);
        this.playerX = new Player(Symbol.X, xStrategy);
        this.playerO = new Player(Symbol.O, oStrategy);
        this.currentPlayer = playerX;
        this.gameContext = new GameContext();
    }

    @Override
    public void play() {
        do {
            board.printBoard();
            Position move = currentPlayer.getPlayerStrategy().makeMove(board);
            board.makeMove(move, currentPlayer.getSymbol());
            board.checkGameState(gameContext, currentPlayer);
            switchPlayer();

        } while (!gameContext.isGameOver());
        announceResults();
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == playerX? playerO: playerX;
    }

    private void announceResults() {
        GameState state = gameContext.getCurrentState();
        if (state instanceof XWonState) {
            System.out.println("PlayerX Won the game");
        } else if (state instanceof OWonState) {
            System.out.println("PlayerO Won the game");
        } else {
            System.out.println("Match was drawn");
        }
    }
}
