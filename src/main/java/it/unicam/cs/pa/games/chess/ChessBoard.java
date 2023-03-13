package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.cli.chess.CliPromotionObserver;
import it.unicam.cs.pa.cli.chess.PromotionObserver;
import it.unicam.cs.pa.games.Board;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Piece;
import it.unicam.cs.pa.games.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessBoard implements Board {
    private static ChessBoard instance;
    private  ArrayList<ArrayList<ChessPiece>> board;

    private final List<ChessMove> movesHistory = new ArrayList<>();

    private  final ArrayList<ChessPiece> observers = new ArrayList<>();
    private PromotionObserver promotionObserver;
    //region Constructors
    /**
     * Create a new ChessBoard
     *      @param width the width of the board
     *      @param height the height of the board
     **/
    private ChessBoard(int width, int height) {
        if(width < 1 || height < 1)
            throw new IllegalArgumentException("Width and height must be greater than 0");

        this.board = new ArrayList<>();
        for(int i = 0; i < width; i++) {
        	this.board.add(new ArrayList<>());
        	for(int j = 0; j < height; j++) {
        		this.board.get(i).add(null);
        	}
        }
    }

    /**
     * Create a new ChessBoard
     **/
    private ChessBoard(){
        this(8, 8);
    }

    public static ChessBoard getInstance() {
        if (instance == null) {
            instance = new ChessBoard();
            instance.setupBoard();
            instance.createListObservers();
            instance.notifyObservers();
        }
        return instance;
    }
    //endregion

    //region Methods ChessBoard
    public void setPromotionObserver(CliPromotionObserver promotionObserver) {
        this.promotionObserver = promotionObserver;
    }

    public PromotionObserver getPromotionObserver() {
        return promotionObserver;
    }

    public ArrayList<ArrayList<ChessPiece>> getBoard() {
        return board;
    }
    /**
     * Get the width of the board
     *      @return the width of the board
     **/
    @Override
    public int getWight(){
        return this.board.size();
    }

    /**
     * Get the height of the board
     *      @return the height of the board
     **/
    @Override
    public int getHeight() {
        return this.board.get(0).size();
    }

    /**
     * Set up the white pieces in the correct position
     * @param piece the piece to set
     * @param position the position where to set the piece
     **/
    @Override
    public <T> void setPiece(Piece<T> piece, Position position) {
        this.board.get(position.x()).set(position.y(), (ChessPiece) piece);
    }

    /**
     * Get the piece in the specified position
     *      @param position the position
     *      @return the piece in the specified position
     **/
    @Override
    public ChessPiece getPiece(Position position) {return this.board.get(position.x()).get(position.y());}
    /**
     * Get the piece in the specified position
     *      @param x the x coordinate
     *      @param y the y coordinate
     *      @return the piece in the specified position
     **/
    @Override
    public ChessPiece getPiece(int x, int y) {return this.board.get(x).get(y);}

    /**
     * Remove the piece in the specified position
     *      @param position the position
     **/
    @Override
    public void removePiece(Position position){this.board.get(position.x()).set(position.y(), null);}

    /**
     * Check if the specified position is free
     *      @param position the position
     *      @return true if the position is free, false otherwise
     **/
    @Override
    public boolean isFree(Position position){
        return this.board.get(position.x()).get(position.y()) == null;
    }
    /**
     * Check if the specified position is free
     *      @param x the x coordinate
     *      @param y the y coordinate
     *      @return true if the position is free, false otherwise
     **/
    @Override
    public boolean isFree(int x, int y){
        return this.board.get(x).get(y) == null;
    }

    /**
     * Check if the specified position is valid
     *      @param piece the position
     *      @return Position> if the position is valid, null otherwise
     **/
    @Override
    public Position getPosition(Piece<?> piece)
    {
        for(int i = 0; i < this.getWight(); i++) {
            if(this.board.get(i).contains((ChessPiece) piece))
                return new Position(i, this.board.get(i).indexOf(piece));
        }
        return null;
    }

    /**
     * Add move to the history
     **/
    public void addMove(ChessMove move) {
    	this.movesHistory.add(move);
    }

    /**
     * Check if the specified position is on the board
     *      @param position the position
     *      @return true if the position is on the board, false otherwise
     **/
    @Override
    public boolean onBoard(Position position){
        return position.x() >= 0 && position.x() < this.getWight() && position.y() >= 0 && position.y() < this.getHeight();
    }

    /**
     * Move a piece from the origin position to the destination position
     *      @param origin the origin position
     *      @param destination the destination position
     **/
    @Override
    public boolean move(Position origin, Position destination, Color color) {
        if(!ChessBoard.getInstance().onBoard(origin) && ChessBoard.getInstance().onBoard(destination)) return false;

        ChessPiece piece = this.getPiece(origin);
        if(piece== null) return false;

        if(piece.getColor() != color) return false;

        Optional<ChessMove> moveFromList = piece.getList().stream().filter(move -> move.getDestination().equals(destination)).findFirst();
        if (moveFromList.isEmpty()) return false;

        this.addMove(moveFromList.get());

        this.executeCapture(destination, moveFromList.get());

        if(this.executePromotion(origin, destination, piece, moveFromList.get())) return true;

        this.swapPiece(piece, origin, destination);
        return true;
        }

        /**
         * Move a piece from the origin position to the destination position
         *      @param origin the origin position
         *      @param destination the destination position
         **/
        private void swapPiece(ChessPiece piece,Position origin, Position destination){
            this.removePiece(origin);
            this.setPiece(piece, destination);
            this.notifyObservers();
        }

        /**
         * Execute the capture of a piece
         *      @param destination the origin position
         *      @param move the destination position
         **/
        private void executeCapture(Position destination, ChessMove move){
            if (move.getIsCapture()) {
                this.removeObserver(this.getPiece(destination));
                this.removePiece(destination);
            }
        }

        /**
         * Execute the promotion of a piece
         *      @param origin the origin position
         *      @param destination the destination position
         *      @param piece the piece to promote
         *      @param move the move to execute
         **/
        private boolean executePromotion(Position origin, Position destination, ChessPiece piece, ChessMove move){
            if (move.getIsPromotion()) {
                this.removePiece(origin);
                this.setPiece(piece, destination);
                ChessBoard.getInstance().getPiece(destination).setType(this.getPromotionObserver().handlePromotion(piece.getColor()));
                this.notifyObservers();
            }
            return false;
        }

        /**
         * Reset the board
         **/
        public void resetBoard() {//TODO: da rivedere per i test
            this.board.clear();
            this.observers.clear();
            this.board = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                this.board.add(new ArrayList<>());
                for (int j = 0; j < 8; j++) {
                    this.board.get(i).add(null);
                }
            }
            this.setupBoard();
            this.createListObservers();
            this.notifyObservers();

        }
    //endregion

    //region Methods Observer
    /**
     * Add an observer to the board
     *      @param observer the observer to add
     **/
    public void addObserver(ChessPiece observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from the board
     *      @param observer the observer to remove
     **/
    public void removeObserver(ChessPiece observer) {
        this.observers.remove(observer);
    }
    /**
     * Get the list of observers
     */
    public List<ChessPiece> getObservers() {
        return this.observers;
    }

    /**
     * Notify the observers that a piece has been moved
     **/
    private void notifyObservers() {
        for (ChessPiece observer : this.observers) {
            observer.update();
        }
    }

    /**
     * Create list observers of the board
     *      Add the observers of the board
     **/
    public void createListObservers() {
        	for(int i = 0; i < this.getWight(); i++) {
        		for(int j = 0; j < this.getHeight(); j++) {
        			if(this.getPiece(new Position(i, j)) != null) {
        				this.addObserver(this.getPiece(new Position(i, j)));
        			}
        		}
        	}
    }
    //endregion

    //region SetupPieces
    /**
     * Set up the board with the pieces in the correct position
     **/
    private void setupBoard() {
        this.setupBlackPieces();
        this.setupWhitePieces();
        this.setupPawns();
    }

    /**
     * Set up the black pieces in the correct position
     **/
    private void setupBlackPieces() {
        //Posizione le torri nere
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.BLACK), new Position(7,0));//7, 0);
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.BLACK), new Position(7,7));//7, 7);
        //Posizione i cavalli neri
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.BLACK), new Position(7,1));//7, 1);
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.BLACK), new Position(7, 6));//7, 6);
        //Posizione i alfieri neri
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.BLACK), new Position(7, 2));//7, 2);
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.BLACK), new Position(7, 5));//7, 5);
        //Posizione il re nero
        this.setPiece(new ChessPiece(ChessPieceType.KING, Color.BLACK), new Position(7, 4));//7, 4);
        //Posizione la regina nera
        this.setPiece(new ChessPiece(ChessPieceType.QUEEN, Color.BLACK), new Position(7, 3));//7, 3);
    }

    /**
     * Set up the white pieces in the correct position
     **/
    private void setupWhitePieces() {
        //Posizione le torri bianche
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.WHITE), new Position(0,0));//0, 0);
        this.setPiece(new ChessPiece(ChessPieceType.ROOK, Color.WHITE), new Position(0,7));//0, 7);
        //Posizione i cavalli bianchi
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.WHITE), new Position(0,1));//0, 1);
        this.setPiece(new ChessPiece(ChessPieceType.KNIGHT, Color.WHITE), new Position(0, 6));//0, 6);
        //Posizione i alfieri bianchi
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.WHITE), new Position(0, 2));//0, 2);
        this.setPiece(new ChessPiece(ChessPieceType.BISHOP, Color.WHITE), new Position(0,5));//0, 5);
        //Posizione la regina bianca
        this.setPiece(new ChessPiece(ChessPieceType.QUEEN, Color.WHITE), new Position(0,3));//0, 3);
        //Posizione il re bianco
        this.setPiece(new ChessPiece(ChessPieceType.KING, Color.WHITE), new Position(0,4));//0, 4);
    }

    /**
     * Set up the pawns in the correct position
     **/
    private void setupPawns() {
        //Posizione i pedoni bianchi e neri
        for(int i = 0; i < 8; i++) {
            this.setPiece(new ChessPiece(ChessPieceType.PAWN, Color.WHITE), new Position(1,i));//1, i);
            this.setPiece(new ChessPiece(ChessPieceType.PAWN, Color.BLACK), new Position(6,i));//6, i);
        }
    }
    //endregion

}
