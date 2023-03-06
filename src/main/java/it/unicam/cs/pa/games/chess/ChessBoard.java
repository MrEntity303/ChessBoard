package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Board;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.PieceInterface;
import java.util.ArrayList;
public class ChessBoard implements Board {
    private final ArrayList<ArrayList<PieceChess>> board;

    /**
     * Create a new ChessBoard
     *      @param width the width of the board
     *      @param height the height of the board
     **/
    private ChessBoard(int width, int height) {
        if(width < 1 || height < 1)
            throw new IllegalArgumentException("Width and height must be greater than 0");

        this.board = new ArrayList<>();
        for(int i = 0; i < width; i++) {
        	this.board.add(new ArrayList<PieceChess>());
        	for(int j = 0; j < height; j++) {
        		this.board.get(i).add(null);
        	}
        }
    }
    public ChessBoard(){
        this(8, 8);
        this.setupBoard();
    }

    /**
     * Set up the board with the pieces in the correct position
     **/
    private void setupBoard() {
        this.setupBlackPieces();
        this.setupWhitePieces();
        this.setupPawns();
    }

    /**
     * Get the width of the board
     *      @return the width of the board
     **/
    @Override
    public int getWight(){
        return this.board.size();
    }

    /**
     * Get the height of the board
     *      @return the height of the board
     **/
    @Override
    public int getHeight() {
        return this.board.get(0).size();
    }

    /**
     * Set a piece in the board
     *      @param piece the piece to set
     *      @param x the x coordinate
     *      @param y the y coordinate
     **/
    @Override
    public <T> void setPiece(PieceInterface<T> piece, int x, int y) {
        this.board.get(x).set(y, (PieceChess) piece);
    }

    /**
     * Get the piece in the specified position
     *      @param x the x coordinate
     *      @param y the y coordinate
     *      @return the piece in the specified position
     **/
    @Override
    public PieceChess getPiece(int x, int y) {
        return this.board.get(x).get(y);
    }

    /**
     * Remove the piece in the specified position
     *      @param x the x coordinate
     *      @param y the y coordinate
     **/
    @Override
    public void removePiece(int x, int y) {
        this.board.get(x).remove(y);
    }

    /**
     * Check if the specified position is free
     *      @param x the x coordinate
     *      @param y the y coordinate
     *      @return true if the position is free, false otherwise
     **/
    @Override
    public boolean isFree(int x, int y) {
        return this.board.get(x).get(y) == null;
    }

    /**
     * Move a piece from the origin position to the destination position
     *      @param x_origin the x coordinate of the origin position
     *      @param y_origin the y coordinate of the origin position
     *      @param x_dest the x coordinate of the destination position
     *      @param y_dest the y coordinate of the destination position
     **/
    @Override
    public void move(int x_origin, int y_origin, int x_dest, int y_dest) {
    	PieceChess piece = this.getPiece(x_origin, y_origin);
    	this.removePiece(x_origin, y_origin);
    	this.setPiece(piece, x_dest, y_dest);
    }


    /**
     * Set up the black pieces in the correct position
     **/
    private void setupBlackPieces() {
        //Posizione le torri nere
        this.setPiece(new PieceChess(PieceChessType.ROOK, Color.BLACK), 7, 0);
        this.setPiece(new PieceChess(PieceChessType.ROOK, Color.BLACK), 7, 7);
        //Posizione i cavalli neri
        this.setPiece(new PieceChess(PieceChessType.KNIGHT, Color.BLACK), 7, 1);
        this.setPiece(new PieceChess(PieceChessType.KNIGHT, Color.BLACK), 7, 6);
        //Posizione i alfieri neri
        this.setPiece(new PieceChess(PieceChessType.BISHOP, Color.BLACK), 7, 2);
        this.setPiece(new PieceChess(PieceChessType.BISHOP, Color.BLACK), 7, 5);
        //Posizione il re nero
        this.setPiece(new PieceChess(PieceChessType.KING, Color.BLACK), 7, 4);
        //Posizione la regina nera
        this.setPiece(new PieceChess(PieceChessType.QUEEN, Color.BLACK), 7, 3);
    }

    /**
     * Set up the white pieces in the correct position
     **/
    private void setupWhitePieces() {
        //Posizione le torri bianche
        this.setPiece(new PieceChess(PieceChessType.ROOK, Color.WHITE), 0, 0);
        this.setPiece(new PieceChess(PieceChessType.ROOK, Color.WHITE), 0, 7);
        //Posizione i cavalli bianchi
        this.setPiece(new PieceChess(PieceChessType.KNIGHT, Color.WHITE), 0, 1);
        this.setPiece(new PieceChess(PieceChessType.KNIGHT, Color.WHITE), 0, 6);
        //Posizione i alfieri bianchi
        this.setPiece(new PieceChess(PieceChessType.BISHOP, Color.WHITE), 0, 2);
        this.setPiece(new PieceChess(PieceChessType.BISHOP, Color.WHITE), 0, 5);
        //Posizione la regina bianca
        this.setPiece(new PieceChess(PieceChessType.QUEEN, Color.WHITE), 0, 3);
        //Posizione il re bianco
        this.setPiece(new PieceChess(PieceChessType.KING, Color.WHITE), 0, 4);
    }

    /**
     * Set up the pawns in the correct position
     **/
    private void setupPawns() {
        //Posizione i pedoni bianchi e neri
        for(int i = 0; i < 8; i++) {
            this.setPiece(new PieceChess(PieceChessType.PAWN, Color.WHITE), 1, i);
            this.setPiece(new PieceChess(PieceChessType.PAWN, Color.BLACK), 6, i);
        }
    }

}
