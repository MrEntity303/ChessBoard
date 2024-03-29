package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.cli.chess.CliPromotionObserver;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Position;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
    @Test
    public void testWhitePiece() {
        ChessBoard board = ChessBoard.getInstance();
        board.resetBoard();
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
        board.resetBoard();
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
        board.resetBoard();
        for (int i = 0; i < 8; i++) {
            assertEquals(board.getPiece(new Position(1,i)).getType(), ChessPieceType.PAWN);
            assertEquals(board.getPiece(new Position(1,i)).getColor(), Color.WHITE);
            assertEquals(board.getPiece(new Position(6,i)).getType(), ChessPieceType.PAWN);
            assertEquals(board.getPiece(new Position(6,i)).getColor(), Color.BLACK);
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
        board.resetBoard();
        assertTrue(board.isFree(new Position(2, 2)));
        assertTrue(board.isFree(new Position(3, 3)));
        assertTrue(board.isFree(new Position(4, 4)));
        assertTrue(board.isFree(new Position(5, 5)));
    }
    @Test
    public void move(){
        ChessBoard board = ChessBoard.getInstance();
        board.resetBoard();
        board.move(new Position(1, 7), new Position(3, 7), Color.WHITE);
        board.move(new Position(6, 6), new Position(4, 6), Color.BLACK);
        assertEquals(board.getPiece(new Position(4, 6)).getType(), ChessPieceType.PAWN);
        assertEquals(board.getPiece(new Position(4, 6)).getColor(), Color.BLACK);
        assertEquals(board.getPiece(new Position(3, 7)).getType(), ChessPieceType.PAWN);
        assertEquals(board.getPiece(new Position(3, 7)).getColor(), Color.WHITE);
        assertTrue(board.isFree(new Position(1, 7)));
    }
    @Test
    public void moveIsCapturePawn(){
        ChessBoard board = ChessBoard.getInstance();
        board.resetBoard();
        board.move(new Position(1, 7), new Position(3, 7), Color.WHITE);
        board.move(new Position(6, 6), new Position(4, 6), Color.BLACK);
        board.move(new Position(3, 7), new Position(4, 6), Color.WHITE);
        assertEquals(board.getObservers().size(), 31);
        assertEquals(board.getPiece(new Position(4, 6)).getType(), ChessPieceType.PAWN);
        assertEquals(board.getPiece(new Position(4, 6)).getColor(), Color.WHITE);
        assertTrue(board.isFree(new Position(1, 7)));
    }
    @Test
    public void moveIsPromotionPawn() {
        String inputString = "queen";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        Scanner scanner = new Scanner(inputStream);
        ChessBoard board = ChessBoard.getInstance();
        board.setPromotionObserver(new CliPromotionObserver(scanner));
        board.resetBoard();
        board.removeObserver(board.getPiece(new Position(0, 0)));
        board.removeObserver(board.getPiece(new Position(1, 0)));
        board.removePiece(new Position(0, 0));
        board.removePiece(new Position(1, 0));
        board.move(new Position(6, 0), new Position(4, 0), Color.BLACK);
        board.move(new Position(4, 0), new Position(3, 0), Color.BLACK);
        board.move(new Position(3, 0), new Position(2, 0), Color.BLACK);
        board.move(new Position(2, 0), new Position(1, 0), Color.BLACK);
        board.move(new Position(1, 0), new Position(0, 0), Color.BLACK);
        assertNotNull(board.getPiece(new Position(0, 0)));
        assertEquals(board.getPiece(new Position(0, 0)).getType(), ChessPieceType.QUEEN);
    }
    @Test
    public void checkPawnIsBlock(){
        ChessBoard board = ChessBoard.getInstance();
        board.resetBoard();
        board.move(new Position(1, 0), new Position(3, 0), Color.WHITE);
        board.move(new Position(6, 0), new Position(4, 0), Color.BLACK);
        assertTrue(board.isFree(new Position(2, 0)));
        assertFalse(board.isFree(new Position(3, 0)));
        assertTrue(board.isFree(new Position(5, 0)));
        assertFalse(board.isFree(new Position(4, 0)));
    }
    // una pedina non puo spostarsi se il RE va sotto scacco
    @Test
    public void invalidMove(){
        ChessBoard board = ChessBoard.getInstance();
        board.resetBoard();
        board.removeObserver(board.getPiece(new Position(6, 4)));
        board.removePiece(new Position(6, 4));
        board.move(new Position(6, 3), new Position(4, 3), Color.BLACK);
        board.move(new Position(1, 4), new Position(3, 4), Color.WHITE);
        board.move(new Position(7, 3), new Position(6, 4), Color.BLACK);
        assertFalse(board.move(new Position(3, 4), new Position(4, 3), Color.WHITE));
    }
}
