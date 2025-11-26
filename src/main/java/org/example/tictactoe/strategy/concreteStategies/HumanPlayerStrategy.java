package org.example.tictactoe.strategy.concreteStategies;

import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.strategy.PlayerStrategy;
import org.example.tictactoe.utility.Board;
import org.example.tictactoe.utility.Position;

import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy {
    private final Scanner scanner;
    private final String playerName;

    public HumanPlayerStrategy(String playerName) {
        this.playerName = playerName;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Position makeMove(Board board) {
        Position position;
        while (true) {
            System.out.println(playerName + ": ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            position = new Position(row, col);
            if (board.isValidMove(position)) {
                return position;
            }
            System.out.println("Invalid move. Try again.");
        }
    }
}
