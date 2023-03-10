package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Game;
public class ChessGame extends Game {
    private final ChessBoard board = ChessBoard.getInstance();

    @Override
    protected void initialize() {
        super.initialize();
        System.out.println("Inizializzazione del gioco di scacchi");
    }

    @Override
    protected void makeTurn() {
        System.out.println("Turno del giocatore");
    }

    @Override
    protected boolean endOfGame() {
        return false;
    }

    @Override
    protected void printResult() {
        System.out.println("Il gioco è finito");
    }
}
