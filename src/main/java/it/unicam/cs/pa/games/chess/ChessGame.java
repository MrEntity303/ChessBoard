package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Game;
import it.unicam.cs.pa.games.Position;

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

//    @Override
//    protected boolean validator(ChessPiece chessPiece, Position origin, Position destination) {
//        return switch (chessPiece.getType()) {
//            case KING -> kingValidator(chessPiece, origin, destination);
//            case QUEEN -> queenValidator(chessPiece, origin, destination);
//            case ROOK -> rookValidator(chessPiece, origin, destination);
//            case BISHOP -> bishopValidator(chessPiece, origin, destination);
//            case KNIGHT -> knightValidator(chessPiece, origin, destination);
//            case PAWN -> pawnValidator(chessPiece, origin, destination);
//        };
//    }
//
//    private boolean pawnValidator(ChessPiece chessPiece, Position origin, Position destination){
//        //TODO: implement
//        return false;
//    }
//
//    private boolean knightValidator(ChessPiece chessPiece, Position origin, Position destination) {
//        //TODO: implement
//        return false;
//    }
//
//    private boolean bishopValidator(ChessPiece chessPiece, Position origin, Position destination) {
//        //TODO: implement
//        return false;
//    }
//
//    private boolean rookValidator(ChessPiece chessPiece, Position origin, Position destination) {
//        //TODO: implement
//        return false;
//    }
//
//    private boolean queenValidator(ChessPiece chessPiece, Position origin, Position destination) {
//        //TODO: implement
//        return false;
//    }
//
//    private boolean kingValidator(ChessPiece chessPiece, Position origin, Position destination) {
//        //TODO: implement
//        return false;
//    }

    @Override
    protected void printResult() {
        System.out.println("Il gioco è finito");
    }
}
