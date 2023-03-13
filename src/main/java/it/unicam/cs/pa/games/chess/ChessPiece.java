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
        if (move != null)
            moves.add((ChessMove) move);
    }

    @Override
    public List<ChessMove> getList() {
        return this.moves;
    }

    @Override
    public void update() {
        moves = this.calculateMoves();
    }

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
        if ((this.getColor() == Color.BLACK) && (positionPiece.x() == 6) || this.getColor() == Color.WHITE && (positionPiece.x() == 1)) {

            for (int i = 1; i < 3; i++)
                if (ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece, i, 0).getDestination()))
                    addMove(this.moveInDirection(positionPiece, i, 0).isValid());
            checkRightAndLeftPawn();
        } else {
            if (ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece, 1, 0).getDestination()))
                addMove(this.moveInDirection(positionPiece, 1, 0).isValid());
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
        if (ChessBoard.getInstance().onBoard(this.moveInDirection(positionPiece, 1, 1).getDestination())
                && !ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece, 1, 1).getDestination()))
            addMove(this.moveInDirection(positionPiece, 1, 1).isValid());
        if (ChessBoard.getInstance().onBoard(this.moveInDirection(positionPiece, 1, -1).getDestination())
                && !ChessBoard.getInstance().isFree(this.moveInDirection(positionPiece, 1, -1).getDestination()))
            addMove(this.moveInDirection(positionPiece, 1, -1).isValid());
    }

    private List<ChessMove> knightMoves() {
        moves.clear();
        Position piecePosition = ChessBoard.getInstance().getPosition(this);
        addMove(moveInDirection(piecePosition, 2, 1).isValid()); // forward and right move
        addMove(moveInDirection(piecePosition, 1, 2).isValid()); // forward and right move

        addMove(moveInDirection(piecePosition, 2, -1).isValid()); // forward and left move
        addMove(moveInDirection(piecePosition, 1, -2).isValid()); // forward and left move

        addMove(moveInDirection(piecePosition, -2, 1).isValid()); // backward and right move
        addMove(moveInDirection(piecePosition, -1, 2).isValid()); // backward and right move

        addMove(moveInDirection(piecePosition, -2, -1).isValid()); // backward and left move
        addMove(moveInDirection(piecePosition, -1, -2).isValid()); // backward and left move
        return moves;
    }

    private List<ChessMove> bishopMoves() {
        moves.clear();
        Position piecePosition = ChessBoard.getInstance().getPosition(this);
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        legalDirection(piecePosition, directions);
        return moves;
    }

    private List<ChessMove> rookMoves() {
        moves.clear();
        Position piecePosition = ChessBoard.getInstance().getPosition(this);
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        legalDirection(piecePosition, directions);
        return moves;
    }

    private List<ChessMove> queenMoves() {
        moves.clear();
        Position piecePosition = ChessBoard.getInstance().getPosition(this);
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        legalDirection(piecePosition, directions);
        return moves;
    }

    private List<ChessMove> kingMoves() {
        moves.clear();
        Position piecePosition = ChessBoard.getInstance().getPosition(this);
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] direction : directions)
            addMove(this.moveInDirection(piecePosition, direction[0], direction[1]).isValid());
        return moves;
    }

    //region Move

    /**
     * Check if the piece can move in the given direction
     *
     * @param origin  the position to check
     * @param rowOffset the number of rows to move in the forward/backward direction
     * @param colOffset the number of columns to move in the right/left direction
     */
    private ChessMove moveInDirection(Position origin, int rowOffset, int colOffset) {
        return new ChessMove(origin, new Position(origin.x() + rowOffset * color.getValue(), origin.y() + colOffset * color.getValue()));
    }
    private void legalDirection(Position origin,int[][] legalDirections) {
        for (int[] direction : legalDirections) {
            int i = 1;
            while (ChessBoard.getInstance().onBoard(this.moveInDirection(origin, i * direction[0], i * direction[1]).getDestination())
                    && this.moveInDirection(origin, i * direction[0], i * direction[1]).isValid() != null) {
                addMove(this.moveInDirection(origin, i * direction[0], i * direction[1]).isValid());
                if(this.moveInDirection(origin, i * direction[0], i * direction[1]).isValid().getIsCapture())
                    break;
                i++;
            }
        }
    }
    //endregion
}


//    moveInDirection(origin, position, 1, 0) // forward move
//    moveInDirection(origin, position, -1, 0) // backward move
//    moveInDirection(origin, position, 0, 1) // right move
//    moveInDirection(origin, position, 0, -1) // left move
//    moveInDirection(origin, position, 1, 1) // forward and right move
//    moveInDirection(origin, position, 1, -1) // forward and left move
//    moveInDirection(origin, position, -1, 1) // backward and right move
//    moveInDirection(origin, position, -1, -1) // backward and left move


//    moveInDirection(origin, position, 2, 1) // forward and right move
//    moveInDirection(origin, position, 1, 2) // forward and right move

//    moveInDirection(origin, position, 2, -1) // forward and left move
//    moveInDirection(origin, position, 1, -2) // forward and left move

//    moveInDirection(origin, position, -2, 1) // backward and right move
//    moveInDirection(origin, position, -1, 2) // backward and right move

//    moveInDirection(origin, position, -2, -1) // backward and left move
//    moveInDirection(origin, position, -1, -2) // backward and left move




