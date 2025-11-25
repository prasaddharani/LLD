package org.example.tictactoe.utility;

import org.example.tictactoe.enums.Symbol;

public class Board {
    private final int rows;
    private final int cols;
    private Symbol[][] grid;

    Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

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
}
