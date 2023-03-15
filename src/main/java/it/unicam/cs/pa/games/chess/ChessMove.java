package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.Position;

public class ChessMove extends Move {
    private boolean isCapture;
    private boolean isPromotion;

    public ChessMove(Position origin, Position destination) {
            super(origin, destination);
    }

    @Override
    public ChessMove isValid() {
        //Controlla se la destinazione e' sulla scacchiera
        if(!ChessBoard.getInstance().onBoard(this.getDestination()))
            return null;
        //Controlla se la lo spazio di destinazione e' occupato, e se e' occupato da una pedina dello stesso colore
        if(!ChessBoard.getInstance().isFree(this.getDestination())
                && ChessBoard.getInstance().getPiece(this.getDestination()).getColor().equals(ChessBoard.getInstance().getPiece(this.getOrigin()).getColor()))
            return null;
        //Controlla se la mossa e' una cattura
        this.setIsCapture();
        //Controlla se la mossa e' una promozione
        this.setIsPromotion();

        return this;
    }
    // region Getters and Setters
    public boolean getIsCapture(){return this.isCapture;}
    public boolean getIsPromotion(){return this.isPromotion;}

    public void setIsCapture(){
        if(ChessBoard.getInstance().getPiece(this.getDestination()) != null
                && ChessBoard.getInstance().getPiece(this.getOrigin()).getColor().equals(ChessBoard.getInstance().getPiece(this.getOrigin()).getColor()))
            this.isCapture = true;
    }
    public void setIsPromotion(){
        if(ChessBoard.getInstance().getPiece(getOrigin()) != null
                && ChessBoard.getInstance().getPiece(getOrigin()).getType() == ChessPieceType.PAWN
                && (ChessBoard.getInstance().getPiece(this.getOrigin()).getColor().equals(Color.WHITE) ? this.getDestination().x() == 7 : this.getDestination().x() == 0))
            this.isPromotion = true;
    }
    //endregion
}

