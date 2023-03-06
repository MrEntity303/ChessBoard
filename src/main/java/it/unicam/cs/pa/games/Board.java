package it.unicam.cs.pa.games;

public interface Board {
    int getWidth();
    int getHeight();
    <T> void setPiece(PieceInterface<T> piece, int x, int y);
    <T> PieceInterface<T> getPiece(int x, int y);
    void removePiece(int x, int y);
    boolean isFree(int x, int y);
    void move(int x, int y);
}
