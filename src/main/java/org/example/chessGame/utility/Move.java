package org.example.chessGame.utility;

public class Move {
    private Cell startCell;
    private Cell endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    public boolean isValid() {
        if (endCell == null) {
            return true;
        }
        return !(startCell.getPiece() == endCell.getPiece());
    }
}
