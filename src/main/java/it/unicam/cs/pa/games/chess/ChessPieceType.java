package it.unicam.cs.pa.games.chess;

public enum ChessPieceType {
    KING('K')/*RE*/, QUEEN('Q')/*REGINA*/, ROOK('R')/*TORRE*/, BISHOP('B')/*ALFIERE*/, KNIGHT('H')/*CAVALLO*/, PAWN('P')/*PEDONE*/;
    private final char value;

    ChessPieceType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
