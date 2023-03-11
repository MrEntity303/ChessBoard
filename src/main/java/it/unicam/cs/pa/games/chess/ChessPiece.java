package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.*;

import java.util.ArrayList;
import java.util.List;

public class ChessPiece implements Piece<ChessPieceType>, Observer {
    private ChessPieceType type;
    private final Color color;
    private List<ChessMove> moves = new ArrayList<>();

    public ChessPiece(ChessPieceType type, Color color) {
        this.type = type;
        this.color = color;
    }
    public void setType(ChessPieceType type) {
        this.type = type;
    }

    @Override
    public ChessPieceType getType() {
        return type;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void addMove(Move move) {
        if(move!=null)
            moves.add((ChessMove) move);
    }
    @Override
    public List<ChessMove> getList() {
        return this.moves;
    }

    @Override
    public void update() {moves = this.calculateMoves();}

    public List<ChessMove> calculateMoves() {
        return switch (this.getType()) {
            case KING -> kingMoves();
            case QUEEN -> queenMoves();
            case ROOK -> rookMoves();
            case BISHOP -> bishopMoves();
            case KNIGHT -> knightMoves();
            case PAWN -> pawnMoves();
        };
    }

    private List<ChessMove> pawnMoves() {
        //moves.removeIf(ChessMove::getIsEnPassant);
        moves.clear();
        //resetMoveList();
        Position positionPiece = ChessBoard.getInstance().getPosition(this);
        //Controllo se il pedone è in posizione iniziale e puo effettuare 2 mosse
        if((this.getColor()==Color.BLACK)&&(positionPiece.x()==6) ||this.getColor()==Color.WHITE&&(positionPiece.x()==1)){

            for(int i = 0; i < 2;i++)
                if(ChessBoard.getInstance().isFree(this.frontMoves(positionPiece, new Position(positionPiece.x() + i * this.getColor().getValue(), positionPiece.y())).getDestination()))
                    addMove(this.frontMoves(positionPiece, new Position(positionPiece.x() + i *this.getColor().getValue(), positionPiece.y())).isValid());
            checkRightAndLeftPawn();
        }else{
            if(ChessBoard.getInstance().isFree(this.frontMoves(positionPiece,positionPiece).getDestination()))
                addMove(this.frontMoves(positionPiece,positionPiece).isValid());
            checkRightAndLeftPawn();
        }
//        moves.removeIf(ChessMove::getIsEnPassant);
        return moves;
    }

//    private void resetMoveList()
//    {
//        moves.removeIf(ChessMove::getIsEnPassant)
//    }

    private void checkRightAndLeftPawn() {
        Position positionPiece = ChessBoard.getInstance().getPosition(this);
        if(ChessBoard.getInstance().onBoard(this.frontRightMoves(positionPiece,positionPiece).getDestination()) && !ChessBoard.getInstance().isFree(this.frontRightMoves(positionPiece,positionPiece).getDestination()))
            addMove(this.frontRightMoves(positionPiece,positionPiece).isValid());
        if(ChessBoard.getInstance().onBoard(this.frontLeftMoves(positionPiece,positionPiece).getDestination()) && !ChessBoard.getInstance().isFree(this.frontLeftMoves(positionPiece,positionPiece).getDestination()))
            addMove(this.frontLeftMoves(positionPiece,positionPiece).isValid());
    }

    private List<ChessMove> knightMoves() {
        //TODO: implement
        return null;
    }

    private List<ChessMove> bishopMoves() {
        //TODO: implement
        return null;
    }

    private List<ChessMove> rookMoves() {
        //TODO: implement
        return null;
    }

    private List<ChessMove> queenMoves() {
        //TODO: implement
        return null;
    }

    private List<ChessMove> kingMoves() {
        //TODO: implement
        return null;
    }

    //region Moves
    /**
     * Check if the piece can move forward
     *      @param position the position to check
     */
    private ChessMove frontMoves(Position origin, Position position) {
        return new ChessMove(origin,new Position(position.x() + color.getValue(),position.y()));


//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//            if(ChessBoard.getInstance().isFree(position.x() + color.getValue(),position.y()))
//                return addMove(position, new Position(position.x() + color.getValue(), position.y())).;
////            else if(ChessBoard.getInstance().getPiece(position.x()-1,position.y()).getColor() == Color.BLACK)
////                return addMove(position, new Position(position.x()-1, position.y()));
//        return false;
    }
    /**
     * Check if the piece can move rightward
     *      @param position the position to check
     */
    private ChessMove rightMoves(Position origin,Position position) {
        return new ChessMove(origin, new Position(position.x(),position.y()+ color.getValue()));
//        if(ChessBoard.getInstance().isFree(position.x(),position.y()+ color.getValue()))
//            return addMove(position, new Position(position.x(), position.y()+ color.getValue()));
//        return false;



//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//        if(ChessBoard.getInstance().getPiece(position).getColor() == Color.WHITE)
//            if(ChessBoard.getInstance().isFree(position.x(),position.y()+1))
//                return addMove(position, new Position(position.x(), position.y()+1));
//        else //if piece is black
//            if(ChessBoard.getInstance().isFree(position.x(),position.y()-1))
//                return addMove(position, new Position(position.x(), position.y()-1));
//        return false;
    }
    /**
     * Check if the piece can move leftward
     *      @param position the position to check
     */
    private ChessMove leftMoves(Position origin, Position position) {
        return new ChessMove(origin, new Position(position.x(),position.y()+ color.getValue()*-1));
//        if(ChessBoard.getInstance().isFree(position.x(),position.y()+ color.getValue()*-1))
//            return addMove(position, new Position(position.x(), position.y()+ color.getValue()*-1));
//        return false;



//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//        if(ChessBoard.getInstance().getPiece(position).getColor() == Color.WHITE)
//            if(ChessBoard.getInstance().isFree(position.x(),position.y()-1))
//                return addMove(position, new Position(position.x(), position.y()-1));
//        else //if piece is black
//            if(ChessBoard.getInstance().isFree(position.x(),position.y()+1))
//                return addMove(position, new Position(position.x(), position.y()+1));
//        return false;
    }

    /**
     * Check if the piece can move backward
     *      @param position the position to check
     */
    private ChessMove backMoves(Position origin, Position position) {
        return new ChessMove(origin, new Position(position.x() + color.getValue()*-1,position.y()));
//        if(ChessBoard.getInstance().isFree(position.x() + color.getValue()*-1,position.y()))
//            return addMove(position, new Position(position.x() + color.getValue()*-1, position.y()));
//        return false;



//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//        if(ChessBoard.getInstance().getPiece(position).getColor() == Color.WHITE)
//            if(ChessBoard.getInstance().isFree(position.x()-1,position.y()))
//                return addMove(position, new Position(position.x()-1, position.y()));
//        else //if piece is black
//            if(ChessBoard.getInstance().isFree(position.x()+1,position.y()))
//                return addMove(position, new Position(position.x()+1, position.y()));
//        return false;
    }
    /**
     * Check if the piece can move forward and rightward
     *      @param position the position to check
     */
    private ChessMove frontRightMoves(Position origin, Position position) {
//        try{
        return new ChessMove(origin,new Position(position.x() + color.getValue(),position.y()+ color.getValue()));
//        }catch (Exception e) {
//            System.err.println("Exception in frontLeftMoves");
//            return null;}
//        if(ChessBoard.getInstance().isFree(position.x() + color.getValue(),position.y()+ color.getValue()))
//            return addMove(position, new Position(position.x() + color.getValue(), position.y()+ color.getValue()));
//        return false;



//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//        if(ChessBoard.getInstance().getPiece(position).getColor() == Color.WHITE)
//            if(ChessBoard.getInstance().isFree(position.x()+1,position.y()+1))
//                return addMove(position, new Position(position.x()+1, position.y()+1));
//        else //if piece is black
//            if(ChessBoard.getInstance().isFree(position.x()-1,position.y()-1))
//                return addMove(position, new Position(position.x()-1, position.y()-1));
//        return false;
    }
    /**
     * Check if the piece can move forward and leftward
     *      @param position the position to check
     */
    private ChessMove frontLeftMoves(Position origin,Position position) {
//        try{
        return new ChessMove(origin, new Position(position.x() + color.getValue(), position.y() + color.getValue() * -1));
//        }catch (Exception e) {
//            System.err.println("Exception in frontLeftMoves");
//            return null;}
//        if(ChessBoard.getInstance().isFree(position.x() + color.getValue(),position.y()+ color.getValue()*-1))
//            return addMove(position, new Position(position.x() + color.getValue(), position.y()+ color.getValue()*-1));
//        return false;



//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//        if(ChessBoard.getInstance().getPiece(position).getColor() == Color.WHITE)
//            if(ChessBoard.getInstance().isFree(position.x()+1,position.y()-1))
//                return addMove(position, new Position(position.x()+1, position.y()-1));
//        else //if piece is black
//            if(ChessBoard.getInstance().isFree(position.x()-1,position.y()+1))
//                return addMove(position, new Position(position.x()-1, position.y()+1));
//        return false;
    }
    /**
     * Check if the piece can move backward and rightward
     *      @param position the position to check
     */
    private ChessMove backRightMoves(Position origin, Position position) {
        return new ChessMove(origin,new Position(position.x() + color.getValue()*-1,position.y()+ color.getValue()));
//        if(ChessBoard.getInstance().isFree(position.x() + color.getValue()*-1,position.y()+ color.getValue()))
//            return addMove(position, new Position(position.x() + color.getValue()*-1, position.y()+ color.getValue()));
//        return false;



//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//        if(ChessBoard.getInstance().getPiece(position).getColor() == Color.WHITE)
//            if(ChessBoard.getInstance().isFree(position.x()-1,position.y()+1))
//                return addMove(position, new Position(position.x()-1, position.y()+1));
//        else //if piece is black
//            if(ChessBoard.getInstance().isFree(position.x()+1,position.y()-1))
//                return addMove(position, new Position(position.x()+1, position.y()-1));
//        return false;
    }
    /**
     * Check if the piece can move backward and leftward
     *      @param position the position to check
     */
    private ChessMove backLeftMoves(Position origin, Position position) {
        return new ChessMove(origin,new Position(position.x() + color.getValue()*-1,position.y()+ color.getValue()*-1));
//        if(ChessBoard.getInstance().isFree(position.x() + color.getValue()*-1,position.y()+ color.getValue()*-1))
//            return addMove(position, new Position(position.x() + color.getValue()*-1, position.y()+ color.getValue()*-1));
//        return false;



//        if(ChessBoard.getInstance().isFree(position))
//            System.err.println("Position is free, any piece can move there");
//        if(ChessBoard.getInstance().getPiece(position).getColor() == Color.WHITE)
//            if(ChessBoard.getInstance().isFree(position.x()-1,position.y()-1))
//                return addMove(position, new Position(position.x()-1, position.y()-1));
//        else //if piece is black
//            if(ChessBoard.getInstance().isFree(position.x()+1,position.y()+1))
//                return addMove(position, new Position(position.x()+1, position.y()+1));
//        return false;
    }
    //endregion
}

