package org.example.chessGame.controller.concreteGames;

import org.example.chessGame.enums.Status;
import org.example.chessGame.pieceFactory.Piece;
import org.example.chessGame.pieceFactory.concretePieces.King;
import org.example.chessGame.utility.Board;
import org.example.chessGame.utility.Cell;
import org.example.chessGame.utility.Move;
import org.example.chessGame.utility.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGame {
    private Player player1;
    private Player player2;
    private boolean isWhiteTurn;
    private Board board;
    private List<Move> gameLogs;
    private Status status;

    public ChessGame(Player player1, Player player2, int rows) {
        this.player1 = player1;
        this.player2 = player2;
        this.isWhiteTurn = true;
        this.board = Board.getInstance(rows);
        this.gameLogs = new ArrayList<>();
        this.status = Status.ACTIVE;
    }

    public void start() {
        while (this.status == Status.ACTIVE) {
            Player currentPlayer = isWhiteTurn? player1: player2;
            System.out.println(currentPlayer.getName() + "'s turn (" + (currentPlayer.isWhite() ? "White": "Black") + ")");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter source row and column (e.g., 6 4): ");

            int startRow = scanner.nextInt();
            int startCol = scanner.nextInt();

            System.out.print("Enter destination row and column (e.g., 6 4): ");

            int endRow = scanner.nextInt();
            int endCol = scanner.nextInt();

            Cell startCell = board.getCell(startRow, startCol);
            Cell endCell = board.getCell(endRow, endCol);

            if (startCell == null || startCell.getPiece() == null) {
                System.out.println("Invalid move, Please try again!");
                continue;
            }

            makeMove(new Move(startCell, endCell));

        }
    }

    private void makeMove(Move move) {
        if (move.isValid()) {
            Piece sourcePiece = move.getStartCell().getPiece();

            if (sourcePiece.canMove(board, move.getStartCell(), move.getEndCell())) {
                Piece destinationPiece = move.getEndCell().getPiece();

                if (destinationPiece != null) {
                    if (destinationPiece instanceof King && isWhiteTurn) {
                        this.status = Status.WHITE_WIN;
                        return;
                    }

                    if (destinationPiece instanceof King) {
                        this.status = Status.BLACK_WIN;
                        return;
                    }

                    destinationPiece.setKilled(true);
                }
                gameLogs.add(move);

                move.getEndCell().setPiece(sourcePiece);
                move.getStartCell().setPiece(null);
                this.isWhiteTurn = !isWhiteTurn;
                System.out.println(isWhiteTurn);
            }
        }
    }
}
