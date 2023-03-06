package it.unicam.cs.pa.games;

public interface Board {
    int getWight();
    int getHeight();
    <T> void setPiece(PieceInterface<T> piece, Position position);
    PieceInterface <?> getPiece(Position position);
    void removePiece(Position position);
    boolean isFree(Position position);
    void move(Position origin, Position destination);
}
