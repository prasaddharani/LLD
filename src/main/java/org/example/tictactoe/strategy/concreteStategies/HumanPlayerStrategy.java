package org.example.tictactoe.strategy.concreteStategies;

import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.strategy.PlayerStrategy;
import org.example.tictactoe.utility.Board;
import org.example.tictactoe.utility.Position;

import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy {
    private Scanner scanner;
    private String playerName;

    HumanPlayerStrategy(String playerName) {
        this.playerName = playerName;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Position makeMove(Board board) {
        Position position = null;
        while (true) {
            int row = scanner.nextInt();
            int cold = scanner.nextInt();

            if (board)
        }
    }
}
