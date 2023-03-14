package it.unicam.cs.pa.players;

import it.unicam.cs.pa.cli.CliMakeTurn;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Position;
import it.unicam.cs.pa.games.chess.ChessBoard;
import it.unicam.cs.pa.players.chess.ChessPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessPlayerTest {
    @Test
    public void isCheckingTest() {
        ChessBoard board = ChessBoard.getInstance();
        board.resetBoard();
        ChessPlayer player = new ChessPlayer("Player1", Color.WHITE);
        ChessPlayer player2 = new ChessPlayer("Player2", Color.BLACK);
        board.move(new Position(1, 5), new Position(2,5), Color.WHITE);
        //board.move(new Position(1, 6), new Position(3,6), Color.WHITE);
        board.move(new Position(6, 4), new Position(5,4), Color.BLACK);
        board.move(new Position(7, 3), new Position(3,7), Color.BLACK);
        assertTrue(player.isCheckKing());
        board.move(new Position(1, 6), new Position(2,6), Color.WHITE);
        assertFalse(player.isCheckKing());
    }
}
