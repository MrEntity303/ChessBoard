package it.unicam.cs.pa.players.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.Piece;
import it.unicam.cs.pa.games.chess.ChessBoard;
import it.unicam.cs.pa.games.chess.ChessPiece;
import it.unicam.cs.pa.players.Player;

public class ChessPlayer extends Player {
    private Piece<ChessPiece> king;

    public ChessPlayer(String name, Color color)
    {
        super(name, color);
    }
    public ChessPlayer(String name)
    {
        super(name);
    }

    @Override
    public boolean makeMove(Move move) {
        return ChessBoard.getInstance().move(move.getOrigin(), move.getDestination(), this.getColor());
    }

    public Piece<? extends Piece<ChessPiece>> getKing() {
        return null;
    }

    public boolean isCheckKing() {
        return false;
    }
    //TODO: implementare il metodo isCheckKing metodo dello scacco
}
