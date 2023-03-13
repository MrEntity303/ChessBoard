package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.cli.CliMakeTurn;
import it.unicam.cs.pa.cli.chess.CliPromotionObserver;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Game;
import it.unicam.cs.pa.players.chess.ChessPlayer;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame extends Game {
    private final Scanner scanner;
    private final ChessPlayer[] players;
    private final ChessBoard board = ChessBoard.getInstance();

    private final CliMakeTurn cliMakeTurn;

    public ChessGame(Scanner scanner, ChessPlayer[] players) {
        this.scanner = scanner;
        this.cliMakeTurn= new CliMakeTurn(scanner);
        this.players = players;
    }

    @Override
    public void initialize() {
        super.initialize();
        initializePlayers();
        board.setPromotionObserver(new CliPromotionObserver(scanner));
        System.out.println("Inizializzazione del gioco di scacchi");
    }

    private void initializePlayers() {
        players[0].setColor(Color.WHITE);
        players[1].setColor(Color.BLACK);
    }

    @Override
    public void makeTurn() {
        for (ChessPlayer player:players) {
            System.out.println("Turn of Player " + player.getName());
            printBoard();
            while(!player.makeMove(cliMakeTurn.inputTurn())) {
                System.out.println("Mossa non valida");
                System.out.println("Turn of Player " + player.getName());
                printBoard();
            }
            if(endOfGame())
                break;

        }
    }

    @Override
    protected void printBoard() {
        System.out.println("Stato del gioco");
        System.out.println("    a    b    c    d    e    f    g    h");
        System.out.println("  +----+----+----+----+----+----+----+----+");
        int rowNumber = 1;
        for (ArrayList<ChessPiece> row : board.getBoard()) {
            System.out.print(rowNumber + " | ");
            for (ChessPiece piece : row)
                if (piece != null)
                    System.out.print(piece.getColor().equals(Color.WHITE) ? "W"+piece.getType().getValue() + " | " : "B"+piece.getType().getValue() + " | ");
                else
                    System.out.print("   | ");
            System.out.println(rowNumber++ + " ");
            System.out.println("  +----+----+----+----+----+----+----+----+");
        }
        System.out.println("    a    b    c    d    e    f    g    h\n");
    }

    @Override
    public boolean endOfGame() {
        return players[0].isCheckKing() || players[1].isCheckKing();
    }

    @Override
    public void printResult() {
        if(players[0].isCheckKing())
            System.out.println("Il giocatore " + players[1].getName() + " ha vinto");
        else if(players[1].isCheckKing())
            System.out.println("Il giocatore " + players[0].getName() + " ha vinto");
        else
            System.out.println("Il gioco è finito in parità");
    }
}
