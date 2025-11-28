package org.example.chessGame.pieceFactory;

import org.example.chessGame.pieceFactory.concretePieces.*;

public class PieceFactory {

    public static Piece getPiece(String pieceType, boolean isWhite) {
       return switch (pieceType) {
            case "king" -> new King(isWhite);
            case "queen" -> new Queen(isWhite);
            case "bishop" -> new Bishop(isWhite);
            case "rook" -> new Rook(isWhite);
            case "knight" -> new Knight(isWhite);
            case "pawn" -> new Pawn(isWhite);
           default -> throw new IllegalStateException("Unexpected value: " + pieceType);
       };
    }
}
