package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessPieceTest {
    @Test
    public void chessPieceTest() {
        ChessPiece piece = new ChessPiece(ChessPieceType.ROOK, Color.WHITE);
        assertEquals(piece.getType(), ChessPieceType.ROOK);
        assertEquals(piece.getColor(), Color.WHITE);
    }

    @Test
    public void chessPieceTest2() {
        ChessPiece piece = new ChessPiece(ChessPieceType.KNIGHT, Color.BLACK);
        assertEquals(piece.getType(), ChessPieceType.KNIGHT);
        assertEquals(piece.getColor(), Color.BLACK);
    }
}
