package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.PieceInterface;
import lombok.Getter;

public class ChessPiece implements PieceInterface<ChessPieceType> {
    private final ChessPieceType type;
    private final Color color;

    public ChessPiece(ChessPieceType type, Color color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public ChessPieceType getType() {
        return type;
    }

    @Override
    public Color getColor() {
        return color;
    }


}
