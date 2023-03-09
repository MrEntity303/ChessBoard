package it.unicam.cs.pa.games;

import it.unicam.cs.pa.games.chess.ChessMove;

import java.util.List;

public interface Piece<T> {
    /**
     * Get the type of the piece
     *      @return the type of the piece
     **/
    T getType();

    /**
     * Get the color of the piece
     *      @return the color of the piece
     **/
    Color getColor();
    /**
     * Add a move to a list<Move> of the piece
     *      @param origin the origin of the move
     *      @param destination the destination of the move
     **/
    void addMove(Move move);

    /**
     * Get the list of moves of the piece
     *      @return the list of moves of the piece
     **/
    List<? extends Move> getList();
}
