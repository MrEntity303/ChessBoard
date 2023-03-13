package it.unicam.cs.pa.games;

import java.util.List;

public interface Piece<T> {
    /**
     * Get the type of the piece
     *      @return the type of the piece
     **/
    T getType();

    /**
     * Set the type of the piece
     *      @param type the type of the piece
     **/
    void setType(T type);

    /**
     * Get the color of the piece
     *      @return the color of the piece
     **/
    Color getColor();
    /**
     * Add a move to a list<Move> of the piece
     *      @param move the move to add
     **/
    void addMove(Move move);

    /**
     * Get the list of moves of the piece
     *      @return the list of moves of the piece
     **/
    List<? extends Move> getList();

    /**
     * Reset the list of moves of the piece
     **/
    void resetListMoves();

    /**
     * Check if a move is legal for the piece
     *
     * @param origin  the position to check
     * @param rowOffset the number of rows to move in the forward/backward direction
     * @param colOffset the number of columns to move in the right/left direction
     * @return legal move in a direction of the piece
     **/
    Move moveInDirection(Position origin, int rowOffset, int colOffset);
}
