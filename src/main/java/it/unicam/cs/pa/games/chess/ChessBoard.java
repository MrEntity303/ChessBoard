package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Board;
import it.unicam.cs.pa.games.PieceInterface;

public class ChessBoard implements Board {

    private PieceChess[][] board;
    private int width;
    private int height;

    @Override
    public int getWidth() {
        //TODO: implement
        return 0;
    }

    @Override
    public int getHeight() {
        //TODO: implement
        return 0;
    }

    @Override
    public <T> void setPiece(PieceInterface<T> piece, int x, int y) {
        //TODO: implement
    }

    @Override
    public <T> PieceInterface<T> getPiece(int x, int y) {
        //TODO: implement
        return null;
    }

    @Override
    public void removePiece(int x, int y) {
        //TODO: implement
    }

    @Override
    public boolean isFree(int x, int y) {
        //TODO: implement
        return false;
    }

    @Override
    public void move(int x, int y) {
        //TODO: implement
    }
}
