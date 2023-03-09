package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
    @Test
    public void testWhitePiece() {
        ChessBoard board = ChessBoard.getInstance();
        assertEquals(board.getPiece(new Position(0,0)).getType(), ChessPieceType.ROOK);
        assertEquals(board.getPiece(new Position(0,0)).getColor(), Color.WHITE);
        assertEquals(board.getPiece(new Position(0,7)).getType(), ChessPieceType.ROOK);
        assertEquals(board.getPiece(new Position(0,7)).getColor(), Color.WHITE);
        assertEquals(board.getPiece(new Position(0,1)).getType(), ChessPieceType.KNIGHT);
        assertEquals(board.getPiece(new Position(0,1)).getColor(), Color.WHITE);
        assertEquals(board.getPiece(new Position(0,6)).getType(), ChessPieceType.KNIGHT);
        assertEquals(board.getPiece(new Position(0,6)).getColor(), Color.WHITE);
        assertEquals(board.getPiece(new Position(0,2)).getType(), ChessPieceType.BISHOP);
        assertEquals(board.getPiece(new Position(0,2)).getColor(), Color.WHITE);
        assertEquals(board.getPiece(new Position(0,5)).getType(), ChessPieceType.BISHOP);
        assertEquals(board.getPiece(new Position(0,5)).getColor(), Color.WHITE);
        assertEquals(board.getPiece(new Position(0,3)).getType(), ChessPieceType.QUEEN);
        assertEquals(board.getPiece(new Position(0,3)).getColor(), Color.WHITE);
        assertEquals(board.getPiece(new Position(0,4)).getType(), ChessPieceType.KING);
        assertEquals(board.getPiece(new Position(0,4)).getColor(), Color.WHITE);
    }

    @Test
    public void testBlackPiece() {
        ChessBoard board = ChessBoard.getInstance();
        assertEquals(board.getPiece(new Position(7,0)).getType(), ChessPieceType.ROOK);
        assertEquals(board.getPiece(new Position(7,0)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(7,7)).getType(), ChessPieceType.ROOK);
        assertEquals(board.getPiece(new Position(7,7)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(7,1)).getType(), ChessPieceType.KNIGHT);
        assertEquals(board.getPiece(new Position(7,1)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(7,6)).getType(), ChessPieceType.KNIGHT);
        assertEquals(board.getPiece(new Position(7,6)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(7,2)).getType(), ChessPieceType.BISHOP);
        assertEquals(board.getPiece(new Position(7,2)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(7,5)).getType(), ChessPieceType.BISHOP);
        assertEquals(board.getPiece(new Position(7,5)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(7,3)).getType(), ChessPieceType.QUEEN);
        assertEquals(board.getPiece(new Position(7,3)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(7,4)).getType(), ChessPieceType.KING);
        assertEquals(board.getPiece(new Position(7,4)).getColor(), Color.BLACK);
    }

    @Test
    public void testWhiteAndBlackPawn() {
        ChessBoard board = ChessBoard.getInstance();
        for (int i = 0; i < 8; i++) {
            if(i==1){//essendo che la scacchiera e' unica adesso la pedina e' stata spostata secondo il test move
            assertEquals(board.getPiece(new Position(1,i)).getType(), ChessPieceType.PAWN);
            assertEquals(board.getPiece(new Position(1,i)).getColor(), Color.WHITE);}
            if(!(i==6)){
            assertEquals(board.getPiece(new Position(6,i)).getType(), ChessPieceType.PAWN);
            assertEquals(board.getPiece(new Position(6,i)).getColor(), Color.BLACK);}
        }
    }

    @Test
    public void getWightAndHeight(){
        ChessBoard board = ChessBoard.getInstance();
        assertEquals(board.getWight(), 8);
        assertEquals(board.getHeight(), 8);
    }

    @Test
    public void testIsFree(){
        ChessBoard board = ChessBoard.getInstance();
        assertTrue(board.isFree(new Position(2, 2)));
        assertTrue(board.isFree(new Position(3, 3)));
        assertTrue(board.isFree(new Position(4, 4)));
        assertTrue(board.isFree(new Position(5, 5)));
    }

    @Test
    public void move(){
        ChessBoard board = ChessBoard.getInstance();
        //board.move(new Position(1, 0), new Position(3, 0));
        //board.getPiece(new Position(1, 0)).addMove(new ChessMove (new Position(1, 0), new Position(3, 0)));
        //System.out.println(board.getPiece(new Position(1, 0)));
//        ChessPiece piece = new ChessPiece(ChessPieceType.PAWN, Color.BLACK);
//        board.setPiece(piece,new Position(2, 1));
//        board.addObserver(piece);
        board.move(new Position(1, 7), new Position(3, 7));
        board.move(new Position(6, 6), new Position(4, 6));
        assertEquals(board.getPiece(new Position(3, 7)).getType(), ChessPieceType.PAWN);
        assertEquals(board.getPiece(new Position(3, 7)).getColor(), Color.WHITE);
        assertTrue(board.isFree(new Position(1, 7)));
    }




}
