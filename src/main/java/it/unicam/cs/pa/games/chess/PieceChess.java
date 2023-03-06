package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.PieceInterface;
import lombok.Getter;

@Getter
public class PieceChess implements PieceInterface<PieceChessType> {
    private final PieceChessType type;
    private final Color color;

    public PieceChess(PieceChessType type, Color color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public PieceChessType getType() {
        return type;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
