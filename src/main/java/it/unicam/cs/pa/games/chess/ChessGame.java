package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.cli.CliMakeTurn;
import it.unicam.cs.pa.cli.chess.CliPromotionObserver;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Game;
import it.unicam.cs.pa.players.chess.ChessPlayer;
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
            //board.printBoard();
            while(!player.makeMove(cliMakeTurn.inputTurn())) {
                System.out.println("Mossa non valida");
                System.out.println("Turn of Player " + player.getName());
            }
            if(endOfGame())
                break;

        }
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
