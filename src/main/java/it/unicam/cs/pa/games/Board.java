package it.unicam.cs.pa.games;

public interface Board{
    /**
     * Get the width of the board
     * @return the width of the board
     */
    int getWight();
    /**
     * Get the height of the board
     * @return the height of the board
     */
    int getHeight();
    /**
     * Set a piece in a position
     * @param piece the piece to set
     * @param position the position where to set the piece
     */
    <T> void setPiece(Piece<T> piece, Position position);
    /**
     * Get a piece in a position
     * @param position the position where to get the piece
     */
    Piece<?> getPiece(Position position);
    /**
     * Get a piece in a position
     * @param x the x coordinate of the position where to get the piece
     * @param y the y coordinate of the position where to get the piece
     */
    Piece<?> getPiece(int x, int y);
    /**
     * Remove a piece in a position
     * @param position the position where to remove the piece
     */
    void removePiece(Position position);
    /**
     * Check if a position is free
     * @param position of the position to check
     */
    boolean isFree(Position position);
    /**
     * Check if a position is free
     * @param x the x coordinate of the position to check
     * @param y the y coordinate of the position to check
     */
    boolean isFree(int x, int y);
    /**
     * Get the position of a piece
     * @param piece the piece
     * @return the position of the piece
     */
    Position getPosition(Piece<?> piece);

    /**
     * Move a piece from an origin to a destination
     * @param origin the origin of the piece
     * @param destination the destination of the piece
     */
    boolean move(Position origin, Position destination, Color color);

    /**
     * Check if a position is on the board
     * @param position the position to check
     * @return true if the position is on the board, false otherwise
     */
    boolean onBoard(Position position);
}
