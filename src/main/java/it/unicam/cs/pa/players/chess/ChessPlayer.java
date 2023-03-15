package it.unicam.cs.pa.players.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.chess.ChessBoard;
import it.unicam.cs.pa.games.chess.ChessPiece;
import it.unicam.cs.pa.games.chess.ChessPieceType;
import it.unicam.cs.pa.players.Player;

public class ChessPlayer extends Player {
    private ChessPiece king;
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

    /**
     * Set the king of the player
     */
    public void setKing() {
        ChessBoard.getInstance().getObservers().forEach(piece -> {
            if (piece.getType() == ChessPieceType.KING && piece.getColor() == this.getColor())
                king = piece;
        });
    }

    /**
     * Get the king of the player if it is set, otherwise set it
     * @return the king of the player
     */
    public ChessPiece getKing() {
        if (king == null)
            setKing();
        return king;
    }

    /**
     * Check if the king is in check
     * @return true if the king is in check, false otherwise
     */
    public boolean isCheckKing() {
        ChessPiece piece = ChessBoard.getInstance().getPiece(ChessBoard.getInstance().lastMove().getDestination());
        return piece.getList().stream().anyMatch(move -> move.getDestination().equals(ChessBoard.getInstance().getPosition(this.getKing())));
    }
}

