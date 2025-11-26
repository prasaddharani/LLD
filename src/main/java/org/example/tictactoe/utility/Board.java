package org.example.tictactoe.utility;

import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.gameStateHandlers.context.GameContext;

public class Board {
    private final int rows;
    private final int cols;
    private Symbol[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Symbol[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isValidMove(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return 0 <= row && row < rows && 0 <= col && col < cols &&
                grid[row][col] == Symbol.EMPTY;
    }

    public void makeMove(Position position, Symbol symbol) {
        grid[position.getRow()][position.getCol()] = symbol;
    }

    public void checkGameState(GameContext context, Player currentPlayer) {

        // Row wise
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] != Symbol.EMPTY && isWinningLine(grid[i])) {
                context.next(currentPlayer, true);
                return;
            }
        }

        // Column wise
        for (int i = 0; i < rows; i++) {
            Symbol[] column = new Symbol[rows];

            for (int j = 0; j < cols; j++) {
                column[j] = grid[j][i];
            }

            if (column[0] != Symbol.EMPTY && isWinningLine(column)) {
                context.next(currentPlayer, true);
                return;
            }
        }

        // Diagonal wise
        int min = Math.min(rows, cols);
        Symbol[] diagonal1 = new Symbol[min];
        Symbol[] diagonal2 = new Symbol[min];

        for (int i = 0; i < min; i++) {
            diagonal1[i] = grid[i][i];
            diagonal2[i] = grid[i][cols - i - 1];
        }
        if (diagonal1[0] != Symbol.EMPTY && isWinningLine(diagonal1)) {
            context.next(currentPlayer, true);
            return;
        }
        if (diagonal2[0] != Symbol.EMPTY && isWinningLine(diagonal2)) {
            context.next(currentPlayer, true);
            return;
        }

    }

    private boolean isWinningLine(Symbol[] symbols) {
        Symbol first = symbols[0];
        for (Symbol symbol: symbols) {
            if (symbol != first) {
                return false;
            }
        }
        return true;
    }


    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Symbol symbol = grid[i][j];
                switch (symbol) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                    default:
                        System.out.print(" . ");
                }

                if (j < cols - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < rows - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }
}
