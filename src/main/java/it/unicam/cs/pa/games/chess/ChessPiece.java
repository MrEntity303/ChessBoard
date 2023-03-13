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
                if(ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece, positionPiece, i+1,0).getDestination()))
                    addMove(this.moveInDirection(positionPiece, positionPiece, i+1,0).isValid());
            checkRightAndLeftPawn();
        }else{
            if(ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece,positionPiece, 1,0).getDestination()))
                addMove(this.moveInDirection(positionPiece,positionPiece, 1, 0).isValid());
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
        if(ChessBoard.getInstance().onBoard(this.moveInDirection(positionPiece,positionPiece, 1, 1).getDestination()) && !ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece,positionPiece, 1, 1).getDestination()))
            addMove(this.moveInDirection(positionPiece,positionPiece, 1, 1).isValid());
        if(ChessBoard.getInstance().onBoard(this.moveInDirection(positionPiece,positionPiece, 1, -1).getDestination()) && !ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece,positionPiece, 1, -1).getDestination()))
            addMove(this.moveInDirection(positionPiece,positionPiece, 1, -1).isValid());
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

    //region Move
    /**
     * Check if the piece can move in the given direction
     * @param origin the starting position of the piece
     * @param position the position to check
     * @param rowOffset the number of rows to move in the forward/backward direction
     * @param colOffset the number of columns to move in the right/left direction
     */
    private ChessMove moveInDirection(Position origin, Position position, int rowOffset, int colOffset) {
        return new ChessMove(origin, new Position(position.x() + rowOffset * color.getValue(), position.y() + colOffset * color.getValue()));
    }
//    /**
//     * Check if the piece can move forward
//     *      @param position the position to check
//     */
//    private ChessMove frontMoves(Position origin, Position position) {
//        return new ChessMove(origin,new Position(position.x() + color.getValue(),position.y()));
//    }
//    /**
//     * Check if the piece can move rightward
//     *      @param position the position to check
//     */
//    private ChessMove rightMoves(Position origin,Position position) {
//        return new ChessMove(origin, new Position(position.x(),position.y()+ color.getValue()));
//    }
//    /**
//     * Check if the piece can move leftward
//     *      @param position the position to check
//     */
//    private ChessMove leftMoves(Position origin, Position position) {
//        return new ChessMove(origin, new Position(position.x(),position.y()+ color.getValue()*-1));
//    }
//
//    /**
//     * Check if the piece can move backward
//     *      @param position the position to check
//     */
//    private ChessMove backMoves(Position origin, Position position) {
//        return new ChessMove(origin, new Position(position.x() + color.getValue()*-1,position.y()));
//    }
//    /**
//     * Check if the piece can move forward and rightward
//     *      @param position the position to check
//     */
//    private ChessMove frontRightMoves(Position origin, Position position) {
//        return new ChessMove(origin,new Position(position.x() + color.getValue(),position.y()+ color.getValue()));
//    }
//    /**
//     * Check if the piece can move forward and leftward
//     *      @param position the position to check
//     */
//    private ChessMove frontLeftMoves(Position origin,Position position) {
//        return new ChessMove(origin, new Position(position.x() + color.getValue(), position.y() + color.getValue() * -1));
//    }
//    /**
//     * Check if the piece can move backward and rightward
//     *      @param position the position to check
//     */
//    private ChessMove backRightMoves(Position origin, Position position) {
//        return new ChessMove(origin,new Position(position.x() + color.getValue()*-1,position.y()+ color.getValue()));
//    }
//    /**
//     * Check if the piece can move backward and leftward
//     *      @param position the position to check
//     */
//    private ChessMove backLeftMoves(Position origin, Position position) {
//        return new ChessMove(origin,new Position(position.x() + color.getValue()*-1,position.y()+ color.getValue()*-1));
//    }
    //endregion
}

//    /**
//     * Check if the piece can move in the given direction
//     * @param origin the starting position of the piece
//     * @param position the position to check
//     * @param rowOffset the number of rows to move in the forward/backward direction
//     * @param colOffset the number of columns to move in the right/left direction
//     */
//    private ChessMove moveInDirection(Position origin, Position position, int rowOffset, int colOffset) {
//        return new ChessMove(origin, new Position(position.x() + rowOffset * color.getValue(), position.y() + colOffset * color.getValue()));
//    }



//    moveInDirection(origin, position, 1, 0) // forward move
//    moveInDirection(origin, position, -1, 0) // backward move
//    moveInDirection(origin, position, 0, 1) // right move
//    moveInDirection(origin, position, 0, -1) // left move
//    moveInDirection(origin, position, 1, 1) // forward and right move
//    moveInDirection(origin, position, 1, -1) // forward and left move
//    moveInDirection(origin, position, -1, 1) // backward and right move
//    moveInDirection(origin, position, -1, -1) // backward and left move

