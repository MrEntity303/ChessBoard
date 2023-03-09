package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.games.*;

import java.util.ArrayList;
import java.util.List;

public class ChessPiece implements Piece<ChessPieceType>, Observer {
    private final ChessPieceType type;
    private final Color color;
    private List<ChessMove> moves = new ArrayList<>();

    public ChessPiece(ChessPieceType type, Color color) {
        this.type = type;
        this.color = color;
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
        //ChessMove move = new ChessMove(origin, destination);
        if(move!=null)
            moves.add((ChessMove) move);
        //return move;
    }

//    @Override
//    public void addMove(ChessMove move) {
//        //ChessMove move = new ChessMove(origin, destination);
//        moves.add(move);
//        //return move;
//    }

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
        moves.clear();
        //Controllo se il pedone è in posizione iniziale e puo effettuare 2 mosse
        if((this.getColor()==Color.BLACK)&&(ChessBoard.getInstance().getPosition(this).x()==6) ||this.getColor()==Color.WHITE&&(ChessBoard.getInstance().getPosition(this).x()==1)){
            for(int i = 0; i < 2;i++)
                if(ChessBoard.getInstance().isFree(this.frontMoves(new Position(ChessBoard.getInstance().getPosition(this).x() + i * this.getColor().getValue(), ChessBoard.getInstance().getPosition(this).y())).getDestination()))
                    addMove(this.frontMoves(new Position(ChessBoard.getInstance().getPosition(this).x() + i * this.getColor().getValue(), ChessBoard.getInstance().getPosition(this).y())).isValid());
            checkRightAndLeftPawn();
        }else{
            if(ChessBoard.getInstance().isFree(this.frontMoves(ChessBoard.getInstance().getPosition(this)).getDestination()))
                addMove(this.frontMoves(ChessBoard.getInstance().getPosition(this)).isValid());
            checkRightAndLeftPawn();
        }
        return moves;
    }

    private void checkRightAndLeftPawn() {
        if(ChessBoard.getInstance().onBoard(this.frontRightMoves(ChessBoard.getInstance().getPosition(this)).getDestination()) && !ChessBoard.getInstance().isFree(this.frontRightMoves(ChessBoard.getInstance().getPosition(this)).getDestination()))
            addMove(this.frontRightMoves(ChessBoard.getInstance().getPosition(this)).isValid());
        if(ChessBoard.getInstance().onBoard(this.frontLeftMoves(ChessBoard.getInstance().getPosition(this)).getDestination())&& !ChessBoard.getInstance().isFree(this.frontLeftMoves(ChessBoard.getInstance().getPosition(this)).getDestination()))
            addMove(this.frontLeftMoves(ChessBoard.getInstance().getPosition(this)).isValid());
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
    private ChessMove frontMoves(Position position) {
        return new ChessMove(position,new Position(position.x() + color.getValue(),position.y()));


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
    private ChessMove rightMoves(Position position) {
        return new ChessMove(position, new Position(position.x(),position.y()+ color.getValue()));
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
    private ChessMove leftMoves(Position position) {
        return new ChessMove(position, new Position(position.x(),position.y()+ color.getValue()*-1));
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
    private ChessMove backMoves(Position position) {
        return new ChessMove(position, new Position(position.x() + color.getValue()*-1,position.y()));
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
    private ChessMove frontRightMoves(Position position) {
//        try{
            return new ChessMove(position,new Position(position.x() + color.getValue(),position.y()+ color.getValue()));
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
    private ChessMove frontLeftMoves(Position position) {
//        try{
            return new ChessMove(position, new Position(position.x() + color.getValue(), position.y() + color.getValue() * -1));
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
    private ChessMove backRightMoves(Position position) {
        return new ChessMove(position,new Position(position.x() + color.getValue()*-1,position.y()+ color.getValue()));
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
    private ChessMove backLeftMoves(Position position) {
        return new ChessMove(position,new Position(position.x() + color.getValue()*-1,position.y()+ color.getValue()*-1));
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

