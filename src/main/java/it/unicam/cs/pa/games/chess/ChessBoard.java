package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Board;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.PieceInterface;
import it.unicam.cs.pa.games.Position;

import java.util.ArrayList;
public class ChessBoard implements Board {
    private final ArrayList<ArrayList<ChessPiece>> board;

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
        	this.board.add(new ArrayList<>());
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
     * Set up the white pieces in the correct position
     * @param piece the piece to set
     * @param position the position where to set the piece
     **/
    @Override
    public <T> void setPiece(PieceInterface<T> piece, Position position) {this.board.get(position.x()).set(position.y(), (ChessPiece) piece);
    }

    /**
     * Get the piece in the specified position
     *      @param position the position
     *      @return the piece in the specified position
     **/
    @Override
    public ChessPiece getPiece(Position position) {return this.board.get(position.x()).get(position.y());
    }

    /**
     * Remove the piece in the specified position
     *      @param position the position
     **/
    @Override
    public void removePiece(Position position){this.board.get(position.x()).set(position.y(), null);}

    /**
     * Check if the specified position is free
     *      @param position the position
     *      @return true if the position is free, false otherwise
     **/
    @Override
    public boolean isFree(Position position){
        return this.board.get(position.x()).get(position.y()) == null;
    }

    /**
     * Move a piece from the origin position to the destination position
     *      @param origin the origin position
     *      @param destination the destination position
     **/
    @Override
    public void move(Position origin, Position destination) {
    	ChessPiece piece = this.getPiece(origin);
    	this.removePiece(origin);
    	this.setPiece(piece, destination);
    }


    /**
     * Set up the black pieces in the correct position
     **/
    private void setupBlackPieces() {
        //Posizione le torri nere
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.BLACK), new Position(7,0));//7, 0);
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.BLACK), new Position(7,7));
        //Posizione i cavalli neri
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.BLACK), new Position(7,1));//7, 1);
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.BLACK), new Position(7, 6));//7, 6);
        //Posizione i alfieri neri
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.BLACK), new Position(7, 2));//7, 2);
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.BLACK), new Position(7, 5));//7, 5);
        //Posizione il re nero
        this.setPiece(new ChessPiece(ChessPieceType.KING, Color.BLACK), new Position(7, 4));//7, 4);
        //Posizione la regina nera
        this.setPiece(new ChessPiece(ChessPieceType.QUEEN, Color.BLACK), new Position(7, 3));//7, 3);
    }

    /**
     * Set up the white pieces in the correct position
     **/
    private void setupWhitePieces() {
        //Posizione le torri bianche
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.WHITE), new Position(0,0));//0, 0);
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.WHITE), new Position(0,7));//0, 7);
        //Posizione i cavalli bianchi
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.WHITE), new Position(0,1));//0, 1);
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.WHITE), new Position(0, 6));//0, 6);
        //Posizione i alfieri bianchi
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.WHITE), new Position(0, 2));//0, 2);
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.WHITE), new Position(0,5));//0, 5);
        //Posizione la regina bianca
        this.setPiece(new ChessPiece(ChessPieceType.QUEEN, Color.WHITE), new Position(0,3));//0, 3);
        //Posizione il re bianco
        this.setPiece(new ChessPiece(ChessPieceType.KING, Color.WHITE), new Position(0,4));//0, 4);
    }

    /**
     * Set up the pawns in the correct position
     **/
    private void setupPawns() {
        //Posizione i pedoni bianchi e neri
        for(int i = 0; i < 8; i++) {
            this.setPiece(new ChessPiece(ChessPieceType.PAWN, Color.WHITE), new Position(1,i));//1, i);
            this.setPiece(new ChessPiece(ChessPieceType.PAWN, Color.BLACK), new Position(6,i));//6, i);
        }
    }

}
