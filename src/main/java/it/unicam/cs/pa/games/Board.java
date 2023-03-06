package it.unicam.cs.pa.games;

public interface Board {
    int getWight();
    int getHeight();
    <T> void setPiece(PieceInterface<T> piece, int x, int y);
    PieceInterface <?> getPiece(int x, int y);
    void removePiece(int x, int y);
    boolean isFree(int x, int y);
    void move(int x_origin, int y_origin, int x_dest, int y_dest);
}
