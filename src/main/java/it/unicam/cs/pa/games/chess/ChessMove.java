package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.Position;

public class ChessMove extends Move {
    private boolean isCapture;
    private boolean isPromotion;
    private boolean isEnPassant;
    private boolean isCastling;


    public ChessMove(Position origin, Position destination) {
            super(origin, destination);
    }

    @Override
    public ChessMove isValid() {
        if(this.getDestination().x()<0|| this.getDestination().x()>7 || this.getDestination().y()<0 || this.getDestination().y()>7)
            return null;
        //Controlla se la mossa e' valida
        if(!ChessBoard.getInstance().isFree(this.getDestination()) && ChessBoard.getInstance().getPiece(this.getDestination()).getColor().equals(ChessBoard.getInstance().getPiece(this.getOrigin()).getColor()))
            return null;
        //Controlla se la mossa e' una cattura
        if(ChessBoard.getInstance().getPiece(this.getDestination()) != null && ChessBoard.getInstance().getPiece(this.getOrigin()).getColor().equals(ChessBoard.getInstance().getPiece(this.getOrigin()).getColor()))
            this.isCapture = true;
        //Controlla se la mossa e' una promozione
        if(ChessBoard.getInstance().getPiece(getOrigin()) != null
                && ChessBoard.getInstance().getPiece(getOrigin()).getType() == ChessPieceType.PAWN
                && (ChessBoard.getInstance().getPiece(this.getOrigin()).getColor().equals(Color.WHITE) ? this.getDestination().x() == 7 : this.getDestination().x() == 0))
            this.isPromotion = true;
//        if(ChessBoard.getInstance().getPiece(getOrigin()).getType() == ChessPieceType.PAWN && ChessBoard.getInstance().getPiece(this.getOrigin()).getColor().equals(Color.WHITE) ? this.getDestination().x() == 7 : this.getDestination().x() == 0)
//            this.isPromotion = true;
        //Controlla se la mossa e' un en passant

//        ChessBoard.getInstance().getPiece(this.getOrigin()).getList().stream().filter(p->p.getDestination().equals(this.getDestination())).findFirst().ifPresent(p->{
//            p.get
//            this.isCapture = true;});
        return this;
    }
    public boolean getIsCapture(){return this.isCapture;}
    public boolean getIsPromotion(){return this.isPromotion;}
    public boolean getIsEnPassant(){return this.isEnPassant;}
    public boolean getIsCastling(){return this.isCastling;}
}

////        //Controlla se la mossa e' una cattura
////        ChessBoard.getInstance().getPiece(this.getOrigin()).getList().stream().filter(p->p.getDestination().equals(this.getDestination())).findFirst().ifPresent(p->{
////            this.isCapture = true;});
////        //Controlla se la mossa e' una promozione
////        if(ChessBoard.getInstance().getPiece(getOrigin()).getType() == ChessPieceType.PAWN &&
////                ChessBoard.getInstance().getPiece(this.getOrigin()).getColor().equals(Color.WHITE) ? this.getDestination().x() == 7 : this.getDestination().x() == 0)
////            this.isPromotion = true;
//        //Controlla se la mossa e' un en passant
////        if(ChessBoard.getInstance().getPiece(getOrigin()).getType() == ChessPieceType.PAWN &&
//
//        return this;
//        //TODO: implement (deve controllare se la mossa e valida ed il tipo di mossa)
