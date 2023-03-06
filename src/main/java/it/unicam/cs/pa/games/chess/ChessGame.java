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
    protected boolean validator(PieceChess pieceChess, int x, int y) {
        return switch (pieceChess.getType()) {
            case KING -> kingValidator(pieceChess, x, y);
            case QUEEN -> queenValidator(pieceChess, x, y);
            case ROOK -> rookValidator(pieceChess, x, y);
            case BISHOP -> bishopValidator(pieceChess, x, y);
            case KNIGHT -> knightValidator(pieceChess, x, y);
            case PAWN -> pawnValidator(pieceChess, x, y);
        };
    }

    private boolean pawnValidator(PieceChess pieceChess, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean knightValidator(PieceChess pieceChess, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean bishopValidator(PieceChess pieceChess, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean rookValidator(PieceChess pieceChess, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean queenValidator(PieceChess pieceChess, int x, int y) {
        //TODO: implement
        return false;
    }

    private boolean kingValidator(PieceChess pieceChess, int x, int y) {
        //TODO: implement
        return false;
    }

    @Override
    protected void printResult() {
        System.out.println("Il gioco è finito");
    }
}
