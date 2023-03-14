package it.unicam.cs.pa.games.chess;

import it.unicam.cs.pa.cli.CliMakeTurn;
import it.unicam.cs.pa.cli.chess.CliPromotionObserver;
import it.unicam.cs.pa.games.Color;
import it.unicam.cs.pa.games.Game;
import it.unicam.cs.pa.games.Position;
import it.unicam.cs.pa.players.chess.ChessPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGame extends Game {
    private final Scanner scanner;
    private final ChessPlayer[] players;
    private final ChessBoard board = ChessBoard.getInstance();

    private final CliMakeTurn cliMakeTurn;

    public ChessGame(Scanner scanner, ChessPlayer[] players) {
        this.scanner = scanner;
        this.cliMakeTurn= new CliMakeTurn(scanner);
        this.players = players;
    }

    @Override
    public void initialize() {
        super.initialize();
        initializePlayers();
        board.setPromotionObserver(new CliPromotionObserver(scanner));
        System.out.println("Inizializzazione del gioco di scacchi");
    }

    private void initializePlayers() {
        players[0].setColor(Color.WHITE);
        players[1].setColor(Color.BLACK);
    }

    @Override
    public void makeTurn() {
        for (ChessPlayer player:players) {
            System.out.println("Turn of Player " + player.getName());
            printBoard();
//            player.setCheckKing();
            while(!player.makeMove(cliMakeTurn.inputTurn())) {
                System.out.println("Mossa non valida");
                System.out.println("Turn of Player " + player.getName());
                printBoard();
            }
            if(endOfGame())
                break;

        }
    }

    @Override
    public void printBoard() {
        System.out.println("Stato del gioco");
        System.out.println("    a    b    c    d    e    f    g    h");
        System.out.println("  +----+----+----+----+----+----+----+----+");
        int rowNumber = 1;
        for (ArrayList<ChessPiece> row : board.getBoard()) {
            System.out.print(rowNumber + " | ");
            for (ChessPiece piece : row)
                if (piece != null)
                    System.out.print(piece.getColor().equals(Color.WHITE) ? "W"+piece.getType().getValue() + " | " : "B"+piece.getType().getValue() + " | ");
                else
                    System.out.print("   | ");
            System.out.println(rowNumber++ + " ");
            System.out.println("  +----+----+----+----+----+----+----+----+");
        }
        System.out.println("    a    b    c    d    e    f    g    h\n");
    }

    @Override
    public boolean endOfGame() {
//        players[0].setCheckKing();
//        players[1].setCheckKing();
        return players[0].isCheckKing() && players[0].getKing().getList().isEmpty() && this.existGuardianMove(players[0])
                ||
               players[1].isCheckKing() && players[1].getKing().getList().isEmpty() && this.existGuardianMove(players[1]);
    }

    public boolean existGuardianMove(ChessPlayer player)
    {
        return player.isCheckKing() && this.positionsTwoIntervals(ChessBoard.getInstance().getPosition(players[0].getKing()), ChessBoard.getInstance().lastMove().getDestination()).isEmpty();
    }

    private List<Position> positionsTwoIntervals(Position position1, Position position2)
    {
        List<Position> positionList = new ArrayList<>();
        if(Math.abs(position1.y() - position2.y()) == Math.abs(position1.x() - position2.x()))
            this.getDiagonalPositions(positionList, position1, position2);
        else if(position1.y() == position2.y())
            this.getForwardAndBackwardPositions(positionList, position1, position2);
        else if (position1.x() == position2.x())
            this.getLeftAndRight(positionList, position1, position2);
        return positionList;
    }

    private void getForwardAndBackwardPositions(List<Position> list, Position position1, Position position2)
    {
        for(int i = (position1.x())+1; i < position2.x(); i++)
            list.add(new Position(i, position1.y()));
    }
    private void getDiagonalPositions(List<Position> list, Position position1, Position position2)
    {
        int x = (position2.y() > position1.y()) ? 1 : -1;
        int y = (position2.x() > position1.x()) ? 1 : -1;
        int column = position1.y();
        int row = position1.x();
        while (column != position2.y() && row != position2.x()) {
            column += x;
            row += y;
            list.add(new Position(row, column));
        }
        list.remove(list.size()-1);
    }
    private void getLeftAndRight(List<Position> list, Position position1, Position position2)
    {
        for(int i = (position1.y())+1; i < position2.y(); i++)
            list.add(new Position(position1.x(), i));
    }

    @Override
    public void printResult() {
        if(players[0].isCheckKing())
            System.out.println("Il giocatore " + players[1].getName() + " ha vinto");
        else if(players[1].isCheckKing())
            System.out.println("Il giocatore " + players[0].getName() + " ha vinto");
        else
            System.out.println("Il gioco è finito in parità");
    }
}
