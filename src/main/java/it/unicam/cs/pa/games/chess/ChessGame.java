package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Game;

public class ChessGame extends Game {

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
    protected boolean validator(ChessPiece chessPiece, int x, int y) {
        return switch (chessPiece.getType()) {
            case KING -> kingValidator(chessPiece, x, y);
            case QUEEN -> queenValidator(chessPiece, x, y);
            case ROOK -> rookValidator(chessPiece, x, y);
            case BISHOP -> bishopValidator(chessPiece, x, y);
            case KNIGHT -> knightValidator(chessPiece, x, y);
            case PAWN -> pawnValidator(chessPiece, x, y);
        };
    }

    private boolean pawnValidator(ChessPiece chessPiece, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean knightValidator(ChessPiece chessPiece, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean bishopValidator(ChessPiece chessPiece, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean rookValidator(ChessPiece chessPiece, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean queenValidator(ChessPiece chessPiece, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean kingValidator(ChessPiece chessPiece, int x, int y) {
        //TODO: implement
        return false;
    }

    @Override
    protected void printResult() {
        System.out.println("Il gioco è finito");
    }
}
