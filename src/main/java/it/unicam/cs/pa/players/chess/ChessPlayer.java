package it.unicam.cs.pa.players.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.Piece;
import it.unicam.cs.pa.games.chess.ChessBoard;
import it.unicam.cs.pa.games.chess.ChessPiece;
import it.unicam.cs.pa.games.chess.ChessPieceType;
import it.unicam.cs.pa.players.Player;

public class ChessPlayer extends Player {
    private ChessPiece king;
    private boolean checkKing;

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
//        this.setCheckKing();
        return ChessBoard.getInstance().move(move.getOrigin(), move.getDestination(), this.getColor());
    }

    public void setKing() {
        ChessBoard.getInstance().getObservers().forEach(piece -> {
            if (piece.getType() == ChessPieceType.KING && piece.getColor() == this.getColor())
                king = piece;
        });
    }

    public void getOppositeKing() {
        ChessBoard.getInstance().getObservers().forEach(piece -> {
            if (piece.getType() == ChessPieceType.KING && piece.getColor() != this.getColor())
                king = piece;
        });
    }
    public ChessPiece getKing() {
        if (king == null)
            setKing();
        return king;
    }

//    public void setCheckKing() {
//        ChessPiece piece = ChessBoard.getInstance().getPiece(ChessBoard.getInstance().lastMove().getDestination());
//        this.checkKing = piece.getList().stream().anyMatch(move -> move.getDestination().equals(ChessBoard.getInstance().getPosition(this.getKing())));
//    }

    public boolean isCheckKing() {
        ChessPiece piece = ChessBoard.getInstance().getPiece(ChessBoard.getInstance().lastMove().getDestination());
        return piece.getList().stream().anyMatch(move -> move.getDestination().equals(ChessBoard.getInstance().getPosition(this.getKing())));
    }

//        for (ChessPiece piece: ChessBoard.getInstance().getObservers()) {
//            if(piece.getList().stream().anyMatch(move -> move.getDestination().equals(ChessBoard.getInstance().getPosition(this.getKing()))))
//                return true;
//        }
//        return false;


//        return getKing()
//                .getList()
//                .stream()
//                .anyMatch(move -> move.getDestination().equals(ChessBoard.getInstance().getPosition(this.getKing())));
    }
    //TODO: implementare il metodo isCheckKing metodo dello scacco


