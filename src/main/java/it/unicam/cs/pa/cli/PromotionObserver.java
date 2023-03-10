package it.unicam.cs.pa.cli;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.chess.ChessPieceType;

public interface PromotionObserver {
    ChessPieceType handlePromotion(Color color);
}
